import React, { useEffect, useState } from 'react';
import api from '../api/api';

export default function Perfil(){
  const [prestamos, setPrestamos] = useState([]);

  useEffect(()=> {
    (async () => {
      try {
        const res = await api.get('/usuarios/me/prestamos'); // adapta ruta
        setPrestamos(res.data);
      } catch (e) { console.error(e); }
    })();
  }, []);

  return (
    <div>
      <h2>Mi perfil</h2>
      <h3>Préstamos</h3>
      <ul>
        {prestamos.map(p => (
          <li key={p.id}>
            {p.obraTitulo || p.ejemplar?.titulo} — Dev: {p.fecha_devolucion_prevista}
          </li>
        ))}
      </ul>
    </div>
  );
}
