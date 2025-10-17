package biblioteca.dao;

import biblioteca.models.Autor;
import java.util.List;
import java.util.Optional;

public interface AutorDAO {

    // CRUD básico
    List<Autor> findAll();
    Optional<Autor> findById(Long id);
    Autor save(Autor autor);
    Autor update(Autor autor);
    void deleteById(Long id);

    // Búsquedas específicas
    List<Autor> findByNombre(String nombre);
    List<Autor> findByApellido(String apellido);
    List<Autor> findByNacionalidad(String nacionalidad);
    List<Autor> findByNombreCompleto(String nombre, String apellido);

    // Búsqueda por texto en nombre o apellido
    List<Autor> searchByNombre(String texto);
}