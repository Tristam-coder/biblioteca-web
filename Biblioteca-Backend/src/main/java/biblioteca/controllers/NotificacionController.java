package biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.dao.NotificacionDAO;
import biblioteca.models.Notificacion;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionDAO notificacionDAO;

    @GetMapping
    public ResponseEntity<List<Notificacion>> getAllNotificaciones() {
        try {
            List<Notificacion> notificaciones = notificacionDAO.findAll();
            return ResponseEntity.ok(notificaciones);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getNotificacionById(@PathVariable Long id) {
        try {
            Optional<Notificacion> notificacion = notificacionDAO.findById(id);
            return notificacion.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Notificacion> createNotificacion(@RequestBody Notificacion notificacion) {
        try {
            Notificacion savedNotificacion = notificacionDAO.save(notificacion);
            return ResponseEntity.ok(savedNotificacion);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> updateNotificacion(@PathVariable Long id, @RequestBody Notificacion notificacion) {
        try {
            Optional<Notificacion> existingNotificacion = notificacionDAO.findById(id);
            if (existingNotificacion.isPresent()) {
                notificacion.setId(id);
                Notificacion updatedNotificacion = notificacionDAO.update(notificacion);
                return ResponseEntity.ok(updatedNotificacion);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificacion(@PathVariable Long id) {
        try {
            notificacionDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> getNotificacionesByUsuario(@PathVariable Long usuarioId) {
        try {
            List<Notificacion> notificaciones = notificacionDAO.findByUsuarioId(usuarioId);
            return ResponseEntity.ok(notificaciones);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Notificacion>> getNotificacionesByTipo(@PathVariable String tipo) {
        try {
            List<Notificacion> notificaciones = notificacionDAO.findByTipo(tipo);
            return ResponseEntity.ok(notificaciones);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/leida/{leida}")
    public ResponseEntity<List<Notificacion>> getNotificacionesByLeida(@PathVariable Boolean leida) {
        try {
            List<Notificacion> notificaciones = notificacionDAO.findByLeida(leida);
            return ResponseEntity.ok(notificaciones);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/no-leidas")
    public ResponseEntity<List<Notificacion>> getNotificacionesNoLeidasByUsuario(@PathVariable Long usuarioId) {
        try {
            List<Notificacion> notificaciones = notificacionDAO.findNotificacionesNoLeidasByUsuario(usuarioId);
            return ResponseEntity.ok(notificaciones);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/recientes/{limite}")
    public ResponseEntity<List<Notificacion>> getNotificacionesRecientes(@PathVariable Long usuarioId,
                                                                         @PathVariable int limite) {
        try {
            List<Notificacion> notificaciones = notificacionDAO.findNotificacionesRecientesByUsuario(usuarioId, limite);
            return ResponseEntity.ok(notificaciones);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}/marcar-leida")
    public ResponseEntity<Void> marcarComoLeida(@PathVariable Long id) {
        try {
            int updated = notificacionDAO.marcarComoLeida(id);
            if (updated > 0) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/usuario/{usuarioId}/marcar-todas-leidas")
    public ResponseEntity<Void> marcarTodasComoLeidas(@PathVariable Long usuarioId) {
        try {
            notificacionDAO.marcarTodasComoLeidasByUsuario(usuarioId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/contar-no-leidas")
    public ResponseEntity<Integer> countNotificacionesNoLeidas(@PathVariable Long usuarioId) {
        try {
            int count = notificacionDAO.countNotificacionesNoLeidasByUsuario(usuarioId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}