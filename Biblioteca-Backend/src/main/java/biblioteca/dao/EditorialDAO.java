package biblioteca.dao;

import biblioteca.models.Editorial;
import java.util.List;
import java.util.Optional;

public interface EditorialDAO {

    // CRUD básico
    List<Editorial> findAll();
    Optional<Editorial> findById(Long id);
    Editorial save(Editorial editorial);
    Editorial update(Editorial editorial);
    void deleteById(Long id);

    // Búsquedas específicas
    List<Editorial> findByNombre(String nombre);
    List<Editorial> findByPais(String pais);
    Optional<Editorial> findByEmail(String email);

    // Verificaciones
    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);
}