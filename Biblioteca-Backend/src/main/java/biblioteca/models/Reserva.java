package biblioteca.models;

import java.util.Date;

public class Reserva {
    private Long id;
    private Long usuarioId;
    private Long obraId;
    private Date fechaReserva;
    private Date fechaDisponibilidadEstimada;
    private String estado;
    private Integer posicionCola;
    private Boolean notificado;
    private Date createdAt;

    // Constructores
    public Reserva() {}

    public Reserva(Long id, Long usuarioId, Long obraId, Date fechaReserva,
                   Date fechaDisponibilidadEstimada, String estado, Integer posicionCola,
                   Boolean notificado, Date createdAt) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.obraId = obraId;
        this.fechaReserva = fechaReserva;
        this.fechaDisponibilidadEstimada = fechaDisponibilidadEstimada;
        this.estado = estado;
        this.posicionCola = posicionCola;
        this.notificado = notificado;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getObraId() { return obraId; }
    public void setObraId(Long obraId) { this.obraId = obraId; }

    public Date getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(Date fechaReserva) { this.fechaReserva = fechaReserva; }

    public Date getFechaDisponibilidadEstimada() { return fechaDisponibilidadEstimada; }
    public void setFechaDisponibilidadEstimada(Date fechaDisponibilidadEstimada) { this.fechaDisponibilidadEstimada = fechaDisponibilidadEstimada; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getPosicionCola() { return posicionCola; }
    public void setPosicionCola(Integer posicionCola) { this.posicionCola = posicionCola; }

    public Boolean getNotificado() { return notificado; }
    public void setNotificado(Boolean notificado) { this.notificado = notificado; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}