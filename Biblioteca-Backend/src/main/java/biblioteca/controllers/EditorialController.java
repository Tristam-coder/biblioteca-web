package biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.dao.EditorialDAO;
import biblioteca.models.Editorial;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/editoriales")
public class EditorialController {

    @Autowired
    private EditorialDAO editorialDAO;

    @GetMapping
    public ResponseEntity<List<Editorial>> getAllEditoriales() {
        try {
            List<Editorial> editoriales = editorialDAO.findAll();
            return ResponseEntity.ok(editoriales);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editorial> getEditorialById(@PathVariable Long id) {
        try {
            Optional<Editorial> editorial = editorialDAO.findById(id);
            return editorial.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Editorial> createEditorial(@RequestBody Editorial editorial) {
        try {
            // Verificar si ya existe una editorial con el mismo nombre o email
            if (editorialDAO.existsByNombre(editorial.getNombre())) {
                return ResponseEntity.badRequest().build();
            }
            if (editorialDAO.existsByEmail(editorial.getEmail())) {
                return ResponseEntity.badRequest().build();
            }

            Editorial savedEditorial = editorialDAO.save(editorial);
            return ResponseEntity.ok(savedEditorial);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editorial> updateEditorial(@PathVariable Long id, @RequestBody Editorial editorial) {
        try {
            Optional<Editorial> existingEditorial = editorialDAO.findById(id);
            if (existingEditorial.isPresent()) {
                editorial.setId(id);
                Editorial updatedEditorial = editorialDAO.update(editorial);
                return ResponseEntity.ok(updatedEditorial);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEditorial(@PathVariable Long id) {
        try {
            editorialDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<List<Editorial>> getEditorialesByNombre(@PathVariable String nombre) {
        try {
            List<Editorial> editoriales = editorialDAO.findByNombre(nombre);
            return ResponseEntity.ok(editoriales);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/buscar/pais/{pais}")
    public ResponseEntity<List<Editorial>> getEditorialesByPais(@PathVariable String pais) {
        try {
            List<Editorial> editoriales = editorialDAO.findByPais(pais);
            return ResponseEntity.ok(editoriales);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/buscar/email/{email}")
    public ResponseEntity<Editorial> getEditorialByEmail(@PathVariable String email) {
        try {
            Optional<Editorial> editorial = editorialDAO.findByEmail(email);
            return editorial.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}