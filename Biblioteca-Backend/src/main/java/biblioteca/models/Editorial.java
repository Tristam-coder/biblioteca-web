package biblioteca.models;

import java.util.Date;

public class Editorial {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private Date fechaFundacion;
    private String pais;
    private Date createdAt;

    // Constructores
    public Editorial() {}

    public Editorial(Long id, String nombre, String direccion, String telefono,
                     String email, Date fechaFundacion, String pais, Date createdAt) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaFundacion = fechaFundacion;
        this.pais = pais;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getFechaFundacion() { return fechaFundacion; }
    public void setFechaFundacion(Date fechaFundacion) { this.fechaFundacion = fechaFundacion; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}