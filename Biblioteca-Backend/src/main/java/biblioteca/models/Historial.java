package biblioteca.models;

import java.util.Date;

public class Historial {
    private Long id;
    private Long usuarioId;
    private String accion;
    private String tablaAfectada;
    private Integer registroId;
    private String detalles;
    private Date createdAt;

    // Constructores
    public Historial() {}

    public Historial(Long id, Long usuarioId, String accion, String tablaAfectada,
                     Integer registroId, String detalles, Date createdAt) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.accion = accion;
        this.tablaAfectada = tablaAfectada;
        this.registroId = registroId;
        this.detalles = detalles;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }

    public String getTablaAfectada() { return tablaAfectada; }
    public void setTablaAfectada(String tablaAfectada) { this.tablaAfectada = tablaAfectada; }

    public Integer getRegistroId() { return registroId; }
    public void setRegistroId(Integer registroId) { this.registroId = registroId; }

    public String getDetalles() { return detalles; }
    public void setDetalles(String detalles) { this.detalles = detalles; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}