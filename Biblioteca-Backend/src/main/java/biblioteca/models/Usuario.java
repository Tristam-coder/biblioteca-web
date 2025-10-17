package biblioteca.models;

import java.util.Date;

public class Usuario {
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;
    private String direccion;
    private String tipoUsuario;
    private String estado;
    private Date fechaRegistro;
    private Date createdAt;

    // Constructores
    public Usuario() {}

    public Usuario(Long id, String dni, String nombre, String apellido, String email,
                   String password, String telefono, String direccion, String tipoUsuario,
                   String estado, Date fechaRegistro, Date createdAt) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}