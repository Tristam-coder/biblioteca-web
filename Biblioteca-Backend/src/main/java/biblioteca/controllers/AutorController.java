package biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.dao.AutorDAO;
import biblioteca.models.Autor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorDAO autorDAO;

    @GetMapping
    public ResponseEntity<List<Autor>> getAllAutores() {
        try {
            List<Autor> autores = autorDAO.findAll();
            return ResponseEntity.ok(autores);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAutorById(@PathVariable Long id) {
        try {
            Optional<Autor> autor = autorDAO.findById(id);
            return autor.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Autor> createAutor(@RequestBody Autor autor) {
        try {
            Autor savedAutor = autorDAO.save(autor);
            return ResponseEntity.ok(savedAutor);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> updateAutor(@PathVariable Long id, @RequestBody Autor autor) {
        try {
            Optional<Autor> existingAutor = autorDAO.findById(id);
            if (existingAutor.isPresent()) {
                autor.setId(id);
                Autor updatedAutor = autorDAO.update(autor);
                return ResponseEntity.ok(updatedAutor);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        try {
            autorDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<List<Autor>> getAutoresByNombre(@PathVariable String nombre) {
        try {
            List<Autor> autores = autorDAO.findByNombre(nombre);
            return ResponseEntity.ok(autores);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/buscar/apellido/{apellido}")
    public ResponseEntity<List<Autor>> getAutoresByApellido(@PathVariable String apellido) {
        try {
            List<Autor> autores = autorDAO.findByApellido(apellido);
            return ResponseEntity.ok(autores);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/buscar/nacionalidad/{nacionalidad}")
    public ResponseEntity<List<Autor>> getAutoresByNacionalidad(@PathVariable String nacionalidad) {
        try {
            List<Autor> autores = autorDAO.findByNacionalidad(nacionalidad);
            return ResponseEntity.ok(autores);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/buscar/{texto}")
    public ResponseEntity<List<Autor>> searchAutores(@PathVariable String texto) {
        try {
            List<Autor> autores = autorDAO.searchByNombre(texto);
            return ResponseEntity.ok(autores);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}