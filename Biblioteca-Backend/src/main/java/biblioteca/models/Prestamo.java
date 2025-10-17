package biblioteca.models;

import java.math.BigDecimal;
import java.util.Date;

public class Prestamo {
    private Long id;
    private Long usuarioId;
    private Long ejemplarId;
    private Date fechaPrestamo;
    private Date fechaDevolucionPrevista;
    private Date fechaDevolucionReal;
    private String estado;
    private BigDecimal multa;
    private String observaciones;
    private Date createdAt;

    // Constructores
    public Prestamo() {}

    public Prestamo(Long id, Long usuarioId, Long ejemplarId, Date fechaPrestamo,
                    Date fechaDevolucionPrevista, Date fechaDevolucionReal, String estado,
                    BigDecimal multa, String observaciones, Date createdAt) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.ejemplarId = ejemplarId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.estado = estado;
        this.multa = multa;
        this.observaciones = observaciones;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getEjemplarId() { return ejemplarId; }
    public void setEjemplarId(Long ejemplarId) { this.ejemplarId = ejemplarId; }

    public Date getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(Date fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public Date getFechaDevolucionPrevista() { return fechaDevolucionPrevista; }
    public void setFechaDevolucionPrevista(Date fechaDevolucionPrevista) { this.fechaDevolucionPrevista = fechaDevolucionPrevista; }

    public Date getFechaDevolucionReal() { return fechaDevolucionReal; }
    public void setFechaDevolucionReal(Date fechaDevolucionReal) { this.fechaDevolucionReal = fechaDevolucionReal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public BigDecimal getMulta() { return multa; }
    public void setMulta(BigDecimal multa) { this.multa = multa; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}