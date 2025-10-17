package biblioteca.dao.impl;

import biblioteca.dao.TipoObraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import biblioteca.models.TipoObra;

import java.util.List;
import java.util.Optional;

@Repository
public class TipoObraDAOImpl implements TipoObraDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TipoObra> findAll() {
        String sql = "SELECT * FROM tipo_obra ORDER BY nombre";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TipoObra.class));
    }

    @Override
    public Optional<TipoObra> findById(Long id) {
        String sql = "SELECT * FROM tipo_obra WHERE id = ?";
        try {
            TipoObra tipoObra = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TipoObra.class), id);
            return Optional.ofNullable(tipoObra);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<TipoObra> findByNombre(String nombre) {
        String sql = "SELECT * FROM tipo_obra WHERE LOWER(nombre) = LOWER(?)";
        try {
            TipoObra tipoObra = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TipoObra.class), nombre);
            return Optional.ofNullable(tipoObra);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public TipoObra save(TipoObra tipoObra) {
        String sql = "INSERT INTO tipo_obra (nombre, descripcion) VALUES (?, ?)";
        jdbcTemplate.update(sql, tipoObra.getNombre(), tipoObra.getDescripcion());
        return tipoObra;
    }

    @Override
    public TipoObra update(TipoObra tipoObra) {
        String sql = "UPDATE tipo_obra SET nombre = ?, descripcion = ? WHERE id = ?";
        jdbcTemplate.update(sql, tipoObra.getNombre(), tipoObra.getDescripcion(), tipoObra.getId());
        return tipoObra;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM tipo_obra WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM tipo_obra WHERE LOWER(nombre) = LOWER(?)";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nombre);
        return count != null && count > 0;
    }
}