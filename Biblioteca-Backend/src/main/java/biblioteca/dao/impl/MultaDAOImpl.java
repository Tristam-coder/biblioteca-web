package biblioteca.dao.impl;

import biblioteca.dao.MultaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import biblioteca.models.Multa;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class MultaDAOImpl implements MultaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Multa> findAll() {
        String sql = "SELECT * FROM multa ORDER BY fecha_generacion DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Multa.class));
    }

    @Override
    public Optional<Multa> findById(Long id) {
        String sql = "SELECT * FROM multa WHERE id = ?";
        try {
            Multa multa = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Multa.class), id);
            return Optional.ofNullable(multa);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Multa save(Multa multa) {
        String sql = "INSERT INTO multa (usuario_id, prestamo_id, monto, motivo, estado, " +
                "fecha_generacion, fecha_pago) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                multa.getUsuarioId(),
                multa.getPrestamoId(),
                multa.getMonto(),
                multa.getMotivo(),
                multa.getEstado(),
                multa.getFechaGeneracion(),
                multa.getFechaPago());
        return multa;
    }

    @Override
    public Multa update(Multa multa) {
        String sql = "UPDATE multa SET usuario_id = ?, prestamo_id = ?, monto = ?, motivo = ?, " +
                "estado = ?, fecha_generacion = ?, fecha_pago = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                multa.getUsuarioId(),
                multa.getPrestamoId(),
                multa.getMonto(),
                multa.getMotivo(),
                multa.getEstado(),
                multa.getFechaGeneracion(),
                multa.getFechaPago(),
                multa.getId());
        return multa;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM multa WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Multa> findByUsuarioId(Long usuarioId) {
        String sql = "SELECT * FROM multa WHERE usuario_id = ? ORDER BY fecha_generacion DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Multa.class), usuarioId);
    }

    @Override
    public List<Multa> findByPrestamoId(Long prestamoId) {
        String sql = "SELECT * FROM multa WHERE prestamo_id = ? ORDER BY fecha_generacion DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Multa.class), prestamoId);
    }

    @Override
    public List<Multa> findByEstado(String estado) {
        String sql = "SELECT * FROM multa WHERE estado = ? ORDER BY fecha_generacion DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Multa.class), estado);
    }

    @Override
    public List<Multa> findMultasPendientes() {
        String sql = "SELECT * FROM multa WHERE estado = 'PENDIENTE' ORDER BY fecha_generacion DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Multa.class));
    }

    @Override
    public List<Multa> findMultasPagadas() {
        String sql = "SELECT * FROM multa WHERE estado = 'PAGADA' ORDER BY fecha_pago DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Multa.class));
    }

    @Override
    public List<Multa> findMultasByUsuarioAndEstado(Long usuarioId, String estado) {
        String sql = "SELECT * FROM multa WHERE usuario_id = ? AND estado = ? ORDER BY fecha_generacion DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Multa.class), usuarioId, estado);
    }

    @Override
    public BigDecimal getTotalMultasPendientesByUsuario(Long usuarioId) {
        String sql = "SELECT COALESCE(SUM(monto), 0) FROM multa WHERE usuario_id = ? AND estado = 'PENDIENTE'";
        BigDecimal total = jdbcTemplate.queryForObject(sql, BigDecimal.class, usuarioId);
        return total != null ? total : BigDecimal.ZERO;
    }

    @Override
    public int countMultasPendientesByUsuario(Long usuarioId) {
        String sql = "SELECT COUNT(*) FROM multa WHERE usuario_id = ? AND estado = 'PENDIENTE'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, usuarioId);
        return count != null ? count : 0;
    }

    @Override
    public boolean tieneMultasPendientes(Long usuarioId) {
        String sql = "SELECT COUNT(*) FROM multa WHERE usuario_id = ? AND estado = 'PENDIENTE'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, usuarioId);
        return count != null && count > 0;
    }

    @Override
    public BigDecimal getTotalMultasPagadas() {
        String sql = "SELECT COALESCE(SUM(monto), 0) FROM multa WHERE estado = 'PAGADA'";
        BigDecimal total = jdbcTemplate.queryForObject(sql, BigDecimal.class);
        return total != null ? total : BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getTotalMultasPendientes() {
        String sql = "SELECT COALESCE(SUM(monto), 0) FROM multa WHERE estado = 'PENDIENTE'";
        BigDecimal total = jdbcTemplate.queryForObject(sql, BigDecimal.class);
        return total != null ? total : BigDecimal.ZERO;
    }
}