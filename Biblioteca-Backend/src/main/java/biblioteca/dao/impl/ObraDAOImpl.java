package biblioteca.dao.impl;

import biblioteca.dao.ObraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import biblioteca.models.Obra;

import java.util.List;
import java.util.Optional;

@Repository
public class ObraDAOImpl implements ObraDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Obra> findAll() {
        String sql = "SELECT * FROM obra ORDER BY titulo";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Obra.class));
    }

    @Override
    public Optional<Obra> findById(Long id) {
        String sql = "SELECT * FROM obra WHERE id = ?";
        try {
            Obra obra = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Obra.class), id);
            return Optional.ofNullable(obra);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Obra save(Obra obra) {
        String sql = "INSERT INTO obra (titulo, isbn_issn, tipo_obra_id, editorial_id, anio_publicacion, " +
                "edicion, numero_paginas, idioma, area_tematica, nivel, descripcion, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                obra.getTitulo(),
                obra.getIsbnIssn(),
                obra.getTipoObraId(),
                obra.getEditorialId(),
                obra.getAnioPublicacion(),
                obra.getEdicion(),
                obra.getNumeroPaginas(),
                obra.getIdioma(),
                obra.getAreaTematica(),
                obra.getNivel(),
                obra.getDescripcion(),
                obra.getEstado());
        return obra;
    }

    @Override
    public Obra update(Obra obra) {
        String sql = "UPDATE obra SET titulo = ?, isbn_issn = ?, tipo_obra_id = ?, editorial_id = ?, " +
                "anio_publicacion = ?, edicion = ?, numero_paginas = ?, idioma = ?, area_tematica = ?, " +
                "nivel = ?, descripcion = ?, estado = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                obra.getTitulo(),
                obra.getIsbnIssn(),
                obra.getTipoObraId(),
                obra.getEditorialId(),
                obra.getAnioPublicacion(),
                obra.getEdicion(),
                obra.getNumeroPaginas(),
                obra.getIdioma(),
                obra.getAreaTematica(),
                obra.getNivel(),
                obra.getDescripcion(),
                obra.getEstado(),
                obra.getId());
        return obra;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM obra WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Obra> findByTitulo(String titulo) {
        String sql = "SELECT * FROM obra WHERE LOWER(titulo) LIKE LOWER(?) ORDER BY titulo";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Obra.class), "%" + titulo + "%");
    }

    @Override
    public List<Obra> findByTipoObraId(Long tipoObraId) {
        String sql = "SELECT * FROM obra WHERE tipo_obra_id = ? ORDER BY titulo";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Obra.class), tipoObraId);
    }

    @Override
    public List<Obra> findByEditorialId(Long editorialId) {
        String sql = "SELECT * FROM obra WHERE editorial_id = ? ORDER BY titulo";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Obra.class), editorialId);
    }

    @Override
    public List<Obra> findByEstado(String estado) {
        String sql = "SELECT * FROM obra WHERE estado = ? ORDER BY titulo";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Obra.class), estado);
    }

    @Override
    public List<Obra> findByAreaTematica(String areaTematica) {
        String sql = "SELECT * FROM obra WHERE LOWER(area_tematica) LIKE LOWER(?) ORDER BY titulo";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Obra.class), "%" + areaTematica + "%");
    }

    @Override
    public Optional<Obra> findByIsbnIssn(String isbnIssn) {
        String sql = "SELECT * FROM obra WHERE isbn_issn = ?";
        try {
            Obra obra = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Obra.class), isbnIssn);
            return Optional.ofNullable(obra);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Obra> searchByTitulo(String texto) {
        String sql = "SELECT * FROM obra WHERE LOWER(titulo) LIKE LOWER(?) OR LOWER(descripcion) LIKE LOWER(?) " +
                "ORDER BY titulo";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Obra.class),
                "%" + texto + "%", "%" + texto + "%");
    }

    @Override
    public List<Obra> findByAnioPublicacion(Integer anio) {
        String sql = "SELECT * FROM obra WHERE anio_publicacion = ? ORDER BY titulo";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Obra.class), anio);
    }

    @Override
    public List<Obra> findByAnioPublicacionBetween(Integer anioInicio, Integer anioFin) {
        String sql = "SELECT * FROM obra WHERE anio_publicacion BETWEEN ? AND ? ORDER BY anio_publicacion, titulo";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Obra.class), anioInicio, anioFin);
    }

    @Override
    public boolean existsByIsbnIssn(String isbnIssn) {
        String sql = "SELECT COUNT(*) FROM obra WHERE isbn_issn = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, isbnIssn);
        return count != null && count > 0;
    }
}