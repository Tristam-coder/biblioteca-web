import React from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Catalogo from './pages/Catalog'
import DetalleObra from './pages/BookDetail'
import SolicitudPrestamo from './pages/LoanRequest'
import PanelUsuario from './pages/UserPanel'
import PaginaPago from './pages/Payment'
import GestionUsuariosReportes from './pages/UserManagementReports'
import Login from './pages/Login'
import Registro from './pages/Registro'
import RecuperarContrasena from './pages/RecuperarContrasena'
import './App.css'

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Catalogo />} />
        <Route path="/catalogo" element={<Catalogo />} />
        <Route path="/detalle-obra" element={<DetalleObra />} />
        <Route path="/solicitud-prestamo" element={<SolicitudPrestamo />} />
        <Route path="/panel-usuario" element={<PanelUsuario />} />
        <Route path="/pago" element={<PaginaPago />} />
        <Route path="/gestion-usuarios" element={<GestionUsuariosReportes />} />
        <Route path="/login" element={<Login />} />
        <Route path="/registro" element={<Registro />} />
        <Route path="/recuperar-contrasena" element={<RecuperarContrasena />} />
        {/* Ruta por defecto para páginas no encontradas */}
        <Route path="*" element={<Catalogo />} />
      </Routes>
    </Router>
  )
}

export default App