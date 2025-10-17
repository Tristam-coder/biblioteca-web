package biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.dao.UsuarioDAO;
import biblioteca.dto.UsuarioDTO;
import biblioteca.models.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller REST para manejar operaciones de Usuario
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    /**
     * GET /api/usuarios - Obtener todos los usuarios
     */
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDAO.findAll();
            List<UsuarioDTO> usuarioDTOs = usuarios.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(usuarioDTOs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * GET /api/usuarios/{id} - Obtener un usuario por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        try {
            Optional<Usuario> usuario = usuarioDAO.findById(id);
            return usuario.map(value -> ResponseEntity.ok(convertToDTO(value)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * POST /api/usuarios - Crear un nuevo usuario
     */
    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody Usuario usuario) {
        try {
            // Verificar si ya existe un usuario con el mismo DNI o email
            if (usuarioDAO.existsByDni(usuario.getDni())) {
                return ResponseEntity.badRequest().build(); // DNI ya existe
            }
            if (usuarioDAO.existsByEmail(usuario.getEmail())) {
                return ResponseEntity.badRequest().build(); // Email ya existe
            }

            Usuario savedUsuario = usuarioDAO.save(usuario);
            UsuarioDTO usuarioDTO = convertToDTO(savedUsuario);
            return ResponseEntity.ok(usuarioDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * PUT /api/usuarios/{id} - Actualizar un usuario existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Optional<Usuario> existingUsuario = usuarioDAO.findById(id);
            if (existingUsuario.isPresent()) {
                usuario.setId(id); // Asegurar que se actualice el usuario correcto
                Usuario updatedUsuario = usuarioDAO.update(usuario);
                UsuarioDTO usuarioDTO = convertToDTO(updatedUsuario);
                return ResponseEntity.ok(usuarioDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * DELETE /api/usuarios/{id} - Eliminar un usuario
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        try {
            usuarioDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * GET /api/usuarios/dni/{dni} - Buscar usuario por DNI
     */
    @GetMapping("/dni/{dni}")
    public ResponseEntity<UsuarioDTO> getUsuarioByDni(@PathVariable String dni) {
        try {
            Optional<Usuario> usuario = usuarioDAO.findByDni(dni);
            return usuario.map(value -> ResponseEntity.ok(convertToDTO(value)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * GET /api/usuarios/email/{email} - Buscar usuario por email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> getUsuarioByEmail(@PathVariable String email) {
        try {
            Optional<Usuario> usuario = usuarioDAO.findByEmail(email);
            return usuario.map(value -> ResponseEntity.ok(convertToDTO(value)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * GET /api/usuarios/activos - Obtener usuarios activos
     */
    @GetMapping("/activos")
    public ResponseEntity<List<UsuarioDTO>> getUsuariosActivos() {
        try {
            List<Usuario> usuarios = usuarioDAO.findByEstado("ACTIVO");
            List<UsuarioDTO> usuarioDTOs = usuarios.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(usuarioDTOs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * GET /api/usuarios/tipo/{tipo} - Obtener usuarios por tipo
     */
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<UsuarioDTO>> getUsuariosByTipo(@PathVariable String tipo) {
        try {
            List<Usuario> usuarios = usuarioDAO.findByTipoUsuario(tipo.toUpperCase());
            List<UsuarioDTO> usuarioDTOs = usuarios.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(usuarioDTOs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Método auxiliar para convertir Usuario a UsuarioDTO
     */
    private UsuarioDTO convertToDTO(Usuario usuario) {
        // Convertir el estado de String a boolean
        boolean estadoActivo = "ACTIVO".equalsIgnoreCase(usuario.getEstado());

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getDni(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getDireccion(),
                usuario.getFechaRegistro(),
                estadoActivo
        );
    }
    /**
     * POST /api/usuarios/login - Iniciar sesión
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        try {
            // Buscar el usuario por email
            Optional<Usuario> usuarioOpt = usuarioDAO.findByEmail(loginRequest.getEmail());

            if (usuarioOpt.isEmpty()) {
                // No existe el usuario
                return ResponseEntity.status(401).body("Usuario no encontrado");
            }

            Usuario usuario = usuarioOpt.get();

            // Verificar contraseña
            if (!usuario.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.status(401).body("Contraseña incorrecta");
            }

            // Si todo bien, devolvemos los datos del usuario (sin contraseña)
            return ResponseEntity.ok(convertToDTO(usuario));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }

}