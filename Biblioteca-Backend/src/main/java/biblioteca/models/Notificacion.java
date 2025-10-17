package biblioteca.models;

import java.util.Date;

public class Notificacion {
    private Long id;
    private Long usuarioId;
    private String tipo;
    private String mensaje;
    private Boolean leida;
    private Date fechaEnvio;
    private Date createdAt;

    // Constructores
    public Notificacion() {}

    public Notificacion(Long id, Long usuarioId, String tipo, String mensaje,
                        Boolean leida, Date fechaEnvio, Date createdAt) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.leida = leida;
        this.fechaEnvio = fechaEnvio;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Boolean getLeida() { return leida; }
    public void setLeida(Boolean leida) { this.leida = leida; }

    public Date getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(Date fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}