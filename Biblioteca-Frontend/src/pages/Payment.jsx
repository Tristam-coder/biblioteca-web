import React, { useState, useEffect } from 'react'
import { Link, useNavigate, useLocation } from 'react-router-dom'
import Header from '../components/Header'
import Footer from '../components/Footer'
import '../styles/Página_de_Pago_.css'

const PaginaPago = () => {
  const navigate = useNavigate()
  const location = useLocation()
  const [currentStep, setCurrentStep] = useState(1)
  const [paymentMethod, setPaymentMethod] = useState('')
  
  // Obtener datos de las multas desde el estado de navegación
  const [paymentData, setPaymentData] = useState({
    fines: [],
    total: 0
  })

  useEffect(() => {
    if (location.state) {
      setPaymentData({
        fines: location.state.fines || [],
        total: location.state.total || 0
      })
    } else {
      // Datos por defecto si no se pasan desde el panel
      setPaymentData({
        fines: [{
          concepto: 'Multa por retraso',
          libro: 'Don Quijote de la Mancha',
          monto: 15.00
        }],
        total: 15.00
      })
    }
  }, [location.state])

  const handleContinueToPayment = () => {
    setCurrentStep(2)
  }

  const handleContinueToConfirm = () => {
    if (paymentMethod) {
      setCurrentStep(3)
    }
  }

  const handleBackToSummary = () => {
    setCurrentStep(1)
  }

  // Función para formatear el método de pago
  const getPaymentMethodText = (method) => {
    const methods = {
      'yape': 'Yape',
      'tarjeta': 'Tarjeta Crédito/Débito',
      'transferencia': 'Transferencia Bancaria',
      'efectivo': 'Pago en Efectivo'
    }
    return methods[method] || method
  }

  return (
    <div className="pagina-pago-page">
      <Header />
      <main>
        <div className="breadcrumb">
          <Link to="/panel-usuario">Mi Cuenta - </Link>  
          <span>Pago</span>
        </div>

        <div className="payment-container">
          <div className="payment-header">
            <h1>Proceso de Pago</h1>
            <div className="payment-steps">
              <div className={`step ${currentStep === 1 ? 'active' : ''}`}>1. Resumen</div>
              <div className={`step ${currentStep === 2 ? 'active' : ''}`}>2. Método de Pago</div>
              <div className={`step ${currentStep === 3 ? 'active' : ''}`}>3. Confirmación</div>
            </div>
          </div>

          {/* Paso 1: Resumen del Pago - ACTUALIZADO */}
          <div id="step-summary" className={`payment-step ${currentStep === 1 ? 'active' : ''}`}>
            <div className="payment-summary">
              <h2>Resumen del Pago</h2>
              <div className="summary-details">
                <div className="summary-item">
                  <h3>Detalles de la Transacción</h3>
                  <div className="details-list" id="transaction-details">
                    <p><strong>Usuario:</strong> Juan Pérez</p>
                    <p><strong>Fecha:</strong> {new Date().toLocaleDateString()}</p>
                    <p><strong>Referencia:</strong> MUL-{Date.now().toString().slice(-6)}</p>
                    <p><strong>Cantidad de items:</strong> {paymentData.fines.length}</p>
                  </div>
                </div>

                <div className="summary-item">
                  <h3>Cantidad(es) a Pagar</h3>
                  <div className="fines-list" id="fines-summary">
                    {paymentData.fines.map((fine, index) => (
                      <div key={index} className="fine-item">
                        <span>
                          {fine.tipo === 'todas' 
                            ? 'Todas las multas pendientes' 
                            : `${fine.concepto} - ${fine.libro}`
                          }
                        </span>
                        <span>S/ {fine.monto ? fine.monto.toFixed(2) : '0.00'}</span>
                      </div>
                    ))}
                  </div>
                  <div className="total-amount">
                    <strong>Total a Pagar: <span id="total-payment">S/ {paymentData.total.toFixed(2)}</span></strong>
                  </div>
                </div>
              </div>

              <div className="payment-actions">
                <button id="continue-to-payment" className="btn-primary" onClick={handleContinueToPayment}>
                  Continuar el Pago
                </button>
                <Link to="/panel-usuario" className="btn-outline">
                  Volver al Panel
                </Link>
              </div>
            </div>
          </div>

          {/* Paso 2: Selección de Método de Pago */}
          <div id="step-payment-method" className={`payment-step ${currentStep === 2 ? 'active' : ''}`}>
            <div className="payment-methods">
              <h2>Seleccione Método de Pago</h2>
              
              <div className="methods-grid">
                <div className={`method-card ${paymentMethod === 'yape' ? 'selected' : ''}`}>
                  <input 
                    type="radio" 
                    id="method-yape" 
                    name="payment-method" 
                    value="yape"
                    checked={paymentMethod === 'yape'}
                    onChange={(e) => setPaymentMethod(e.target.value)}
                  />
                  <label htmlFor="method-yape">
                    <img src="/IMG/yape.png" alt="Yape" />
                    <span>Yape</span>
                  </label>
                </div>

                <div className={`method-card ${paymentMethod === 'tarjeta' ? 'selected' : ''}`}>
                  <input 
                    type="radio" 
                    id="method-card" 
                    name="payment-method" 
                    value="tarjeta"
                    checked={paymentMethod === 'tarjeta'}
                    onChange={(e) => setPaymentMethod(e.target.value)}
                  />
                  <label htmlFor="method-card">
                    <img src="/IMG/Cred_Debi.jpg" alt="Tarjeta" />
                    <span>Tarjeta Crédito/Débito</span>
                  </label>
                </div>

                <div className={`method-card ${paymentMethod === 'transferencia' ? 'selected' : ''}`}>
                  <input 
                    type="radio" 
                    id="method-transfer" 
                    name="payment-method" 
                    value="transferencia"
                    checked={paymentMethod === 'transferencia'}
                    onChange={(e) => setPaymentMethod(e.target.value)}
                  />
                  <label htmlFor="method-transfer">
                    <img src="/IMG/transferencia.png" alt="Transferencia" />
                    <span>Transferencia Bancaria</span>
                  </label>
                </div>

                <div className={`method-card ${paymentMethod === 'efectivo' ? 'selected' : ''}`}>
                  <input 
                    type="radio" 
                    id="method-cash" 
                    name="payment-method" 
                    value="efectivo"
                    checked={paymentMethod === 'efectivo'}
                    onChange={(e) => setPaymentMethod(e.target.value)}
                  />
                  <label htmlFor="method-cash">
                    <img src="/IMG/efectivo.png" alt="Efectivo" />
                    <span>Pago en Efectivo</span>
                  </label>
                </div>
              </div>

              <div className="payment-actions">
                <button id="back-to-summary" className="btn-outline" onClick={handleBackToSummary}>
                  Volver al Resumen
                </button>
                <button 
                  id="continue-to-confirm" 
                  className="btn-primary" 
                  disabled={!paymentMethod}
                  onClick={handleContinueToConfirm}
                >
                  Continuar a Confirmación
                </button>
              </div>
            </div>
          </div>

          {/* Paso 3: Confirmación del Pago - ACTUALIZADO */}
          <div id="step-confirmation" className={`payment-step ${currentStep === 3 ? 'active' : ''}`}>
            <div className="payment-confirmation">
              <div className="confirmation-header">
                <div className="success-icon">✓</div>
                <h2>¡Pago Procesado Exitosamente!</h2>
                <p>Su transacción ha sido completada correctamente.</p>
              </div>

              <div className="confirmation-details">
                <div className="detail-section">
                  <h3>Detalles de la Transacción</h3>
                  <div className="details-grid" id="transaction-confirmation">
                    <div className="detail-item">
                      <span>Número de Transacción:</span>
                      <span>TRX-{Date.now().toString().slice(-8)}</span>
                    </div>
                    <div className="detail-item">
                      <span>Fecha y Hora:</span>
                      <span>{new Date().toLocaleString()}</span>
                    </div>
                    <div className="detail-item">
                      <span>Método de Pago:</span>
                      <span id="payment-method-used">{getPaymentMethodText(paymentMethod)}</span>
                    </div>
                    <div className="detail-item">
                      <span>Monto Total:</span>
                      <span>S/ {paymentData.total.toFixed(2)}</span>
                    </div>
                    <div className="detail-item">
                      <span>Estado:</span>
                      <span className="status-success">Completado</span>
                    </div>
                  </div>
                </div>

                <div className="items-section">
                  <h3>Items Pagados</h3>
                  <div className="items-list">
                    {paymentData.fines.map((fine, index) => (
                      <div key={index} className="item-detail">
                        <span>
                          {fine.tipo === 'todas' 
                            ? 'Todas las multas pendientes' 
                            : `${fine.concepto} - ${fine.libro}`
                          }
                        </span>
                        <span>S/ {fine.monto ? fine.monto.toFixed(2) : '0.00'}</span>
                      </div>
                    ))}
                  </div>
                </div>

                <div className="receipt-section">
                  <h3>Comprobante de Pago</h3>
                  <div className="receipt-actions">
                    <button id="print-receipt" className="btn-primary" onClick={() => window.print()}>
                      Imprimir Comprobante
                    </button>
                    <button id="download-receipt" className="btn-secondary">
                      Descargar PDF
                    </button>
                  </div>
                </div>
              </div>

              <div className="confirmation-actions">
                <Link to="/panel-usuario" className="btn-primary">
                  Volver al Panel de Usuario
                </Link>
                <Link to="/catalogo" className="btn-outline">
                  Ir al Catálogo
                </Link>
              </div>
            </div>
          </div>
        </div>
      </main>
      <Footer />
    </div>
  )
}

export default PaginaPago