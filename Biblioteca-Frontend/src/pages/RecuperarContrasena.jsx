import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import Header from '../components/Header'
import Footer from '../components/Footer'
import '../styles/RecuperarContrasena.css'

const RecuperarContrasena = () => {
  const navigate = useNavigate()
  const [step, setStep] = useState(1) // 1: Email, 2: Código, 3: Nueva contraseña, 4: Éxito
  const [formData, setFormData] = useState({
    email: '',
    codigo: '',
    nuevaPassword: '',
    confirmarPassword: ''
  })
  const [showPassword, setShowPassword] = useState(false)
  const [showConfirmPassword, setShowConfirmPassword] = useState(false)
  const [countdown, setCountdown] = useState(0)

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    })
  }

  const handleEmailSubmit = (e) => {
    e.preventDefault()
    // Simular envío de código
    console.log('Enviando código a:', formData.email)
    setStep(2)
    setCountdown(60) // 60 segundos para reenviar
    
    // Simular countdown
    const timer = setInterval(() => {
      setCountdown(prev => {
        if (prev <= 1) {
          clearInterval(timer)
          return 0
        }
        return prev - 1
      })
    }, 1000)
  }

  const handleCodigoSubmit = (e) => {
    e.preventDefault()
    // Validar código (en una app real, verificaría con el backend)
    if (formData.codigo.length === 6) {
      setStep(3)
    } else {
      alert('El código debe tener 6 dígitos')
    }
  }

  const handlePasswordSubmit = (e) => {
    e.preventDefault()
    
    if (formData.nuevaPassword !== formData.confirmarPassword) {
      alert('Las contraseñas no coinciden')
      return
    }

    if (formData.nuevaPassword.length < 6) {
      alert('La contraseña debe tener al menos 6 caracteres')
      return
    }

    // Simular cambio de contraseña
    console.log('Cambiando contraseña para:', formData.email)
    setStep(4)
  }

  const handleReenviarCodigo = () => {
    if (countdown === 0) {
      setCountdown(60)
      // Simular reenvío de código
      alert('Código reenviado a tu correo electrónico')
    }
  }

  const renderStep = () => {
    switch (step) {
      case 1:
        return (
          <div className="step-content">
            <div className="step-icon">📧</div>
            <h2>Recuperar Contraseña</h2>
            <p>Ingresa tu correo electrónico y te enviaremos un código de verificación</p>
            
            <form onSubmit={handleEmailSubmit} className="recovery-form">
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
              
              <button type="submit" className="btn-primary">
                Enviar Código de Verificación
              </button>
            </form>
          </div>
        )

      case 2:
        return (
          <div className="step-content">
            <div className="step-icon">🔒</div>
            <h2>Verifica tu Identidad</h2>
            <p>Hemos enviado un código de 6 dígitos a <strong>{formData.email}</strong></p>
            
            <form onSubmit={handleCodigoSubmit} className="recovery-form">
              <div className="form-group">
                <label htmlFor="codigo">Código de Verificación</label>
                <input
                  type="text"
                  id="codigo"
                  name="codigo"
                  value={formData.codigo}
                  onChange={handleChange}
                  placeholder="000000"
                  maxLength="6"
                  required
                  className="code-input"
                />
                <small className="help-text">
                  Ingresa el código de 6 dígitos que recibiste
                </small>
              </div>
              
              <div className="resend-code">
                <p>
                  ¿No recibiste el código?{' '}
                  {countdown > 0 ? (
                    <span className="countdown">Reenviar en {countdown}s</span>
                  ) : (
                    <button 
                      type="button" 
                      className="btn-link"
                      onClick={handleReenviarCodigo}
                    >
                      Reenviar código
                    </button>
                  )}
                </p>
              </div>
              
              <button type="submit" className="btn-primary">
                Verificar Código
              </button>
            </form>
          </div>
        )

      case 3:
        return (
          <div className="step-content">
            <div className="step-icon">🔄</div>
            <h2>Nueva Contraseña</h2>
            <p>Crea una nueva contraseña para tu cuenta</p>
            
            <form onSubmit={handlePasswordSubmit} className="recovery-form">
              <div className="form-group">
                <label htmlFor="nuevaPassword">Nueva Contraseña</label>
                <div className="password-input">
                  <input
                    type={showPassword ? "text" : "password"}
                    id="nuevaPassword"
                    name="nuevaPassword"
                    value={formData.nuevaPassword}
                    onChange={handleChange}
                    placeholder="Ingresa tu nueva contraseña"
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
                <label htmlFor="confirmarPassword">Confirmar Contraseña</label>
                <div className="password-input">
                  <input
                    type={showConfirmPassword ? "text" : "password"}
                    id="confirmarPassword"
                    name="confirmarPassword"
                    value={formData.confirmarPassword}
                    onChange={handleChange}
                    placeholder="Confirma tu nueva contraseña"
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
              
              <button type="submit" className="btn-primary">
                Cambiar Contraseña
              </button>
            </form>
          </div>
        )

      case 4:
        return (
          <div className="step-content success-step">
            <div className="success-icon">✅</div>
            <h2>¡Contraseña Actualizada!</h2>
            <p>Tu contraseña ha sido cambiada exitosamente. Ahora puedes iniciar sesión con tu nueva contraseña.</p>
            
            <div className="success-actions">
              <Link to="/login" className="btn-primary">
                Iniciar Sesión
              </Link>
              <Link to="/" className="btn-outline">
                Volver al Inicio
              </Link>
            </div>
          </div>
        )

      default:
        return null
    }
  }

  return (
    <div className="recuperar-contrasena-page">
      <Header />
      <main className="recovery-main">
        <div className="recovery-container">
          <div className="recovery-card">
            {/* Progress Steps */}
            <div className="progress-steps">
              <div className={`step-indicator ${step >= 1 ? 'active' : ''}`}>
                <div className="step-number">1</div>
                <span>Email</span>
              </div>
              <div className={`step-connector ${step >= 2 ? 'active' : ''}`}></div>
              <div className={`step-indicator ${step >= 2 ? 'active' : ''}`}>
                <div className="step-number">2</div>
                <span>Código</span>
              </div>
              <div className={`step-connector ${step >= 3 ? 'active' : ''}`}></div>
              <div className={`step-indicator ${step >= 3 ? 'active' : ''}`}>
                <div className="step-number">3</div>
                <span>Nueva Pass</span>
              </div>
              <div className={`step-connector ${step >= 4 ? 'active' : ''}`}></div>
              <div className={`step-indicator ${step >= 4 ? 'active' : ''}`}>
                <div className="step-number">4</div>
                <span>Listo</span>
              </div>
            </div>

            {/* Step Content */}
            {renderStep()}

            {/* Back to Login */}
            {step < 4 && (
              <div className="recovery-footer">
                <p>
                  ¿Recordaste tu contraseña? <Link to="/login">Inicia sesión aquí</Link>
                </p>
              </div>
            )}
          </div>

          <div className="recovery-info">
            <h2>Seguridad de tu Cuenta</h2>
            <div className="security-tips">
              <div className="tip">
                <span className="tip-icon">🔐</span>
                <div>
                  <h3>Contraseña Segura</h3>
                  <p>Usa una combinación de letras, números y símbolos</p>
                </div>
              </div>
              <div className="tip">
                <span className="tip-icon">📧</span>
                <div>
                  <h3>Email de Recuperación</h3>
                  <p>Asegúrate de tener acceso a tu correo electrónico</p>
                </div>
              </div>
              <div className="tip">
                <span className="tip-icon">⏱️</span>
                <div>
                  <h3>Código Temporal</h3>
                  <p>El código de verificación expira en 10 minutos</p>
                </div>
              </div>
              <div className="tip">
                <span className="tip-icon">🛡️</span>
                <div>
                  <h3>Protección de Cuenta</h3>
                  <p>Tu información está protegida con encriptación</p>
                </div>
              </div>
            </div>

            <div className="emergency-contact">
              <h3>¿Problemas para recuperar tu cuenta?</h3>
              <p>Contacta a nuestro soporte técnico:</p>
              <div className="contact-info">
                <p>📧 soporte@saberum.com</p>
                <p>📞 +51 1 234-5678</p>
              </div>
            </div>
          </div>
        </div>
      </main>
      <Footer />
    </div>
  )
}

export default RecuperarContrasena