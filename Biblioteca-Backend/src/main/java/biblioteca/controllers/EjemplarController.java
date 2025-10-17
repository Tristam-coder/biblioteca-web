package biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.dao.EjemplarDAO;
import biblioteca.models.Ejemplar;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ejemplares")
public class EjemplarController {

    @Autowired
    private EjemplarDAO ejemplarDAO;

    @GetMapping
    public ResponseEntity<List<Ejemplar>> getAllEjemplares() {
        try {
            List<Ejemplar> ejemplares = ejemplarDAO.findAll();
            return ResponseEntity.ok(ejemplares);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ejemplar> getEjemplarById(@PathVariable Long id) {
        try {
            Optional<Ejemplar> ejemplar = ejemplarDAO.findById(id);
            return ejemplar.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Ejemplar> createEjemplar(@RequestBody Ejemplar ejemplar) {
        try {
            // Verificar si ya existe un ejemplar con el mismo código de barras
            if (ejemplarDAO.existsByCodigoBarras(ejemplar.getCodigoBarras())) {
                return ResponseEntity.badRequest().build();
            }

            Ejemplar savedEjemplar = ejemplarDAO.save(ejemplar);
            return ResponseEntity.ok(savedEjemplar);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ejemplar> updateEjemplar(@PathVariable Long id, @RequestBody Ejemplar ejemplar) {
        try {
            Optional<Ejemplar> existingEjemplar = ejemplarDAO.findById(id);
            if (existingEjemplar.isPresent()) {
                ejemplar.setId(id);
                Ejemplar updatedEjemplar = ejemplarDAO.update(ejemplar);
                return ResponseEntity.ok(updatedEjemplar);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjemplar(@PathVariable Long id) {
        try {
            ejemplarDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/obra/{obraId}")
    public ResponseEntity<List<Ejemplar>> getEjemplaresByObra(@PathVariable Long obraId) {
        try {
            List<Ejemplar> ejemplares = ejemplarDAO.findByObraId(obraId);
            return ResponseEntity.ok(ejemplares);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Ejemplar>> getEjemplaresByEstado(@PathVariable String estado) {
        try {
            List<Ejemplar> ejemplares = ejemplarDAO.findByEstado(estado);
            return ResponseEntity.ok(ejemplares);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/codigo/{codigoBarras}")
    public ResponseEntity<Ejemplar> getEjemplarByCodigoBarras(@PathVariable String codigoBarras) {
        try {
            Optional<Ejemplar> ejemplar = ejemplarDAO.findByCodigoBarras(codigoBarras);
            return ejemplar.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Ejemplar>> getEjemplaresDisponibles() {
        try {
            List<Ejemplar> ejemplares = ejemplarDAO.findEjemplaresDisponibles();
            return ResponseEntity.ok(ejemplares);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/prestados")
    public ResponseEntity<List<Ejemplar>> getEjemplaresPrestados() {
        try {
            List<Ejemplar> ejemplares = ejemplarDAO.findEjemplaresPrestados();
            return ResponseEntity.ok(ejemplares);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/obra/{obraId}/estado/{estado}/count")
    public ResponseEntity<Integer> countEjemplaresByObraAndEstado(@PathVariable Long obraId,
                                                                  @PathVariable String estado) {
        try {
            int count = ejemplarDAO.countByObraIdAndEstado(obraId, estado);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}