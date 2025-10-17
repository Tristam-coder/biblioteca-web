package biblioteca.dao;

import biblioteca.models.Multa;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface MultaDAO {

    // CRUD básico
    List<Multa> findAll();
    Optional<Multa> findById(Long id);
    Multa save(Multa multa);
    Multa update(Multa multa);
    void deleteById(Long id);

    // Búsquedas específicas
    List<Multa> findByUsuarioId(Long usuarioId);
    List<Multa> findByPrestamoId(Long prestamoId);
    List<Multa> findByEstado(String estado);

    // Búsquedas avanzadas
    List<Multa> findMultasPendientes();
    List<Multa> findMultasPagadas();
    List<Multa> findMultasByUsuarioAndEstado(Long usuarioId, String estado);

    // Operaciones financieras
    BigDecimal getTotalMultasPendientesByUsuario(Long usuarioId);
    int countMultasPendientesByUsuario(Long usuarioId);
    boolean tieneMultasPendientes(Long usuarioId);

    // Estadísticas
    BigDecimal getTotalMultasPagadas();
    BigDecimal getTotalMultasPendientes();
}