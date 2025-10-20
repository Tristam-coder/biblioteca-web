import React, { useState, useEffect } from 'react'
import { Link, useLocation, useNavigate } from 'react-router-dom'

const Header = () => {
  const location = useLocation()
  const navigate = useNavigate()

  const [isAuthenticated, setIsAuthenticated] = useState(false)
  const [isAdmin, setIsAdmin] = useState(false)

  useEffect(() => {
    // Verificar autenticación al cargar el componente
    const savedAuth = sessionStorage.getItem('isAuthenticated')
    const savedAdmin = sessionStorage.getItem('isAdmin')
    
    if (savedAuth === 'true') {
      setIsAuthenticated(true)
      setIsAdmin(savedAdmin === 'true')
    }
  }, [])

  const isActive = (path) => {
    return location.pathname === path ? 'active' : ''
  }

  const handleLogout = () => {
    if (window.confirm('¿Está seguro de que desea cerrar sesión?')) {
      // Limpiar sessionStorage
      sessionStorage.removeItem('isAuthenticated')
      sessionStorage.removeItem('isAdmin')
      sessionStorage.removeItem('userName')
      
      // Actualizar estado
      setIsAuthenticated(false)
      setIsAdmin(false)
      
      // Redirigir al catálogo
      navigate('/catalogo')
    }
  }

  return (
    <header>
      <div className="header-top">
        <div className="logo">
          <h1>Ediciones Saberum</h1>
          <p>Ven, lee y saborea el conocimiento</p>
        </div>
        <div className="header-actions">
          {isAuthenticated ? (
            <div className="user-menu">
              <span className="welcome-msg">Bienvenido, Usuario</span>
              <button className="btn-logout" onClick={handleLogout}>
                Cerrar Sesión
              </button> 
            </div>
          ) : (
            <div className="auth-buttons">
              <Link to="/registro" className="btn-register">
                Registrarse
              </Link>
              <Link to="/login" className="btn-login">
                Iniciar Sesión
              </Link>
            </div>
          )}
        </div>
      </div>
      <nav>
        <ul>
          <li><Link to="/catalogo" className={isActive('/catalogo')}>Catálogo</Link></li>
          {isAuthenticated && (
            <li><Link to="/panel-usuario" className={isActive('/panel-usuario')}>Mi Cuenta</Link></li>
          )}
          {isAuthenticated && isAdmin && (
            <li>
              <Link to="/gestion-usuarios" className={isActive('/gestion-usuarios')}>
                Administración
              </Link>
            </li>
          )}
        </ul>
      </nav>
    </header>
  )
}

export default Header