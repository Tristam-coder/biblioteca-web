package biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.dao.ObraDAO;
import biblioteca.models.Obra;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/obras")
public class ObraController {

    @Autowired
    private ObraDAO obraDAO;

    @GetMapping
    public ResponseEntity<List<Obra>> getAllObras() {
        try {
            List<Obra> obras = obraDAO.findAll();
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> getObraById(@PathVariable Long id) {
        try {
            Optional<Obra> obra = obraDAO.findById(id);
            return obra.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Obra> createObra(@RequestBody Obra obra) {
        try {
            // Verificar si ya existe una obra con el mismo ISBN/ISSN
            if (obra.getIsbnIssn() != null && obraDAO.existsByIsbnIssn(obra.getIsbnIssn())) {
                return ResponseEntity.badRequest().build();
            }

            Obra savedObra = obraDAO.save(obra);
            return ResponseEntity.ok(savedObra);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obra> updateObra(@PathVariable Long id, @RequestBody Obra obra) {
        try {
            Optional<Obra> existingObra = obraDAO.findById(id);
            if (existingObra.isPresent()) {
                obra.setId(id);
                Obra updatedObra = obraDAO.update(obra);
                return ResponseEntity.ok(updatedObra);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObra(@PathVariable Long id) {
        try {
            obraDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/buscar/titulo/{titulo}")
    public ResponseEntity<List<Obra>> getObrasByTitulo(@PathVariable String titulo) {
        try {
            List<Obra> obras = obraDAO.findByTitulo(titulo);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/buscar/texto/{texto}")
    public ResponseEntity<List<Obra>> searchObras(@PathVariable String texto) {
        try {
            List<Obra> obras = obraDAO.searchByTitulo(texto);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/tipo/{tipoObraId}")
    public ResponseEntity<List<Obra>> getObrasByTipo(@PathVariable Long tipoObraId) {
        try {
            List<Obra> obras = obraDAO.findByTipoObraId(tipoObraId);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/editorial/{editorialId}")
    public ResponseEntity<List<Obra>> getObrasByEditorial(@PathVariable Long editorialId) {
        try {
            List<Obra> obras = obraDAO.findByEditorialId(editorialId);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Obra>> getObrasByEstado(@PathVariable String estado) {
        try {
            List<Obra> obras = obraDAO.findByEstado(estado);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/area/{areaTematica}")
    public ResponseEntity<List<Obra>> getObrasByAreaTematica(@PathVariable String areaTematica) {
        try {
            List<Obra> obras = obraDAO.findByAreaTematica(areaTematica);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/isbn/{isbnIssn}")
    public ResponseEntity<Obra> getObraByIsbn(@PathVariable String isbnIssn) {
        try {
            Optional<Obra> obra = obraDAO.findByIsbnIssn(isbnIssn);
            return obra.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/anio/{anio}")
    public ResponseEntity<List<Obra>> getObrasByAnio(@PathVariable Integer anio) {
        try {
            List<Obra> obras = obraDAO.findByAnioPublicacion(anio);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/anio/{anioInicio}/{anioFin}")
    public ResponseEntity<List<Obra>> getObrasByRangoAnio(@PathVariable Integer anioInicio,
                                                          @PathVariable Integer anioFin) {
        try {
            List<Obra> obras = obraDAO.findByAnioPublicacionBetween(anioInicio, anioFin);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}