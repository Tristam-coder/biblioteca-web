package biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.dao.ObraAutorDAO;
import biblioteca.models.ObraAutor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/obra-autor")
public class ObraAutorController {

    @Autowired
    private ObraAutorDAO obraAutorDAO;

    @GetMapping
    public ResponseEntity<List<ObraAutor>> getAllObraAutor() {
        try {
            List<ObraAutor> obraAutores = obraAutorDAO.findAll();
            return ResponseEntity.ok(obraAutores);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraAutor> getObraAutorById(@PathVariable Long id) {
        try {
            Optional<ObraAutor> obraAutor = obraAutorDAO.findById(id);
            return obraAutor.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ObraAutor> createObraAutor(@RequestBody ObraAutor obraAutor) {
        try {
            // Verificar si ya existe la relación
            if (obraAutorDAO.existsByObraAndAutor(obraAutor.getObraId(), obraAutor.getAutorId())) {
                return ResponseEntity.badRequest().build();
            }

            ObraAutor savedObraAutor = obraAutorDAO.save(obraAutor);
            return ResponseEntity.ok(savedObraAutor);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraAutor> updateObraAutor(@PathVariable Long id, @RequestBody ObraAutor obraAutor) {
        try {
            Optional<ObraAutor> existingObraAutor = obraAutorDAO.findById(id);
            if (existingObraAutor.isPresent()) {
                obraAutor.setId(id);
                ObraAutor updatedObraAutor = obraAutorDAO.update(obraAutor);
                return ResponseEntity.ok(updatedObraAutor);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObraAutor(@PathVariable Long id) {
        try {
            obraAutorDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/obra/{obraId}")
    public ResponseEntity<List<ObraAutor>> getAutoresByObra(@PathVariable Long obraId) {
        try {
            List<ObraAutor> obraAutores = obraAutorDAO.findByObraId(obraId);
            return ResponseEntity.ok(obraAutores);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<ObraAutor>> getObrasByAutor(@PathVariable Long autorId) {
        try {
            List<ObraAutor> obraAutores = obraAutorDAO.findByAutorId(autorId);
            return ResponseEntity.ok(obraAutores);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/obra/{obraId}/autor/{autorId}")
    public ResponseEntity<ObraAutor> getObraAutorByObraAndAutor(@PathVariable Long obraId,
                                                                @PathVariable Long autorId) {
        try {
            Optional<ObraAutor> obraAutor = obraAutorDAO.findByObraAndAutor(obraId, autorId);
            return obraAutor.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/obra/{obraId}")
    public ResponseEntity<Void> deleteAutoresByObra(@PathVariable Long obraId) {
        try {
            obraAutorDAO.deleteByObraId(obraId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/autor/{autorId}")
    public ResponseEntity<Void> deleteObrasByAutor(@PathVariable Long autorId) {
        try {
            obraAutorDAO.deleteByAutorId(autorId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}