package biblioteca.models;

import java.util.Date;

public class ObraAutor {
    private Long id;
    private Long obraId;
    private Long autorId;
    private String tipoContribucion;
    private Date createdAt;

    // Constructores
    public ObraAutor() {}

    public ObraAutor(Long id, Long obraId, Long autorId, String tipoContribucion, Date createdAt) {
        this.id = id;
        this.obraId = obraId;
        this.autorId = autorId;
        this.tipoContribucion = tipoContribucion;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getObraId() { return obraId; }
    public void setObraId(Long obraId) { this.obraId = obraId; }

    public Long getAutorId() { return autorId; }
    public void setAutorId(Long autorId) { this.autorId = autorId; }

    public String getTipoContribucion() { return tipoContribucion; }
    public void setTipoContribucion(String tipoContribucion) { this.tipoContribucion = tipoContribucion; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}