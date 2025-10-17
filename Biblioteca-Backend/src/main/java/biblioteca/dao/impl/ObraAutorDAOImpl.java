package biblioteca.dao.impl;

import biblioteca.dao.ObraAutorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import biblioteca.models.ObraAutor;

import java.util.List;
import java.util.Optional;

@Repository
public class ObraAutorDAOImpl implements ObraAutorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ObraAutor> findAll() {
        String sql = "SELECT * FROM obra_autor ORDER BY obra_id, autor_id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ObraAutor.class));
    }

    @Override
    public Optional<ObraAutor> findById(Long id) {
        String sql = "SELECT * FROM obra_autor WHERE id = ?";
        try {
            ObraAutor obraAutor = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ObraAutor.class), id);
            return Optional.ofNullable(obraAutor);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public ObraAutor save(ObraAutor obraAutor) {
        String sql = "INSERT INTO obra_autor (obra_id, autor_id, tipo_contribucion) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                obraAutor.getObraId(),
                obraAutor.getAutorId(),
                obraAutor.getTipoContribucion());
        return obraAutor;
    }

    @Override
    public ObraAutor update(ObraAutor obraAutor) {
        String sql = "UPDATE obra_autor SET obra_id = ?, autor_id = ?, tipo_contribucion = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                obraAutor.getObraId(),
                obraAutor.getAutorId(),
                obraAutor.getTipoContribucion(),
                obraAutor.getId());
        return obraAutor;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM obra_autor WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<ObraAutor> findByObraId(Long obraId) {
        String sql = "SELECT * FROM obra_autor WHERE obra_id = ? ORDER BY autor_id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ObraAutor.class), obraId);
    }

    @Override
    public List<ObraAutor> findByAutorId(Long autorId) {
        String sql = "SELECT * FROM obra_autor WHERE autor_id = ? ORDER BY obra_id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ObraAutor.class), autorId);
    }

    @Override
    public Optional<ObraAutor> findByObraAndAutor(Long obraId, Long autorId) {
        String sql = "SELECT * FROM obra_autor WHERE obra_id = ? AND autor_id = ?";
        try {
            ObraAutor obraAutor = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ObraAutor.class), obraId, autorId);
            return Optional.ofNullable(obraAutor);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByObraId(Long obraId) {
        String sql = "DELETE FROM obra_autor WHERE obra_id = ?";
        jdbcTemplate.update(sql, obraId);
    }

    @Override
    public void deleteByAutorId(Long autorId) {
        String sql = "DELETE FROM obra_autor WHERE autor_id = ?";
        jdbcTemplate.update(sql, autorId);
    }

    @Override
    public boolean existsByObraAndAutor(Long obraId, Long autorId) {
        String sql = "SELECT COUNT(*) FROM obra_autor WHERE obra_id = ? AND autor_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, obraId, autorId);
        return count != null && count > 0;
    }
}