package biblioteca.dao;

import biblioteca.models.ObraAutor;
import java.util.List;
import java.util.Optional;

public interface ObraAutorDAO {

    // CRUD básico
    List<ObraAutor> findAll();
    Optional<ObraAutor> findById(Long id);
    ObraAutor save(ObraAutor obraAutor);
    ObraAutor update(ObraAutor obraAutor);
    void deleteById(Long id);

    // Búsquedas específicas
    List<ObraAutor> findByObraId(Long obraId);
    List<ObraAutor> findByAutorId(Long autorId);
    Optional<ObraAutor> findByObraAndAutor(Long obraId, Long autorId);

    // Operaciones de relación
    void deleteByObraId(Long obraId);
    void deleteByAutorId(Long autorId);

    // Verificación
    boolean existsByObraAndAutor(Long obraId, Long autorId);
}