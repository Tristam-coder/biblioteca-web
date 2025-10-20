import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import Header from '../components/Header'
import Footer from '../components/Footer'
import '../styles/Solicitud_de_Préstamo_.css'

const SolicitudPrestamo = () => {
  const navigate = useNavigate()
  const [currentStep, setCurrentStep] = useState(1)
  const [resourceType, setResourceType] = useState('')
  const [loanDate, setLoanDate] = useState('')
  const [returnDate, setReturnDate] = useState('')
  const [agreeTerms, setAgreeTerms] = useState(false)
  const [loanPurpose, setLoanPurpose] = useState('')

  const handleContinueToDetails = () => {
    if (resourceType) {
      setCurrentStep(2)
    }
  }

  const handleSubmitLoan = (e) => {
    e.preventDefault()
    if (agreeTerms) {
      setCurrentStep(3)
    }
  }

  const handleNewLoan = () => {
    setCurrentStep(1)
    setResourceType('')
    setLoanDate('')
    setReturnDate('')
    setAgreeTerms(false)
    setLoanPurpose('')
  }

  // Calcular fecha de devolución automáticamente (15 días después)
  const calculateReturnDate = (startDate) => {
    if (startDate) {
      const date = new Date(startDate)
      date.setDate(date.getDate() + 15)
      return date.toISOString().split('T')[0]
    }
    return ''
  }

  const handleLoanDateChange = (e) => {
    const date = e.target.value
    setLoanDate(date)
    setReturnDate(calculateReturnDate(date))
  }

  return (
    <div className="solicitud-prestamo-page">
      <Header />
      <main>
        <div className="breadcrumb">
          <Link to="/catalogo">Catálogo - </Link>  
          <Link to="/detalle-obra" id="breadcrumb-book">Detalle de Obra - </Link> 
          <span>Solicitud de Préstamo</span>
        </div>

        <section className="loan-process">
          <h1>Solicitud de Préstamo</h1>
          
          <div className="process-steps">
            <div className={`step ${currentStep === 1 ? 'active' : ''}`}>
              <div className="step-number">1</div>
              <span>Verificación</span>
            </div>
            <div className={`step-connector ${currentStep >= 2 ? 'active' : ''}`}></div>
            <div className={`step ${currentStep === 2 ? 'active' : ''}`}>
              <div className="step-number">2</div>
              <span>Datos del Préstamo</span>
            </div>
            <div className={`step-connector ${currentStep >= 3 ? 'active' : ''}`}></div>
            <div className={`step ${currentStep === 3 ? 'active' : ''}`}>
              <div className="step-number">3</div>
              <span>Confirmación</span>
            </div>
          </div>

          <div className="loan-content">
            {/* Paso 1: Verificación */}
            {currentStep === 1 && (
              <div className="step-content active">
                <div className="step-content-inner">
                  <div className="book-summary">
                    <h2>Resumen del Libro</h2>
                    <div className="book-summary-content">
                      <p><strong>Título:</strong> Cien años de soledad</p>
                      <p><strong>Autor:</strong> Gabriel García Márquez</p>
                      <p><strong>ISBN:</strong> 978-8437604947</p>
                    </div>
                  </div>

                  <div className="verification-section">
                    <h2>Verificar Disponibilidad</h2>
                    <div className="status-message available">
                      El libro está disponible para préstamo
                    </div>

                    <div className="verification-questions">
                      <div className="question">
                        <label htmlFor="resource-type">¿El recurso es virtual o físico?</label>
                        <select 
                          id="resource-type" 
                          value={resourceType}
                          onChange={(e) => setResourceType(e.target.value)}
                        >
                          <option value="">Seleccione una opción</option>
                          <option value="virtual">Virtual</option>
                          <option value="fisico">Físico</option>
                        </select>
                      </div>
                    </div>

                    <div className="step-actions">
                      <button 
                        className="btn-primary" 
                        disabled={!resourceType}
                        onClick={handleContinueToDetails}
                      >
                        Continuar con los Datos del Préstamo
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            )}

            {/* Paso 2: Datos del Préstamo */}
            {currentStep === 2 && (
              <div className="step-content active">
                <div className="step-content-inner">
                  <h2>Datos del Préstamo</h2>
                  
                  <form onSubmit={handleSubmitLoan}>
                    <div className="form-group">
                      <label htmlFor="loan-date">Fecha de Préstamo *</label>
                      <input 
                        type="date" 
                        id="loan-date" 
                        value={loanDate}
                        onChange={handleLoanDateChange}
                        required 
                      />
                    </div>

                    <div className="form-group">
                      <label htmlFor="return-date">Fecha de Devolución *</label>
                      <input 
                        type="date" 
                        id="return-date" 
                        value={returnDate}
                        onChange={(e) => setReturnDate(e.target.value)}
                        required 
                        readOnly
                      />
                      <small className="help-text">
                        La fecha de devolución se calculó automáticamente para 15 días después
                      </small>
                    </div>

                    <div className="form-group">
                      <label htmlFor="loan-purpose">Comentarios del préstamo (Opcional)</label>
                      <textarea 
                        id="loan-purpose" 
                        rows="3" 
                        placeholder="Describa brevemente el propósito del préstamo..."
                        value={loanPurpose}
                        onChange={(e) => setLoanPurpose(e.target.value)}
                      ></textarea>
                    </div>

                    <div className="terms-section">
                      <h3>Términos y Condiciones</h3>
                      <div className="terms-content">
                        <ul>
                          <li>El período máximo de préstamo es de 15 días</li>
                          <li>Se permiten hasta 2 renovaciones por obra</li>
                          <li>La multa por día de retraso es de S/ 5.00</li>
                          <li>Se aplican 3 días de gracia antes del cobro de multas</li>
                          <li>El límite de recursos prestados por usuario es de 5</li>
                          <li>El usuario es responsable del cuidado del material</li>
                        </ul>
                      </div>
                      <div className="terms-agreement">
                        <input 
                          type="checkbox" 
                          id="agree-terms" 
                          checked={agreeTerms}
                          onChange={(e) => setAgreeTerms(e.target.checked)}
                          required 
                        />
                        <label htmlFor="agree-terms">Acepto los términos y condiciones del préstamo</label>
                      </div>
                    </div>

                    <div className="step-actions">
                      <button 
                        type="button" 
                        className="btn-outline" 
                        onClick={() => setCurrentStep(1)}
                      >
                        Volver a Verificación
                      </button>
                      <button 
                        type="submit" 
                        className="btn-primary"
                        disabled={!agreeTerms}
                      >
                        Solicitar Préstamo
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            )}

            {/* Paso 3: Confirmación */}
            {currentStep === 3 && (
              <div className="step-content active">
                <div className="step-content-inner">
                  <div className="confirmation-message">
                    <div className="success-icon">✓</div>
                    <h2>¡Préstamo Registrado Exitosamente!</h2>
                    <p>Su solicitud de préstamo ha sido procesada correctamente.</p>
                    
                    <div className="loan-details-summary">
                      <h3>Detalles del Préstamo</h3>
                      <div className="loan-summary-content">
                        <p><strong>Libro:</strong> Cien años de soledad</p>
                        <p><strong>Autor:</strong> Gabriel García Márquez</p>
                        <p><strong>Fecha de préstamo:</strong> {loanDate}</p>
                        <p><strong>Fecha de devolución:</strong> {returnDate}</p>
                        <p><strong>Tipo:</strong> {resourceType === 'virtual' ? 'Virtual' : 'Físico'}</p>
                        {loanPurpose && <p><strong>Comentarios:</strong> {loanPurpose}</p>}
                      </div>
                    </div>

                    <div className="confirmation-actions">
                      <button className="btn-primary" onClick={() => window.print()}>
                        Generar Comprobante
                      </button>
                      <button className="btn-secondary" onClick={handleNewLoan}>
                        Solicitar Otro Préstamo
                      </button>
                      <Link to="/catalogo" className="btn-outline">
                        Volver al Catálogo
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
            )}
          </div>
        </section>
      </main>
      <Footer />
    </div>
  )
}

export default SolicitudPrestamo