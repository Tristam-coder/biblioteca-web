package biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.dao.HistorialDAO;
import biblioteca.models.Historial;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/historial")
public class HistorialController {

    @Autowired
    private HistorialDAO historialDAO;

    @GetMapping
    public ResponseEntity<List<Historial>> getAllHistorial() {
        try {
            List<Historial> historial = historialDAO.findAll();
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historial> getHistorialById(@PathVariable Long id) {
        try {
            Optional<Historial> historial = historialDAO.findById(id);
            return historial.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Historial> createHistorial(@RequestBody Historial historial) {
        try {
            Historial savedHistorial = historialDAO.save(historial);
            return ResponseEntity.ok(savedHistorial);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Historial>> getHistorialByUsuario(@PathVariable Long usuarioId) {
        try {
            List<Historial> historial = historialDAO.findByUsuarioId(usuarioId);
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/accion/{accion}")
    public ResponseEntity<List<Historial>> getHistorialByAccion(@PathVariable String accion) {
        try {
            List<Historial> historial = historialDAO.findByAccion(accion);
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/tabla/{tablaAfectada}")
    public ResponseEntity<List<Historial>> getHistorialByTabla(@PathVariable String tablaAfectada) {
        try {
            List<Historial> historial = historialDAO.findByTablaAfectada(tablaAfectada);
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/recientes/{limite}")
    public ResponseEntity<List<Historial>> getHistorialReciente(@PathVariable int limite) {
        try {
            List<Historial> historial = historialDAO.findHistorialReciente(limite);
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/accion/{accion}")
    public ResponseEntity<List<Historial>> getHistorialByUsuarioAndAccion(@PathVariable Long usuarioId,
                                                                          @PathVariable String accion) {
        try {
            List<Historial> historial = historialDAO.findByUsuarioAndAccion(usuarioId, accion);
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/contar")
    public ResponseEntity<Integer> countHistorialByUsuario(@PathVariable Long usuarioId) {
        try {
            int count = historialDAO.countByUsuarioId(usuarioId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/estadisticas/acciones-frecuentes")
    public ResponseEntity<List<Object[]>> getAccionesMasFrecuentes() {
        try {
            List<Object[]> estadisticas = historialDAO.getAccionesMasFrecuentes();
            return ResponseEntity.ok(estadisticas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}