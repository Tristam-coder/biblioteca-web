package biblioteca.models;

import java.util.Date;

public class Obra {
    private Long id;
    private String titulo;
    private String isbnIssn;
    private Long tipoObraId;
    private Long editorialId;
    private Integer anioPublicacion;
    private Integer edicion;
    private Integer numeroPaginas;
    private String idioma;
    private String areaTematica;
    private String nivel;
    private String descripcion;
    private String estado;
    private Date createdAt;

    // Constructores
    public Obra() {}

    public Obra(Long id, String titulo, String isbnIssn, Long tipoObraId, Long editorialId,
                Integer anioPublicacion, Integer edicion, Integer numeroPaginas, String idioma,
                String areaTematica, String nivel, String descripcion, String estado, Date createdAt) {
        this.id = id;
        this.titulo = titulo;
        this.isbnIssn = isbnIssn;
        this.tipoObraId = tipoObraId;
        this.editorialId = editorialId;
        this.anioPublicacion = anioPublicacion;
        this.edicion = edicion;
        this.numeroPaginas = numeroPaginas;
        this.idioma = idioma;
        this.areaTematica = areaTematica;
        this.nivel = nivel;
        this.descripcion = descripcion;
        this.estado = estado;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIsbnIssn() { return isbnIssn; }
    public void setIsbnIssn(String isbnIssn) { this.isbnIssn = isbnIssn; }

    public Long getTipoObraId() { return tipoObraId; }
    public void setTipoObraId(Long tipoObraId) { this.tipoObraId = tipoObraId; }

    public Long getEditorialId() { return editorialId; }
    public void setEditorialId(Long editorialId) { this.editorialId = editorialId; }

    public Integer getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(Integer anioPublicacion) { this.anioPublicacion = anioPublicacion; }

    public Integer getEdicion() { return edicion; }
    public void setEdicion(Integer edicion) { this.edicion = edicion; }

    public Integer getNumeroPaginas() { return numeroPaginas; }
    public void setNumeroPaginas(Integer numeroPaginas) { this.numeroPaginas = numeroPaginas; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public String getAreaTematica() { return areaTematica; }
    public void setAreaTematica(String areaTematica) { this.areaTematica = areaTematica; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}