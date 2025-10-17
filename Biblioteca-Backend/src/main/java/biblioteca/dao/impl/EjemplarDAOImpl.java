package biblioteca.dao.impl;

import biblioteca.dao.EjemplarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import biblioteca.models.Ejemplar;

import java.util.List;
import java.util.Optional;

@Repository
public class EjemplarDAOImpl implements EjemplarDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Ejemplar> findAll() {
        String sql = "SELECT * FROM ejemplar ORDER BY obra_id, codigo_barras";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ejemplar.class));
    }

    @Override
    public Optional<Ejemplar> findById(Long id) {
        String sql = "SELECT * FROM ejemplar WHERE id = ?";
        try {
            Ejemplar ejemplar = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Ejemplar.class), id);
            return Optional.ofNullable(ejemplar);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Ejemplar save(Ejemplar ejemplar) {
        String sql = "INSERT INTO ejemplar (obra_id, codigo_barras, estado, ubicacion, observaciones) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                ejemplar.getObraId(),
                ejemplar.getCodigoBarras(),
                ejemplar.getEstado(),
                ejemplar.getUbicacion(),
                ejemplar.getObservaciones());
        return ejemplar;
    }

    @Override
    public Ejemplar update(Ejemplar ejemplar) {
        String sql = "UPDATE ejemplar SET obra_id = ?, codigo_barras = ?, estado = ?, " +
                "ubicacion = ?, observaciones = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                ejemplar.getObraId(),
                ejemplar.getCodigoBarras(),
                ejemplar.getEstado(),
                ejemplar.getUbicacion(),
                ejemplar.getObservaciones(),
                ejemplar.getId());
        return ejemplar;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM ejemplar WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Ejemplar> findByObraId(Long obraId) {
        String sql = "SELECT * FROM ejemplar WHERE obra_id = ? ORDER BY codigo_barras";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ejemplar.class), obraId);
    }

    @Override
    public List<Ejemplar> findByEstado(String estado) {
        String sql = "SELECT * FROM ejemplar WHERE estado = ? ORDER BY obra_id, codigo_barras";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ejemplar.class), estado);
    }

    @Override
    public Optional<Ejemplar> findByCodigoBarras(String codigoBarras) {
        String sql = "SELECT * FROM ejemplar WHERE codigo_barras = ?";
        try {
            Ejemplar ejemplar = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Ejemplar.class), codigoBarras);
            return Optional.ofNullable(ejemplar);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Ejemplar> findEjemplaresDisponibles() {
        String sql = "SELECT * FROM ejemplar WHERE estado = 'DISPONIBLE' ORDER BY obra_id, codigo_barras";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ejemplar.class));
    }

    @Override
    public List<Ejemplar> findEjemplaresPrestados() {
        String sql = "SELECT * FROM ejemplar WHERE estado = 'PRESTADO' ORDER BY obra_id, codigo_barras";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ejemplar.class));
    }

    @Override
    public boolean existsByCodigoBarras(String codigoBarras) {
        String sql = "SELECT COUNT(*) FROM ejemplar WHERE codigo_barras = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, codigoBarras);
        return count != null && count > 0;
    }

    @Override
    public int countByObraIdAndEstado(Long obraId, String estado) {
        String sql = "SELECT COUNT(*) FROM ejemplar WHERE obra_id = ? AND estado = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, obraId, estado);
        return count != null ? count : 0;
    }
}