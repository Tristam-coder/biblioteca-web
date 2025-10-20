import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import Header from '../components/Header'
import Footer from '../components/Footer'
import '../styles/Login.css'

const Login = () => {
  const navigate = useNavigate()
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    userType: 'socio'
  })
  const [showPassword, setShowPassword] = useState(false)

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    })
  }

  const handleSubmit = (e) => {
    // Guardar en sessionStorage
    sessionStorage.setItem('isAuthenticated', 'true')
    sessionStorage.setItem('isAdmin', formData.userType === 'admin' ? 'true' : 'false')
    sessionStorage.setItem('userName', formData.email.split('@')[0])
    
    console.log('Datos de login:', formData)
    
    // Redirigir según el tipo de usuario
    if (formData.userType === 'admin') {
      navigate('/gestion-usuarios')
    } else {
      navigate('/panel-usuario')
    }
  }

  return (
    <div className="login-page">
      <Header />
      <main className="login-main">
        <div className="login-container">
          <div className="login-card">
            <div className="login-header">
              <h1>Iniciar Sesión</h1>
              <p>Accede a tu cuenta de Ediciones Saberum</p>
            </div>

            <form className="login-form" onSubmit={handleSubmit}>
              <div className="form-group">
                <label htmlFor="userType">Tipo de Usuario</label>
                <select 
                  id="userType"
                  name="userType"
                  value={formData.userType}
                  onChange={handleChange}
                  required
                >
                  <option value="socio">Socio</option>
                  <option value="estudiante">Estudiante</option>
                  <option value="profesor">Profesor</option>
                  <option value="admin">Administrador</option>
                </select>
              </div>

              <div className="form-group">
                <label htmlFor="email">Correo Electrónico</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  placeholder="usuario@ejemplo.com"
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="password">Contraseña</label>
                <div className="password-input">
                  <input
                    type={showPassword ? "text" : "password"}
                    id="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    placeholder="Ingresa tu contraseña"
                    required
                  />
                  <button
                    type="button"
                    className="password-toggle"
                    onClick={() => setShowPassword(!showPassword)}
                  >
                    {showPassword ? '🙈' : '👁️'}
                  </button>
                </div>
              </div>

              <div className="form-options">
                <label className="remember-me">
                  <input type="checkbox" />
                  Recordar sesión
                </label>
                <Link to="/recuperar-contrasena" className="forgot-password">
                    ¿Olvidaste tu contraseña?
                </Link>
              </div>

              <button type="submit" className="btn-login-submit">
                Iniciar Sesión
              </button>
            </form>

            <div className="login-footer">
              <p>¿No tienes una cuenta? <Link to="/registro">Regístrate aquí</Link></p>
              
              <div className="demo-accounts">
                <h3>Cuentas de Demo:</h3>
                <div className="demo-account">
                  <strong>Admin:</strong> admin@saberum.com / admin123
                </div>
                <div className="demo-account">
                  <strong>Usuario:</strong> usuario@saberum.com / user123
                </div>
              </div>
            </div>
          </div>

          <div className="login-features">
            <h2>Beneficios de tu cuenta</h2>
            <div className="features-list">
              <div className="feature">
                <span className="feature-icon">📚</span>
                <div>
                  <h3>Préstamos accesibes</h3>
                  <p>Accede a nuestro catálogo completo</p>
                </div>
              </div>
              <div className="feature">
                <span className="feature-icon">⏰</span>
                <div>
                  <h3>Reservas anticipadas</h3>
                  <p>Reserva libros antes de su disponibilidad</p>
                </div>
              </div>
              <div className="feature">
                <span className="feature-icon">🔔</span>
                <div>
                  <h3>Notificaciones</h3>
                  <p>Recibe alertas de vencimientos y novedades</p>
                </div>
              </div>
              <div className="feature">
                <span className="feature-icon">📊</span>
                <div>
                  <h3>Historial personal</h3>
                  <p>Consulta tu historial de préstamos y lecturas</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
      <Footer />
    </div>
  )
}

export default Login