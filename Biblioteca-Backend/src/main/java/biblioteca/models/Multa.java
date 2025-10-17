package biblioteca.models;

import java.math.BigDecimal;
import java.util.Date;

public class Multa {
    private Long id;
    private Long usuarioId;
    private Long prestamoId;
    private BigDecimal monto;
    private String motivo;
    private String estado;
    private Date fechaGeneracion;
    private Date fechaPago;
    private Date createdAt;

    // Constructores
    public Multa() {}

    public Multa(Long id, Long usuarioId, Long prestamoId, BigDecimal monto, String motivo,
                 String estado, Date fechaGeneracion, Date fechaPago, Date createdAt) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.prestamoId = prestamoId;
        this.monto = monto;
        this.motivo = motivo;
        this.estado = estado;
        this.fechaGeneracion = fechaGeneracion;
        this.fechaPago = fechaPago;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getPrestamoId() { return prestamoId; }
    public void setPrestamoId(Long prestamoId) { this.prestamoId = prestamoId; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Date getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(Date fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }

    public Date getFechaPago() { return fechaPago; }
    public void setFechaPago(Date fechaPago) { this.fechaPago = fechaPago; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}