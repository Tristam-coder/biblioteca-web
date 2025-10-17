import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function Header(){
  const nav = useNavigate();
  const logged = !!localStorage.getItem('token');

  const handleLogout = () => {
    localStorage.removeItem('token');
    nav('/login');
  };

  return (
    <header style={{ background: '#fff', padding: 12, borderBottom: '1px solid #eee' }}>
      <div style={{ maxWidth: 960, margin: '0 auto', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <div>
          <Link to="/"><strong>Biblioteca</strong></Link>
        </div>
        <nav>
          <Link to="/" style={{ marginRight: 10 }}>Catálogo</Link>
          {logged ? (
            <>
              <Link to="/perfil" style={{ marginRight: 10 }}>Mi perfil</Link>
              <button onClick={handleLogout}>Salir</button>
            </>
          ) : (
            <Link to="/login">Iniciar sesión</Link>
          )}
        </nav>
      </div>
    </header>
  );
}
