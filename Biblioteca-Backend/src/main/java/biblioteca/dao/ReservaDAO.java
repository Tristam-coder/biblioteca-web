package biblioteca.dao;

import biblioteca.models.Reserva;
import java.util.List;
import java.util.Optional;

public interface ReservaDAO {

    // CRUD básico
    List<Reserva> findAll();
    Optional<Reserva> findById(Long id);
    Reserva save(Reserva reserva);
    Reserva update(Reserva reserva);
    void deleteById(Long id);

    // Búsquedas específicas
    List<Reserva> findByUsuarioId(Long usuarioId);
    List<Reserva> findByObraId(Long obraId);
    List<Reserva> findByEstado(String estado);

    // Búsquedas avanzadas
    List<Reserva> findReservasActivas();
    List<Reserva> findReservasPendientes();
    List<Reserva> findReservasPorNotificar();
    List<Reserva> findReservasByUsuarioAndEstado(Long usuarioId, String estado);

    // Operaciones de cola de reservas
    int getPosicionEnCola(Long obraId);
    int countReservasActivasByObra(Long obraId);
    Optional<Reserva> findSiguienteReservaEnCola(Long obraId);

    // Verificaciones
    boolean existsReservaActivaByUsuarioAndObra(Long usuarioId, Long obraId);
    int countReservasActivasByUsuario(Long usuarioId);
}