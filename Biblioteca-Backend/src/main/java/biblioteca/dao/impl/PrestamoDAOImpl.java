package biblioteca.dao.impl;

import biblioteca.dao.PrestamoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import biblioteca.models.Prestamo;

import java.util.List;
import java.util.Optional;

@Repository
public class PrestamoDAOImpl implements PrestamoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Prestamo> findAll() {
        String sql = "SELECT * FROM prestamo ORDER BY fecha_prestamo DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Prestamo.class));
    }

    @Override
    public Optional<Prestamo> findById(Long id) {
        String sql = "SELECT * FROM prestamo WHERE id = ?";
        try {
            Prestamo prestamo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Prestamo.class), id);
            return Optional.ofNullable(prestamo);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        String sql = "INSERT INTO prestamo (usuario_id, ejemplar_id, fecha_prestamo, " +
                "fecha_devolucion_prevista, fecha_devolucion_real, estado, multa, observaciones) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                prestamo.getUsuarioId(),
                prestamo.getEjemplarId(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucionPrevista(),
                prestamo.getFechaDevolucionReal(),
                prestamo.getEstado(),
                prestamo.getMulta(),
                prestamo.getObservaciones());
        return prestamo;
    }

    @Override
    public Prestamo update(Prestamo prestamo) {
        String sql = "UPDATE prestamo SET usuario_id = ?, ejemplar_id = ?, fecha_prestamo = ?, " +
                "fecha_devolucion_prevista = ?, fecha_devolucion_real = ?, estado = ?, " +
                "multa = ?, observaciones = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                prestamo.getUsuarioId(),
                prestamo.getEjemplarId(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucionPrevista(),
                prestamo.getFechaDevolucionReal(),
                prestamo.getEstado(),
                prestamo.getMulta(),
                prestamo.getObservaciones(),
                prestamo.getId());
        return prestamo;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM prestamo WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Prestamo> findByUsuarioId(Long usuarioId) {
        String sql = "SELECT * FROM prestamo WHERE usuario_id = ? ORDER BY fecha_prestamo DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Prestamo.class), usuarioId);
    }

    @Override
    public List<Prestamo> findByEjemplarId(Long ejemplarId) {
        String sql = "SELECT * FROM prestamo WHERE ejemplar_id = ? ORDER BY fecha_prestamo DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Prestamo.class), ejemplarId);
    }

    @Override
    public List<Prestamo> findByEstado(String estado) {
        String sql = "SELECT * FROM prestamo WHERE estado = ? ORDER BY fecha_prestamo DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Prestamo.class), estado);
    }

    @Override
    public List<Prestamo> findPrestamosActivos() {
        String sql = "SELECT * FROM prestamo WHERE estado = 'ACTIVO' ORDER BY fecha_devolucion_prevista";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Prestamo.class));
    }

    @Override
    public List<Prestamo> findPrestamosVencidos() {
        String sql = "SELECT * FROM prestamo WHERE estado = 'ACTIVO' AND fecha_devolucion_prevista < CURRENT_DATE " +
                "ORDER BY fecha_devolucion_prevista";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Prestamo.class));
    }

    @Override
    public List<Prestamo> findPrestamosConMulta() {
        String sql = "SELECT * FROM prestamo WHERE multa > 0 ORDER BY multa DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Prestamo.class));
    }

    @Override
    public List<Prestamo> findPrestamosByUsuarioAndEstado(Long usuarioId, String estado) {
        String sql = "SELECT * FROM prestamo WHERE usuario_id = ? AND estado = ? ORDER BY fecha_prestamo DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Prestamo.class), usuarioId, estado);
    }

    @Override
    public int countPrestamosActivosByUsuario(Long usuarioId) {
        String sql = "SELECT COUNT(*) FROM prestamo WHERE usuario_id = ? AND estado = 'ACTIVO'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, usuarioId);
        return count != null ? count : 0;
    }

    @Override
    public boolean tienePrestamosVencidos(Long usuarioId) {
        String sql = "SELECT COUNT(*) FROM prestamo WHERE usuario_id = ? AND estado = 'ACTIVO' " +
                "AND fecha_devolucion_prevista < CURRENT_DATE";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, usuarioId);
        return count != null && count > 0;
    }
}