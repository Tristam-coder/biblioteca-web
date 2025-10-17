package biblioteca.dao;

import biblioteca.models.Ejemplar;
import java.util.List;
import java.util.Optional;

public interface EjemplarDAO {

    // CRUD básico
    List<Ejemplar> findAll();
    Optional<Ejemplar> findById(Long id);
    Ejemplar save(Ejemplar ejemplar);
    Ejemplar update(Ejemplar ejemplar);
    void deleteById(Long id);

    // Búsquedas específicas
    List<Ejemplar> findByObraId(Long obraId);
    List<Ejemplar> findByEstado(String estado);
    Optional<Ejemplar> findByCodigoBarras(String codigoBarras);

    // Búsquedas avanzadas
    List<Ejemplar> findEjemplaresDisponibles();
    List<Ejemplar> findEjemplaresPrestados();

    // Verificaciones
    boolean existsByCodigoBarras(String codigoBarras);
    int countByObraIdAndEstado(Long obraId, String estado);
}