package biblioteca.dao;

import biblioteca.models.Prestamo;
import java.util.List;
import java.util.Optional;

public interface PrestamoDAO {

    // CRUD básico
    List<Prestamo> findAll();
    Optional<Prestamo> findById(Long id);
    Prestamo save(Prestamo prestamo);
    Prestamo update(Prestamo prestamo);
    void deleteById(Long id);

    // Búsquedas específicas
    List<Prestamo> findByUsuarioId(Long usuarioId);
    List<Prestamo> findByEjemplarId(Long ejemplarId);
    List<Prestamo> findByEstado(String estado);

    // Búsquedas avanzadas
    List<Prestamo> findPrestamosActivos();
    List<Prestamo> findPrestamosVencidos();
    List<Prestamo> findPrestamosConMulta();
    List<Prestamo> findPrestamosByUsuarioAndEstado(Long usuarioId, String estado);

    // Estadísticas
    int countPrestamosActivosByUsuario(Long usuarioId);
    boolean tienePrestamosVencidos(Long usuarioId);
}