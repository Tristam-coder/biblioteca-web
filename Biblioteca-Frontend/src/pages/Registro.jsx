import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import Header from '../components/Header'
import Footer from '../components/Footer'
import '../styles/Registro.css'

const Registro = () => {
  const navigate = useNavigate()
  const [formData, setFormData] = useState({
    tipoUsuario: 'socio',
    dni: '',
    nombres: '',
    apellidos: '',
    email: '',
    telefono: '',
    direccion: '',
    password: '',
    confirmPassword: '',
    aceptaTerminos: false
  })
  const [showPassword, setShowPassword] = useState(false)
  const [showConfirmPassword, setShowConfirmPassword] = useState(false)

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target
    setFormData(prev => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value
    }))
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    
    // Validaciones básicas
    if (formData.password !== formData.confirmPassword) {
      alert('Las contraseñas no coinciden')
      return
    }

    if (!formData.aceptaTerminos) {
      alert('Debe aceptar los términos y condiciones')
      return
    }
    // Simular registro exitoso y guardar en sessionStorage
    sessionStorage.setItem('isAuthenticated', 'true')
    sessionStorage.setItem('isAdmin', 'false')
    sessionStorage.setItem('userName', formData.nombres)
    
    console.log('Datos de registro:', formData)
    
    // Redirigir al panel de usuario
    navigate('/panel-usuario', { 
      state: { message: '¡Registro exitoso! Ahora puedes iniciar sesión.' }
    })
  }

  return (
    <div className="registro-page">
      <Header />
      <main className="registro-main">
        <div className="registro-container">
          <div className="registro-card">
            <div className="registro-header">
              <h1>Crear Cuenta</h1>
              <p>Únete a Ediciones Saberum y descubre un mundo de conocimiento</p>
            </div>

            <form className="registro-form" onSubmit={handleSubmit}>
              <div className="form-section">
                <h3>Información Personal</h3>
                <div className="form-grid">
                  <div className="form-group">
                    <label htmlFor="tipoUsuario">Tipo de Usuario *</label>
                    <select 
                      id="tipoUsuario"
                      name="tipoUsuario"
                      value={formData.tipoUsuario}
                      onChange={handleChange}
                      required
                    >
                      <option value="socio">Socio</option>
                      <option value="estudiante">Estudiante</option>
                      <option value="profesor">Profesor</option>
                      <option value="investigador">Investigador</option>
                    </select>
                  </div>

                  <div className="form-group">
                    <label htmlFor="dni">DNI *</label>
                    <input
                      type="text"
                      id="dni"
                      name="dni"
                      value={formData.dni}
                      onChange={handleChange}
                      placeholder="Ingresa tu DNI"
                      required
                      maxLength="8"
                    />
                  </div>

                  <div className="form-group">
                    <label htmlFor="nombres">Nombres *</label>
                    <input
                      type="text"
                      id="nombres"
                      name="nombres"
                      value={formData.nombres}
                      onChange={handleChange}
                      placeholder="Ingresa tus nombres"
                      required
                    />
                  </div>

                  <div className="form-group">
                    <label htmlFor="apellidos">Apellidos *</label>
                    <input
                      type="text"
                      id="apellidos"
                      name="apellidos"
                      value={formData.apellidos}
                      onChange={handleChange}
                      placeholder="Ingresa tus apellidos"
                      required
                    />
                  </div>

                  <div className="form-group">
                    <label htmlFor="email">Correo Electrónico *</label>
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
                    <label htmlFor="telefono">Teléfono</label>
                    <input
                      type="tel"
                      id="telefono"
                      name="telefono"
                      value={formData.telefono}
                      onChange={handleChange}
                      placeholder="+51 987 654 321"
                    />
                  </div>

                  <div className="form-group full-width">
                    <label htmlFor="direccion">Dirección</label>
                    <textarea
                      id="direccion"
                      name="direccion"
                      value={formData.direccion}
                      onChange={handleChange}
                      placeholder="Ingresa tu dirección completa"
                      rows="3"
                    />
                  </div>
                </div>
              </div>

              <div className="form-section">
                <h3>Seguridad</h3>
                <div className="form-grid">
                  <div className="form-group">
                    <label htmlFor="password">Contraseña *</label>
                    <div className="password-input">
                      <input
                        type={showPassword ? "text" : "password"}
                        id="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        placeholder="Crea una contraseña segura"
                        required
                        minLength="6"
                      />
                      <button
                        type="button"
                        className="password-toggle"
                        onClick={() => setShowPassword(!showPassword)}
                      >
                        {showPassword ? '🙈' : '👁️'}
                      </button>
                    </div>
                    <small className="help-text">
                      Mínimo 6 caracteres
                    </small>
                  </div>

                  <div className="form-group">
                    <label htmlFor="confirmPassword">Confirmar Contraseña *</label>
                    <div className="password-input">
                      <input
                        type={showConfirmPassword ? "text" : "password"}
                        id="confirmPassword"
                        name="confirmPassword"
                        value={formData.confirmPassword}
                        onChange={handleChange}
                        placeholder="Repite tu contraseña"
                        required
                      />
                      <button
                        type="button"
                        className="password-toggle"
                        onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                      >
                        {showConfirmPassword ? '🙈' : '👁️'}
                      </button>
                    </div>
                  </div>
                </div>
              </div>

              <div className="form-section">
                <div className="terms-agreement">
                  <label className="checkbox-label">
                    <input
                      type="checkbox"
                      name="aceptaTerminos"
                      checked={formData.aceptaTerminos}
                      onChange={handleChange}
                      required
                    />
                    <span className="checkmark"></span>
                    Acepto los <Link to="/terminos" target="_blank">Términos y Condiciones</Link> y la <Link to="/privacidad" target="_blank">Política de Privacidad</Link> *
                  </label>
                </div>

                <div className="terms-agreement">
                  <label className="checkbox-label">
                    <input
                      type="checkbox"
                      name="aceptaNewsletter"
                    />
                    <span className="checkmark"></span>
                    Deseo recibir información sobre novedades y promociones
                  </label>
                </div>
              </div>

              <button type="submit" className="btn-registro-submit">
                Crear Cuenta
              </button>
            </form>

            <div className="registro-footer">
              <p>
                ¿Ya tienes una cuenta? <Link to="/login">Inicia sesión aquí</Link>
              </p>
            </div>
          </div>

          <div className="registro-benefits">
            <h2>Beneficios Exclusivos</h2>
            <div className="benefits-list">
              <div className="benefit">
                <span className="benefit-icon">🎁</span>
                <div>
                  <h3>Registro Gratuito</h3>
                  <p>Acceso inmediato sin costo alguno</p>
                </div>
              </div>
              
              <div className="benefit">
                <span className="benefit-icon">📚</span>
                <div>
                  <h3>Catálogo Completo</h3>
                  <p>Acceso a miles de libros y recursos</p>
                </div>
              </div>
              
              <div className="benefit">
                <span className="benefit-icon">⏰</span>
                <div>
                  <h3>Reservas Anticipadas</h3>
                  <p>Reserva libros antes que otros</p>
                </div>
              </div>
              
              <div className="benefit">
                <span className="benefit-icon">💼</span>
                <div>
                  <h3>Perfil Personalizado</h3>
                  <p>Recomendaciones basadas en tus intereses</p>
                </div>
              </div>
              
              <div className="benefit">
                <span className="benefit-icon">🔔</span>
                <div>
                  <h3>Notificaciones</h3>
                  <p>Alertas de nuevos libros y vencimientos</p>
                </div>
              </div>
              
              <div className="benefit">
                <span className="benefit-icon">📊</span>
                <div>
                  <h3>Historial Detallado</h3>
                  <p>Seguimiento de todos tus préstamos</p>
                </div>
              </div>
            </div>

            <div className="user-types-info">
              <h3>Tipos de Usuario</h3>
              <div className="user-types">
                <div className="user-type">
                  <strong>Socio:</strong> Acceso general a todos los servicios
                </div>
                <div className="user-type">
                  <strong>Estudiante:</strong> Préstamos extendidos y recursos académicos
                </div>
                <div className="user-type">
                  <strong>Profesor:</strong> Acceso a material docente y reservas prioritarias
                </div>
                <div className="user-type">
                  <strong>Investigador:</strong> Recursos especializados y préstamos a largo plazo
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

export default Registro