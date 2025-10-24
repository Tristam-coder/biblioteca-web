import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Login from './pages/Login';
import Catalogo from './pages/Catalogo';
import ObraDetalle from './pages/ObraDetalle';
import Perfil from './pages/Perfil';
import NotFound from './pages/NotFound';
import PrivateRoute from './components/ProttectedRouter'; 
export default function App() {
  return (
    <BrowserRouter>
      <Header />
      <main style={{ padding: 20 }}>
        <Routes>
          <Route path="/login" element={<Login />} />

          {/* Rutas protegidas */}
          <Route
            path="/"
            element={
              <PrivateRoute>
                <Catalogo />
              </PrivateRoute>
            }
          />
          <Route
            path="/perfil"
            element={
              <PrivateRoute>
                <Perfil />
              </PrivateRoute>
            }
          />
          <Route
            path="/obra/:id"
            element={
              <PrivateRoute>
                <ObraDetalle />
              </PrivateRoute>
            }
          />

          {/* Página 404 */}
          <Route path="*" element={<NotFound />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}
