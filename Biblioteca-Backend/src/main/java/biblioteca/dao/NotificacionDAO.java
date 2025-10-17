package biblioteca.dao;

import biblioteca.models.Notificacion;
import java.util.List;
import java.util.Optional;

public interface NotificacionDAO {

    // CRUD básico
    List<Notificacion> findAll();
    Optional<Notificacion> findById(Long id);
    Notificacion save(Notificacion notificacion);
    Notificacion update(Notificacion notificacion);
    void deleteById(Long id);

    // Búsquedas específicas
    List<Notificacion> findByUsuarioId(Long usuarioId);
    List<Notificacion> findByTipo(String tipo);
    List<Notificacion> findByLeida(Boolean leida);

    // Búsquedas avanzadas
    List<Notificacion> findNotificacionesNoLeidasByUsuario(Long usuarioId);
    List<Notificacion> findNotificacionesRecientesByUsuario(Long usuarioId, int limite);

    // Operaciones de estado
    int marcarComoLeida(Long id);
    int marcarTodasComoLeidasByUsuario(Long usuarioId);

    // Estadísticas
    int countNotificacionesNoLeidasByUsuario(Long usuarioId);
}