package biblioteca.models;

import java.util.Date;

public class Ejemplar {
    private Long id;
    private Long obraId;
    private String codigoBarras;
    private String estado;
    private String ubicacion;
    private String observaciones;
    private Date createdAt;

    // Constructores
    public Ejemplar() {}

    public Ejemplar(Long id, Long obraId, String codigoBarras, String estado,
                    String ubicacion, String observaciones, Date createdAt) {
        this.id = id;
        this.obraId = obraId;
        this.codigoBarras = codigoBarras;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.observaciones = observaciones;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getObraId() { return obraId; }
    public void setObraId(Long obraId) { this.obraId = obraId; }

    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}