package biblioteca.dao.impl;

import biblioteca.dao.NotificacionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import biblioteca.models.Notificacion;

import java.util.List;
import java.util.Optional;

@Repository
public class NotificacionDAOImpl implements NotificacionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Notificacion> findAll() {
        String sql = "SELECT * FROM notificacion ORDER BY fecha_envio DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Notificacion.class));
    }

    @Override
    public Optional<Notificacion> findById(Long id) {
        String sql = "SELECT * FROM notificacion WHERE id = ?";
        try {
            Notificacion notificacion = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Notificacion.class), id);
            return Optional.ofNullable(notificacion);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Notificacion save(Notificacion notificacion) {
        String sql = "INSERT INTO notificacion (usuario_id, tipo, mensaje, leida, fecha_envio) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                notificacion.getUsuarioId(),
                notificacion.getTipo(),
                notificacion.getMensaje(),
                notificacion.getLeida(),
                notificacion.getFechaEnvio());
        return notificacion;
    }

    @Override
    public Notificacion update(Notificacion notificacion) {
        String sql = "UPDATE notificacion SET usuario_id = ?, tipo = ?, mensaje = ?, " +
                "leida = ?, fecha_envio = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                notificacion.getUsuarioId(),
                notificacion.getTipo(),
                notificacion.getMensaje(),
                notificacion.getLeida(),
                notificacion.getFechaEnvio(),
                notificacion.getId());
        return notificacion;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM notificacion WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Notificacion> findByUsuarioId(Long usuarioId) {
        String sql = "SELECT * FROM notificacion WHERE usuario_id = ? ORDER BY fecha_envio DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Notificacion.class), usuarioId);
    }

    @Override
    public List<Notificacion> findByTipo(String tipo) {
        String sql = "SELECT * FROM notificacion WHERE tipo = ? ORDER BY fecha_envio DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Notificacion.class), tipo);
    }

    @Override
    public List<Notificacion> findByLeida(Boolean leida) {
        String sql = "SELECT * FROM notificacion WHERE leida = ? ORDER BY fecha_envio DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Notificacion.class), leida);
    }

    @Override
    public List<Notificacion> findNotificacionesNoLeidasByUsuario(Long usuarioId) {
        String sql = "SELECT * FROM notificacion WHERE usuario_id = ? AND leida = false " +
                "ORDER BY fecha_envio DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Notificacion.class), usuarioId);
    }

    @Override
    public List<Notificacion> findNotificacionesRecientesByUsuario(Long usuarioId, int limite) {
        String sql = "SELECT * FROM notificacion WHERE usuario_id = ? " +
                "ORDER BY fecha_envio DESC LIMIT ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Notificacion.class), usuarioId, limite);
    }

    @Override
    public int marcarComoLeida(Long id) {
        String sql = "UPDATE notificacion SET leida = true WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int marcarTodasComoLeidasByUsuario(Long usuarioId) {
        String sql = "UPDATE notificacion SET leida = true WHERE usuario_id = ? AND leida = false";
        return jdbcTemplate.update(sql, usuarioId);
    }

    @Override
    public int countNotificacionesNoLeidasByUsuario(Long usuarioId) {
        String sql = "SELECT COUNT(*) FROM notificacion WHERE usuario_id = ? AND leida = false";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, usuarioId);
        return count != null ? count : 0;
    }
}