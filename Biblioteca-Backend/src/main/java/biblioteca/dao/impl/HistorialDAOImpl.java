package biblioteca.dao.impl;

import biblioteca.dao.HistorialDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import biblioteca.models.Historial;

import java.util.List;
import java.util.Optional;

@Repository
public class HistorialDAOImpl implements HistorialDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Historial> findAll() {
        String sql = "SELECT * FROM historial ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Historial.class));
    }

    @Override
    public Optional<Historial> findById(Long id) {
        String sql = "SELECT * FROM historial WHERE id = ?";
        try {
            Historial historial = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Historial.class), id);
            return Optional.ofNullable(historial);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Historial save(Historial historial) {
        String sql = "INSERT INTO historial (usuario_id, accion, tabla_afectada, registro_id, detalles) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                historial.getUsuarioId(),
                historial.getAccion(),
                historial.getTablaAfectada(),
                historial.getRegistroId(),
                historial.getDetalles());
        return historial;
    }

    @Override
    public List<Historial> findByUsuarioId(Long usuarioId) {
        String sql = "SELECT * FROM historial WHERE usuario_id = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Historial.class), usuarioId);
    }

    @Override
    public List<Historial> findByAccion(String accion) {
        String sql = "SELECT * FROM historial WHERE accion = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Historial.class), accion);
    }

    @Override
    public List<Historial> findByTablaAfectada(String tablaAfectada) {
        String sql = "SELECT * FROM historial WHERE tabla_afectada = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Historial.class), tablaAfectada);
    }

    @Override
    public List<Historial> findHistorialReciente(int limite) {
        String sql = "SELECT * FROM historial ORDER BY created_at DESC LIMIT ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Historial.class), limite);
    }

    @Override
    public List<Historial> findByUsuarioAndAccion(Long usuarioId, String accion) {
        String sql = "SELECT * FROM historial WHERE usuario_id = ? AND accion = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Historial.class), usuarioId, accion);
    }

    @Override
    public int countByUsuarioId(Long usuarioId) {
        String sql = "SELECT COUNT(*) FROM historial WHERE usuario_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, usuarioId);
        return count != null ? count : 0;
    }

    @Override
    public List<Object[]> getAccionesMasFrecuentes() {
        String sql = "SELECT accion, COUNT(*) as cantidad FROM historial " +
                "GROUP BY accion ORDER BY cantidad DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Object[]{rs.getString("accion"), rs.getInt("cantidad")});
    }
}