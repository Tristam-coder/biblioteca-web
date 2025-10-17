package biblioteca.dao;

import biblioteca.models.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioDAO {

    // CRUD básico
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    Usuario update(Usuario usuario);
    void deleteById(Long id);

    // Búsquedas específicas
    Optional<Usuario> findByDni(String dni);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByEstado(String estado);
    List<Usuario> findByTipoUsuario(String tipoUsuario);

    // Verificaciones
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
}