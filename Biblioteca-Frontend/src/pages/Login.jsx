import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/api';

export default function Login(){
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [err, setErr] = useState('');
  const nav = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    setErr('');
    try {
      const res = await api.post('/auth/login', { email, password }); // adapta ruta si hace falta
      localStorage.setItem('token', res.data.token);
      nav('/');
    } catch (error) {
      setErr(error.response?.data?.message || 'Error al iniciar sesión');
    }
  };

  return (
    <div style={{ maxWidth: 420, margin: '20px auto' }}>
      <h2>Iniciar sesión</h2>
      <form onSubmit={submit} style={{ display:'grid', gap:10 }}>
        <input placeholder="Email" value={email} onChange={e=>setEmail(e.target.value)} required />
        <input type="password" placeholder="Contraseña" value={password} onChange={e=>setPassword(e.target.value)} required />
        <button type="submit">Entrar</button>
        {err && <p style={{ color: 'red' }}>{err}</p>}
      </form>
    </div>
  );
}
