package biblioteca.models;

import java.util.Date;

public class TipoObra {
    private Long id;
    private String nombre;
    private String descripcion;
    private Date createdAt;

    // Constructores
    public TipoObra() {}

    public TipoObra(Long id, String nombre, String descripcion, Date createdAt) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}