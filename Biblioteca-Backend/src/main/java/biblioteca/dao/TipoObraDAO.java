package biblioteca.dao;

import biblioteca.models.TipoObra;
import java.util.List;
import java.util.Optional;

public interface TipoObraDAO {

    // CRUD básico
    List<TipoObra> findAll();
    Optional<TipoObra> findById(Long id);
    Optional<TipoObra> findByNombre(String nombre);
    TipoObra save(TipoObra tipoObra);
    TipoObra update(TipoObra tipoObra);
    void deleteById(Long id);

    // Verificación
    boolean existsByNombre(String nombre);
}