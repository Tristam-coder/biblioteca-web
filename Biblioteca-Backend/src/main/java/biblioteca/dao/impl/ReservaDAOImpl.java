package biblioteca.dao.impl;

import biblioteca.dao.ReservaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import biblioteca.models.Reserva;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservaDAOImpl implements ReservaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Reserva> findAll() {
        String sql = "SELECT * FROM reserva ORDER BY fecha_reserva DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Reserva.class));
    }

    @Override
    public Optional<Reserva> findById(Long id) {
        String sql = "SELECT * FROM reserva WHERE id = ?";
        try {
            Reserva reserva = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Reserva.class), id);
            return Optional.ofNullable(reserva);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Reserva save(Reserva reserva) {
        // Obtener posición en cola
        int posicionCola = getPosicionEnCola(reserva.getObraId()) + 1;
        reserva.setPosicionCola(posicionCola);

        String sql = "INSERT INTO reserva (usuario_id, obra_id, fecha_reserva, " +
                "fecha_disponibilidad_estimada, estado, posicion_cola, notificado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                reserva.getUsuarioId(),
                reserva.getObraId(),
                reserva.getFechaReserva(),
                reserva.getFechaDisponibilidadEstimada(),
                reserva.getEstado(),
                reserva.getPosicionCola(),
                reserva.getNotificado());
        return reserva;
    }

    @Override
    public Reserva update(Reserva reserva) {
        String sql = "UPDATE reserva SET usuario_id = ?, obra_id = ?, fecha_reserva = ?, " +
                "fecha_disponibilidad_estimada = ?, estado = ?, posicion_cola = ?, " +
                "notificado = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                reserva.getUsuarioId(),
                reserva.getObraId(),
                reserva.getFechaReserva(),
                reserva.getFechaDisponibilidadEstimada(),
                reserva.getEstado(),
                reserva.getPosicionCola(),
                reserva.getNotificado(),
                reserva.getId());
        return reserva;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM reserva WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Reserva> findByUsuarioId(Long usuarioId) {
        String sql = "SELECT * FROM reserva WHERE usuario_id = ? ORDER BY fecha_reserva DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Reserva.class), usuarioId);
    }

    @Override
    public List<Reserva> findByObraId(Long obraId) {
        String sql = "SELECT * FROM reserva WHERE obra_id = ? ORDER BY posicion_cola ASC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Reserva.class), obraId);
    }

    @Override
    public List<Reserva> findByEstado(String estado) {
        String sql = "SELECT * FROM reserva WHERE estado = ? ORDER BY fecha_reserva DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Reserva.class), estado);
    }

    @Override
    public List<Reserva> findReservasActivas() {
        String sql = "SELECT * FROM reserva WHERE estado IN ('PENDIENTE', 'ACTIVA') ORDER BY fecha_reserva DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Reserva.class));
    }

    @Override
    public List<Reserva> findReservasPendientes() {
        String sql = "SELECT * FROM reserva WHERE estado = 'PENDIENTE' ORDER BY posicion_cola ASC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Reserva.class));
    }

    @Override
    public List<Reserva> findReservasPorNotificar() {
        String sql = "SELECT * FROM reserva WHERE estado = 'ACTIVA' AND notificado = false ORDER BY fecha_reserva";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Reserva.class));
    }

    @Override
    public List<Reserva> findReservasByUsuarioAndEstado(Long usuarioId, String estado) {
        String sql = "SELECT * FROM reserva WHERE usuario_id = ? AND estado = ? ORDER BY fecha_reserva DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Reserva.class), usuarioId, estado);
    }

    @Override
    public int getPosicionEnCola(Long obraId) {
        String sql = "SELECT COUNT(*) FROM reserva WHERE obra_id = ? AND estado IN ('PENDIENTE', 'ACTIVA')";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, obraId);
        return count != null ? count : 0;
    }

    @Override
    public int countReservasActivasByObra(Long obraId) {
        String sql = "SELECT COUNT(*) FROM reserva WHERE obra_id = ? AND estado IN ('PENDIENTE', 'ACTIVA')";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, obraId);
        return count != null ? count : 0;
    }

    @Override
    public Optional<Reserva> findSiguienteReservaEnCola(Long obraId) {
        String sql = "SELECT * FROM reserva WHERE obra_id = ? AND estado = 'PENDIENTE' " +
                "ORDER BY posicion_cola ASC LIMIT 1";
        try {
            Reserva reserva = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Reserva.class), obraId);
            return Optional.ofNullable(reserva);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsReservaActivaByUsuarioAndObra(Long usuarioId, Long obraId) {
        String sql = "SELECT COUNT(*) FROM reserva WHERE usuario_id = ? AND obra_id = ? " +
                "AND estado IN ('PENDIENTE', 'ACTIVA')";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, usuarioId, obraId);
        return count != null && count > 0;
    }

    @Override
    public int countReservasActivasByUsuario(Long usuarioId) {
        String sql = "SELECT COUNT(*) FROM reserva WHERE usuario_id = ? AND estado IN ('PENDIENTE', 'ACTIVA')";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, usuarioId);
        return count != null ? count : 0;
    }
}