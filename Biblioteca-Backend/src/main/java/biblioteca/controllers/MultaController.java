package biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.dao.MultaDAO;
import biblioteca.models.Multa;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/multas")
public class MultaController {

    @Autowired
    private MultaDAO multaDAO;

    @GetMapping
    public ResponseEntity<List<Multa>> getAllMultas() {
        try {
            List<Multa> multas = multaDAO.findAll();
            return ResponseEntity.ok(multas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Multa> getMultaById(@PathVariable Long id) {
        try {
            Optional<Multa> multa = multaDAO.findById(id);
            return multa.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Multa> createMulta(@RequestBody Multa multa) {
        try {
            Multa savedMulta = multaDAO.save(multa);
            return ResponseEntity.ok(savedMulta);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Multa> updateMulta(@PathVariable Long id, @RequestBody Multa multa) {
        try {
            Optional<Multa> existingMulta = multaDAO.findById(id);
            if (existingMulta.isPresent()) {
                multa.setId(id);
                Multa updatedMulta = multaDAO.update(multa);
                return ResponseEntity.ok(updatedMulta);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMulta(@PathVariable Long id) {
        try {
            multaDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Multa>> getMultasByUsuario(@PathVariable Long usuarioId) {
        try {
            List<Multa> multas = multaDAO.findByUsuarioId(usuarioId);
            return ResponseEntity.ok(multas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/prestamo/{prestamoId}")
    public ResponseEntity<List<Multa>> getMultasByPrestamo(@PathVariable Long prestamoId) {
        try {
            List<Multa> multas = multaDAO.findByPrestamoId(prestamoId);
            return ResponseEntity.ok(multas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Multa>> getMultasByEstado(@PathVariable String estado) {
        try {
            List<Multa> multas = multaDAO.findByEstado(estado);
            return ResponseEntity.ok(multas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<Multa>> getMultasPendientes() {
        try {
            List<Multa> multas = multaDAO.findMultasPendientes();
            return ResponseEntity.ok(multas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/pagadas")
    public ResponseEntity<List<Multa>> getMultasPagadas() {
        try {
            List<Multa> multas = multaDAO.findMultasPagadas();
            return ResponseEntity.ok(multas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/total-pendiente")
    public ResponseEntity<BigDecimal> getTotalMultasPendientesByUsuario(@PathVariable Long usuarioId) {
        try {
            BigDecimal total = multaDAO.getTotalMultasPendientesByUsuario(usuarioId);
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/tiene-pendientes")
    public ResponseEntity<Boolean> tieneMultasPendientes(@PathVariable Long usuarioId) {
        try {
            boolean tieneMultas = multaDAO.tieneMultasPendientes(usuarioId);
            return ResponseEntity.ok(tieneMultas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/estadisticas/total-pagadas")
    public ResponseEntity<BigDecimal> getTotalMultasPagadas() {
        try {
            BigDecimal total = multaDAO.getTotalMultasPagadas();
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/estadisticas/total-pendientes")
    public ResponseEntity<BigDecimal> getTotalMultasPendientes() {
        try {
            BigDecimal total = multaDAO.getTotalMultasPendientes();
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}