package biblioteca.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import biblioteca.models.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT * FROM usuario";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usuario.class));
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try {
            Usuario usuario = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Usuario.class), id);
            return Optional.ofNullable(usuario);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Usuario save(Usuario usuario) {
        String sql = "INSERT INTO usuario (dni, nombre, apellido, email, password, telefono, direccion, tipo_usuario, estado, fecha_registro) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                usuario.getDni(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getTelefono(),
                usuario.getDireccion(),
                usuario.getTipoUsuario(),
                usuario.getEstado(),
                usuario.getFechaRegistro());
        return usuario;
    }

    @Override
    public Usuario update(Usuario usuario) {
        String sql = "UPDATE usuario SET dni = ?, nombre = ?, apellido = ?, email = ?, password = ?, " +
                "telefono = ?, direccion = ?, tipo_usuario = ?, estado = ?, fecha_registro = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql,
                usuario.getDni(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getTelefono(),
                usuario.getDireccion(),
                usuario.getTipoUsuario(),
                usuario.getEstado(),
                usuario.getFechaRegistro(),
                usuario.getId());
        return usuario;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Usuario> findByDni(String dni) {
        String sql = "SELECT * FROM usuario WHERE dni = ?";
        try {
            Usuario usuario = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Usuario.class), dni);
            return Optional.ofNullable(usuario);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try {
            Usuario usuario = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Usuario.class), email);
            return Optional.ofNullable(usuario);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Usuario> findByEstado(String estado) {
        String sql = "SELECT * FROM usuario WHERE estado = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usuario.class), estado);
    }

    @Override
    public List<Usuario> findByTipoUsuario(String tipoUsuario) {
        String sql = "SELECT * FROM usuario WHERE tipo_usuario = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usuario.class), tipoUsuario);
    }

    @Override
    public boolean existsByDni(String dni) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE dni = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, dni);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }
}