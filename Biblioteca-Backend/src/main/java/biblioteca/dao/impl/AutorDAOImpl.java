package biblioteca.dao.impl;

import biblioteca.dao.AutorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import biblioteca.models.Autor;

import java.util.List;
import java.util.Optional;

@Repository
public class AutorDAOImpl implements AutorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Autor> findAll() {
        String sql = "SELECT * FROM autor ORDER BY apellido, nombre";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Autor.class));
    }

    @Override
    public Optional<Autor> findById(Long id) {
        String sql = "SELECT * FROM autor WHERE id = ?";
        try {
            Autor autor = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Autor.class), id);
            return Optional.ofNullable(autor);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Autor save(Autor autor) {
        String sql = "INSERT INTO autor (nombre, apellido, fecha_nacimiento, nacionalidad, biografia) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                autor.getNombre(),
                autor.getApellido(),
                autor.getFechaNacimiento(),
                autor.getNacionalidad(),
                autor.getBiografia());
        return autor;
    }

    @Override
    public Autor update(Autor autor) {
        String sql = "UPDATE autor SET nombre = ?, apellido = ?, fecha_nacimiento = ?, " +
                "nacionalidad = ?, biografia = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                autor.getNombre(),
                autor.getApellido(),
                autor.getFechaNacimiento(),
                autor.getNacionalidad(),
                autor.getBiografia(),
                autor.getId());
        return autor;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM autor WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Autor> findByNombre(String nombre) {
        String sql = "SELECT * FROM autor WHERE LOWER(nombre) LIKE LOWER(?) ORDER BY apellido, nombre";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Autor.class), "%" + nombre + "%");
    }

    @Override
    public List<Autor> findByApellido(String apellido) {
        String sql = "SELECT * FROM autor WHERE LOWER(apellido) LIKE LOWER(?) ORDER BY apellido, nombre";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Autor.class), "%" + apellido + "%");
    }

    @Override
    public List<Autor> findByNacionalidad(String nacionalidad) {
        String sql = "SELECT * FROM autor WHERE LOWER(nacionalidad) LIKE LOWER(?) ORDER BY apellido, nombre";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Autor.class), "%" + nacionalidad + "%");
    }

    @Override
    public List<Autor> findByNombreCompleto(String nombre, String apellido) {
        String sql = "SELECT * FROM autor WHERE LOWER(nombre) LIKE LOWER(?) AND LOWER(apellido) LIKE LOWER(?) " +
                "ORDER BY apellido, nombre";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Autor.class),
                "%" + nombre + "%", "%" + apellido + "%");
    }

    @Override
    public List<Autor> searchByNombre(String texto) {
        String sql = "SELECT * FROM autor WHERE LOWER(nombre) LIKE LOWER(?) OR LOWER(apellido) LIKE LOWER(?) " +
                "ORDER BY apellido, nombre";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Autor.class),
                "%" + texto + "%", "%" + texto + "%");
    }
}