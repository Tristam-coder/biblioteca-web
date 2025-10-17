package biblioteca.dao;

import biblioteca.models.Historial;
import java.util.List;
import java.util.Optional;

public interface HistorialDAO {

    // CRUD básico
    List<Historial> findAll();
    Optional<Historial> findById(Long id);
    Historial save(Historial historial);

    // Búsquedas específicas
    List<Historial> findByUsuarioId(Long usuarioId);
    List<Historial> findByAccion(String accion);
    List<Historial> findByTablaAfectada(String tablaAfectada);

    // Búsquedas avanzadas
    List<Historial> findHistorialReciente(int limite);
    List<Historial> findByUsuarioAndAccion(Long usuarioId, String accion);

    // Estadísticas
    int countByUsuarioId(Long usuarioId);
    List<Object[]> getAccionesMasFrecuentes();
}