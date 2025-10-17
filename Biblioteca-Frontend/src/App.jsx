import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Login from './pages/Login';
import Catalogo from './pages/Catalogo';
import ObraDetalle from './pages/ObraDetalle';
import Perfil from './pages/Perfil';
import NotFound from './pages/NotFound';

export default function App() {
  return (
    <BrowserRouter>
      <Header />
      <main style={{ padding: 20 }}>
        <Routes>
          <Route path="/" element={<Catalogo />} />
          <Route path="/login" element={<Login />} />
          <Route path="/obra/:id" element={<ObraDetalle />} />
          <Route path="/perfil" element={<Perfil />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}
