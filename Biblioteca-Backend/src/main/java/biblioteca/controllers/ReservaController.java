package biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.dao.ReservaDAO;
import biblioteca.models.Reserva;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaDAO reservaDAO;

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        try {
            List<Reserva> reservas = reservaDAO.findAll();
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        try {
            Optional<Reserva> reserva = reservaDAO.findById(id);
            return reserva.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        try {
            // Verificar si el usuario ya tiene una reserva activa para esta obra
            if (reservaDAO.existsReservaActivaByUsuarioAndObra(reserva.getUsuarioId(), reserva.getObraId())) {
                return ResponseEntity.badRequest().build();
            }

            // Verificar límite de reservas activas por usuario
            if (reservaDAO.countReservasActivasByUsuario(reserva.getUsuarioId()) >= 3) {
                return ResponseEntity.badRequest().build();
            }

            Reserva savedReserva = reservaDAO.save(reserva);
            return ResponseEntity.ok(savedReserva);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        try {
            Optional<Reserva> existingReserva = reservaDAO.findById(id);
            if (existingReserva.isPresent()) {
                reserva.setId(id);
                Reserva updatedReserva = reservaDAO.update(reserva);
                return ResponseEntity.ok(updatedReserva);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        try {
            reservaDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Reserva>> getReservasByUsuario(@PathVariable Long usuarioId) {
        try {
            List<Reserva> reservas = reservaDAO.findByUsuarioId(usuarioId);
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/obra/{obraId}")
    public ResponseEntity<List<Reserva>> getReservasByObra(@PathVariable Long obraId) {
        try {
            List<Reserva> reservas = reservaDAO.findByObraId(obraId);
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Reserva>> getReservasByEstado(@PathVariable String estado) {
        try {
            List<Reserva> reservas = reservaDAO.findByEstado(estado);
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Reserva>> getReservasActivas() {
        try {
            List<Reserva> reservas = reservaDAO.findReservasActivas();
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<Reserva>> getReservasPendientes() {
        try {
            List<Reserva> reservas = reservaDAO.findReservasPendientes();
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/por-notificar")
    public ResponseEntity<List<Reserva>> getReservasPorNotificar() {
        try {
            List<Reserva> reservas = reservaDAO.findReservasPorNotificar();
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/obra/{obraId}/siguiente")
    public ResponseEntity<Reserva> getSiguienteReservaEnCola(@PathVariable Long obraId) {
        try {
            Optional<Reserva> reserva = reservaDAO.findSiguienteReservaEnCola(obraId);
            return reserva.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/obra/{obraId}/posicion")
    public ResponseEntity<Integer> getPosicionEnCola(@PathVariable Long obraId) {
        try {
            int posicion = reservaDAO.getPosicionEnCola(obraId);
            return ResponseEntity.ok(posicion);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/obra/{obraId}/existe")
    public ResponseEntity<Boolean> existeReservaActiva(@PathVariable Long usuarioId, @PathVariable Long obraId) {
        try {
            boolean existe = reservaDAO.existsReservaActivaByUsuarioAndObra(usuarioId, obraId);
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}