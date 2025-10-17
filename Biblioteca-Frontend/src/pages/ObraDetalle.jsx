import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import api from '../api/api';

export default function ObraDetalle(){
  const { id } = useParams();
  const [obra, setObra] = useState(null);

  useEffect(()=> {
    (async () => {
      try {
        const res = await api.get(`/obras/${id}`);
        setObra(res.data);
      } catch (e) {
        console.error(e);
      }
    })();
  }, [id]);

  if (!obra) return <p>Cargando obra...</p>;

  const reservar = async () => {
    try {
      await api.post('/reservas', { obra_id: obra.id });
      alert('Reserva solicitada.');
    } catch (e) {
      alert('Error: ' + (e.response?.data?.message || e.message));
    }
  };

  return (
    <div>
      <h2>{obra.titulo}</h2>
      <p>{obra.descripcion || '-'}</p>
      <p>Idioma: {obra.idioma || '-'}</p>
      <p>Año: {obra.anio_publicacion || '-'}</p>
      <div style={{ display:'flex', gap:8 }}>
        <button onClick={reservar}>Reservar</button>
      </div>
    </div>
  );
}
