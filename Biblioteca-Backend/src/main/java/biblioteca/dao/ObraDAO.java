package biblioteca.dao;

import biblioteca.models.Obra;
import java.util.List;
import java.util.Optional;

public interface ObraDAO {

    // CRUD básico
    List<Obra> findAll();
    Optional<Obra> findById(Long id);
    Obra save(Obra obra);
    Obra update(Obra obra);
    void deleteById(Long id);

    // Búsquedas específicas
    List<Obra> findByTitulo(String titulo);
    List<Obra> findByTipoObraId(Long tipoObraId);
    List<Obra> findByEditorialId(Long editorialId);
    List<Obra> findByEstado(String estado);
    List<Obra> findByAreaTematica(String areaTematica);
    Optional<Obra> findByIsbnIssn(String isbnIssn);

    // Búsquedas avanzadas
    List<Obra> searchByTitulo(String texto);
    List<Obra> findByAnioPublicacion(Integer anio);
    List<Obra> findByAnioPublicacionBetween(Integer anioInicio, Integer anioFin);

    // Verificaciones
    boolean existsByIsbnIssn(String isbnIssn);
}