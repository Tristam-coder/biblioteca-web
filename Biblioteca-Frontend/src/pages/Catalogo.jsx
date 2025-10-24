import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import api from '../api/api';

export default function Catalogo(){
  const [obras, setObras] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(()=> {
    (async () => {
      try {
        const res = await api.get('/obras'); // adapta a tu endpoint real
        setObras(res.data);
      } catch (e) {
        console.error(e);
      } finally { setLoading(false); }
    })();
  }, []);

  if (loading) return <p>Cargando...</p>;

  return (
    <div>
      <h2>Catálogo</h2>
      <ul style={{ listStyle: 'none', padding: 0 }}>
        {obras.map(o => (
          <li key={o.id} style={{ background:'#fff', padding:12, marginBottom:8, borderRadius:8 }}>
            <Link to={`/obra/${o.id}`}><strong>{o.titulo}</strong></Link>
            <div style={{ fontSize: 13, color:'#555' }}>{o.anio_publicacion} — {o.idioma || '—'}</div>
          </li>
        ))}
      </ul>
    </div>
  );
}
