package biblioteca.dao.impl;

import biblioteca.dao.EditorialDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import biblioteca.models.Editorial;

import java.util.List;
import java.util.Optional;

@Repository
public class EditorialDAOImpl implements EditorialDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Editorial> findAll() {
        String sql = "SELECT * FROM editorial ORDER BY nombre";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Editorial.class));
    }

    @Override
    public Optional<Editorial> findById(Long id) {
        String sql = "SELECT * FROM editorial WHERE id = ?";
        try {
            Editorial editorial = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Editorial.class), id);
            return Optional.ofNullable(editorial);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Editorial save(Editorial editorial) {
        String sql = "INSERT INTO editorial (nombre, direccion, telefono, email, fecha_fundacion, pais) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                editorial.getNombre(),
                editorial.getDireccion(),
                editorial.getTelefono(),
                editorial.getEmail(),
                editorial.getFechaFundacion(),
                editorial.getPais());
        return editorial;
    }

    @Override
    public Editorial update(Editorial editorial) {
        String sql = "UPDATE editorial SET nombre = ?, direccion = ?, telefono = ?, " +
                "email = ?, fecha_fundacion = ?, pais = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                editorial.getNombre(),
                editorial.getDireccion(),
                editorial.getTelefono(),
                editorial.getEmail(),
                editorial.getFechaFundacion(),
                editorial.getPais(),
                editorial.getId());
        return editorial;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM editorial WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Editorial> findByNombre(String nombre) {
        String sql = "SELECT * FROM editorial WHERE LOWER(nombre) LIKE LOWER(?) ORDER BY nombre";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Editorial.class), "%" + nombre + "%");
    }

    @Override
    public List<Editorial> findByPais(String pais) {
        String sql = "SELECT * FROM editorial WHERE LOWER(pais) LIKE LOWER(?) ORDER BY nombre";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Editorial.class), "%" + pais + "%");
    }

    @Override
    public Optional<Editorial> findByEmail(String email) {
        String sql = "SELECT * FROM editorial WHERE email = ?";
        try {
            Editorial editorial = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Editorial.class), email);
            return Optional.ofNullable(editorial);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsByNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM editorial WHERE LOWER(nombre) = LOWER(?)";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nombre);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM editorial WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }
}