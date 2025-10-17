package biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.dao.PrestamoDAO;
import biblioteca.models.Prestamo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoDAO prestamoDAO;

    @GetMapping
    public ResponseEntity<List<Prestamo>> getAllPrestamos() {
        try {
            List<Prestamo> prestamos = prestamoDAO.findAll();
            return ResponseEntity.ok(prestamos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable Long id) {
        try {
            Optional<Prestamo> prestamo = prestamoDAO.findById(id);
            return prestamo.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Prestamo> createPrestamo(@RequestBody Prestamo prestamo) {
        try {
            Prestamo savedPrestamo = prestamoDAO.save(prestamo);
            return ResponseEntity.ok(savedPrestamo);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        try {
            Optional<Prestamo> existingPrestamo = prestamoDAO.findById(id);
            if (existingPrestamo.isPresent()) {
                prestamo.setId(id);
                Prestamo updatedPrestamo = prestamoDAO.update(prestamo);
                return ResponseEntity.ok(updatedPrestamo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestamo(@PathVariable Long id) {
        try {
            prestamoDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Prestamo>> getPrestamosByUsuario(@PathVariable Long usuarioId) {
        try {
            List<Prestamo> prestamos = prestamoDAO.findByUsuarioId(usuarioId);
            return ResponseEntity.ok(prestamos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/ejemplar/{ejemplarId}")
    public ResponseEntity<List<Prestamo>> getPrestamosByEjemplar(@PathVariable Long ejemplarId) {
        try {
            List<Prestamo> prestamos = prestamoDAO.findByEjemplarId(ejemplarId);
            return ResponseEntity.ok(prestamos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Prestamo>> getPrestamosByEstado(@PathVariable String estado) {
        try {
            List<Prestamo> prestamos = prestamoDAO.findByEstado(estado);
            return ResponseEntity.ok(prestamos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Prestamo>> getPrestamosActivos() {
        try {
            List<Prestamo> prestamos = prestamoDAO.findPrestamosActivos();
            return ResponseEntity.ok(prestamos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/vencidos")
    public ResponseEntity<List<Prestamo>> getPrestamosVencidos() {
        try {
            List<Prestamo> prestamos = prestamoDAO.findPrestamosVencidos();
            return ResponseEntity.ok(prestamos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/con-multa")
    public ResponseEntity<List<Prestamo>> getPrestamosConMulta() {
        try {
            List<Prestamo> prestamos = prestamoDAO.findPrestamosConMulta();
            return ResponseEntity.ok(prestamos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/estado/{estado}")
    public ResponseEntity<List<Prestamo>> getPrestamosByUsuarioAndEstado(@PathVariable Long usuarioId,
                                                                         @PathVariable String estado) {
        try {
            List<Prestamo> prestamos = prestamoDAO.findPrestamosByUsuarioAndEstado(usuarioId, estado);
            return ResponseEntity.ok(prestamos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/contar-activos")
    public ResponseEntity<Integer> countPrestamosActivosByUsuario(@PathVariable Long usuarioId) {
        try {
            int count = prestamoDAO.countPrestamosActivosByUsuario(usuarioId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/tiene-vencidos")
    public ResponseEntity<Boolean> tienePrestamosVencidos(@PathVariable Long usuarioId) {
        try {
            boolean tieneVencidos = prestamoDAO.tienePrestamosVencidos(usuarioId);
            return ResponseEntity.ok(tieneVencidos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}