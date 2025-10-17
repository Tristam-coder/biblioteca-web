package biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.dao.TipoObraDAO;
import biblioteca.models.TipoObra;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipos-obra")
public class TipoObraController {

    @Autowired
    private TipoObraDAO tipoObraDAO;

    @GetMapping
    public ResponseEntity<List<TipoObra>> getAllTiposObra() {
        try {
            List<TipoObra> tiposObra = tipoObraDAO.findAll();
            return ResponseEntity.ok(tiposObra);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoObra> getTipoObraById(@PathVariable Long id) {
        try {
            Optional<TipoObra> tipoObra = tipoObraDAO.findById(id);
            return tipoObra.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<TipoObra> getTipoObraByNombre(@PathVariable String nombre) {
        try {
            Optional<TipoObra> tipoObra = tipoObraDAO.findByNombre(nombre);
            return tipoObra.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoObra> createTipoObra(@RequestBody TipoObra tipoObra) {
        try {
            // Verificar si ya existe un tipo de obra con el mismo nombre
            if (tipoObraDAO.existsByNombre(tipoObra.getNombre())) {
                return ResponseEntity.badRequest().build();
            }

            TipoObra savedTipoObra = tipoObraDAO.save(tipoObra);
            return ResponseEntity.ok(savedTipoObra);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoObra> updateTipoObra(@PathVariable Long id, @RequestBody TipoObra tipoObra) {
        try {
            Optional<TipoObra> existingTipoObra = tipoObraDAO.findById(id);
            if (existingTipoObra.isPresent()) {
                tipoObra.setId(id);
                TipoObra updatedTipoObra = tipoObraDAO.update(tipoObra);
                return ResponseEntity.ok(updatedTipoObra);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoObra(@PathVariable Long id) {
        try {
            tipoObraDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}