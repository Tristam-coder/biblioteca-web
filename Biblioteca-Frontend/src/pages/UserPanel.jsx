import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import Header from '../components/Header'
import Footer from '../components/Footer'
import '../styles/Panel_de_Usuario_.css'

const PanelUsuario = () => {
  const navigate = useNavigate()
  const [activeTab, setActiveTab] = useState('prestamos-activos')
  const [showPaymentModal, setShowPaymentModal] = useState(false)
  const [selectedFine, setSelectedFine] = useState(null)

  // Datos de ejemplo para multas
  const finesData = [
    {
      id: 1,
      concepto: 'Retraso en devolución',
      libro: 'Don Quijote de la Mancha',
      monto: 15.00,
      fecha: '2025-10-17',
      estado: 'pendiente'
    },
    {
      id: 2,
      concepto: 'Daño en material',
      libro: 'Cien años de soledad',
      monto: 25.00,
      fecha: '2025-10-09',
      estado: 'pendiente'
    }
  ]

  const totalFines = finesData.reduce((sum, fine) => sum + fine.monto, 0)
  const pendingFinesCount = finesData.filter(fine => fine.estado === 'pendiente').length

  const handleLogout = () => {
    if (window.confirm('¿Está seguro de que desea cerrar sesión?')) {
      navigate('/catalogo')
    }
  }

  const handlePayment = (fine) => {
    setSelectedFine(fine)
    setShowPaymentModal(true)
  }

  const handlePayAllFines = () => {
    setSelectedFine({ 
      tipo: 'todas', 
      monto: totalFines,
      concepto: 'Todas las multas pendientes'
    })
    setShowPaymentModal(true)
  }

  const confirmPayment = () => {
    // Simular proceso de pago
    console.log('Procesando pago:', selectedFine)
    setShowPaymentModal(false)
    setSelectedFine(null)
    
    // Redirigir a la página de pago con los datos
    navigate('/pago', { 
      state: { 
        fines: selectedFine.tipo === 'todas' ? finesData : [selectedFine],
        total: selectedFine.monto
      }
    })
  }

  return (
  <div className="panel-usuario-page">
    <Header />
    <main>
      <div className="user-dashboard">
        <aside className="user-sidebar">
          <div className="user-profile">
            <div className="profile-avatar">
              <img src="https://via.placeholder.com/80x80/3498db/ffffff?text=U" alt="Avatar del usuario" id="user-avatar" />
            </div>
            <div className="profile-info">
              <h2 id="user-name">Juan Pérez</h2>
              <p id="user-email">juan.perez@email.com</p>
              <p id="user-type">Tipo: Socio</p>
              <p id="user-status" className="status-active">Estado: Activo</p>
            </div>
          </div>

          <nav className="sidebar-nav">
            <ul>
              <li>
                <a 
                  href="#prestamos-activos" 
                  className={`nav-link ${activeTab === 'prestamos-activos' ? 'active' : ''}`}
                  onClick={(e) => { e.preventDefault(); setActiveTab('prestamos-activos') }}
                >
                  <span className="nav-icon">📚</span>
                  <span className="nav-text">Préstamos Activos</span>
                </a>
              </li>
              <li>
                <a 
                  href="#reservas-pendientes" 
                  className={`nav-link ${activeTab === 'reservas-pendientes' ? 'active' : ''}`}
                  onClick={(e) => { e.preventDefault(); setActiveTab('reservas-pendientes') }}
                >
                  <span className="nav-icon">⏰</span>
                  <span className="nav-text">Reservas Pendientes</span>
                </a>
              </li>
              <li>
                <a 
                  href="#multas" 
                  className={`nav-link ${activeTab === 'multas' ? 'active' : ''}`}
                  onClick={(e) => { e.preventDefault(); setActiveTab('multas') }}
                >
                  <span className="nav-icon">⚠️</span>
                  <span className="nav-text">Multas y Sanciones</span>
                  {pendingFinesCount > 0 && (
                    <span className="notification-badge">{pendingFinesCount}</span>
                  )}
                </a>
              </li>
              <li>
                <a 
                  href="#perfil" 
                  className={`nav-link ${activeTab === 'perfil' ? 'active' : ''}`}
                  onClick={(e) => { e.preventDefault(); setActiveTab('perfil') }}
                >
                  <span className="nav-icon">👤</span>
                  <span className="nav-text">Mi Perfil</span>
                </a>
              </li>
              <li>
                <a 
                  href="#notificaciones" 
                  className={`nav-link ${activeTab === 'notificaciones' ? 'active' : ''}`}
                  onClick={(e) => { e.preventDefault(); setActiveTab('notificaciones') }}
                >
                  <span className="nav-icon">🔔</span>
                  <span className="nav-text">Notificaciones</span>
                </a>
              </li>
            </ul>
          </nav>
        </aside>

          <section className="user-content">
            {/* Pestaña: Préstamos Activos */}
            <div id="prestamos-activos" className={`tab-content ${activeTab === 'prestamos-activos' ? 'active' : ''}`}>
              <div className="tab-header">
                <h2>Préstamos Activos</h2>
                <div className="tab-stats">
                  <span className="stat-item">
                    <strong id="active-loans-count">2</strong> préstamos activos
                  </span>
                  <span className="stat-item">
                    <strong id="near-due-count">1</strong> por vencer
                  </span>
                </div>
              </div>

              <div className="loans-list">
                <div className="loan-item">
                  <div className="loan-info">
                    <h3>Cien años de soledad</h3>
                    <p>Autor: Gabriel García Márquez</p>
                    <p>Fecha de préstamo: 2025-09-01</p>
                    <p>Fecha de devolución: <strong>2025-09-15</strong></p>
                  </div>
                  <span className="status warning">Por vencer</span>
                </div>
                <div className="loan-item">
                  <div className="loan-info">
                    <h3>1984</h3>
                    <p>Autor: George Orwell</p>
                    <p>Fecha de préstamo: 2025-10-05</p>
                    <p>Fecha de devolución: <strong>2025-10-15</strong></p>
                  </div>
                  <span className="status success">En tiempo</span>
                </div>
              </div>

              <div className="empty-state" style={{ display: 'none' }} id="empty-active-loans">
                <div className="empty-icon">📚</div>
                <h3>No tienes préstamos activos</h3>
                <p>Visita nuestro catálogo para encontrar libros interesantes.</p>
                <Link to="/catalogo" className="btn-primary">Explorar Catálogo</Link>
              </div>
            </div>

            {/* Pestaña: Reservas Pendientes */}
            <div id="reservas-pendientes" className={`tab-content ${activeTab === 'reservas-pendientes' ? 'active' : ''}`}>
              <div className="tab-header">
                <h2>Reservas Pendientes</h2>
                <div className="tab-stats">
                  <span className="stat-item">
                    <strong id="pending-reservations-count">1</strong> reservas activas
                  </span>
                </div>
              </div>

              <div className="reservations-list">
                <div className="reservation-item">
                  <div className="reservation-info">
                    <h3>El amor en los tiempos del cólera</h3>
                    <p>Autor: Gabriel García Márquez</p>
                    <p>Fecha de reserva: 2025-10-18</p>
                    <p>Estado: En espera de disponibilidad</p>
                  </div>
                  <span className="status info">Disponible en 3 días</span>
                </div>
              </div>

              <div className="empty-state" style={{ display: 'none' }} id="empty-reservations">
                <div className="empty-icon">⏳</div>
                <h3>No tienes reservas pendientes</h3>
                <p>Puedes reservar libros que estén actualmente prestados.</p>
                <Link to="/catalogo" className="btn-primary">Buscar Libros</Link>
              </div>
            </div>

            {/* Pestaña: Multas y Sanciones - ACTUALIZADA */}
            <div id="multas" className={`tab-content ${activeTab === 'multas' ? 'active' : ''}`}>
              <div className="tab-header">
                <h2>Multas y Sanciones</h2>
                <div className="tab-stats">
                  <span className="stat-item total-amount">
                    Total pendiente: <strong id="total-fines">S/ {totalFines.toFixed(2)}</strong>
                  </span>
                  <span className="stat-item">
                    <strong id="pending-fines-count">{pendingFinesCount}</strong> multas pendientes
                  </span>
                </div>
              </div>

              {finesData.length > 0 ? (
                <>
                  <div className="fines-list">
                    {finesData.map((fine) => (
                      <div key={fine.id} className="fine-item">
                        <div className="fine-info">
                          <h3>{fine.concepto}</h3>
                          <p>Libro: {fine.libro}</p>
                          <p>Fecha: {fine.fecha}</p>
                          <p className="fine-amount">Monto: <strong>S/ {fine.monto.toFixed(2)}</strong></p>
                        </div>
                        <div className="fine-actions">
                          <span className={`status ${fine.estado === 'pendiente' ? 'error' : 'success'}`}>
                            {fine.estado === 'pendiente' ? 'Pendiente' : 'Pagada'}
                          </span>
                          {fine.estado === 'pendiente' && (
                            <button 
                              className="btn-pay"
                              onClick={() => handlePayment(fine)}
                            >
                              Pagar Multa
                            </button>
                          )}
                        </div>
                      </div>
                    ))}
                  </div>

                  <div className="payment-section">
                    <div className="payment-header">
                      <h3>Opciones de Pago</h3>
                      <p>Selecciona cómo deseas realizar el pago</p>
                    </div>
                    
                    <div className="payment-actions">
                      <button 
                        className="btn-pay-all"
                        onClick={handlePayAllFines}
                      >
                        💳 Pagar Todas las Multas (S/ {totalFines.toFixed(2)})
                      </button>
                      
                      <div className="payment-methods">
                        <h4>O pagar multas individuales:</h4>
                        <div className="methods-grid">
                          {finesData.filter(fine => fine.estado === 'pendiente').map(fine => (
                            <button 
                              key={fine.id}
                              className="payment-method"
                              onClick={() => handlePayment(fine)}
                            >
                              <span className="method-amount">S/ {fine.monto.toFixed(2)}</span>
                              <span className="method-description">{fine.concepto}</span>
                              <small>{fine.libro}</small>
                            </button>
                          ))}
                        </div>
                      </div>
                    </div>
                  </div>
                </>
              ) : (
                <div className="empty-state" id="empty-fines">
                  <div className="empty-icon">✅</div>
                  <h3>No tienes multas pendientes</h3>
                  <p>¡Mantén tus préstamos al día!</p>
                </div>
              )}
            </div>

            {/* Pestaña: Mi Perfil */}
            <div id="perfil" className={`tab-content ${activeTab === 'perfil' ? 'active' : ''}`}>
              <div className="tab-header">
                <h2>Mi Perfil</h2>
              </div>

              <form className="profile-form">
                <div className="form-section">
                  <h3>Información Personal</h3>
                  <div className="form-grid">
                    <div className="form-group">
                      <label htmlFor="profile-dni">DNI *</label>
                      <input type="text" id="profile-dni" defaultValue="12345678" required />
                    </div>
                    <div className="form-group">
                      <label htmlFor="profile-name">Nombres *</label>
                      <input type="text" id="profile-name" defaultValue="Juan" required />
                    </div>
                    <div className="form-group">
                      <label htmlFor="profile-lastname">Apellidos *</label>
                      <input type="text" id="profile-lastname" defaultValue="Pérez" required />
                    </div>
                    <div className="form-group">
                      <label htmlFor="profile-email">Correo Electrónico *</label>
                      <input type="email" id="profile-email" defaultValue="juan.perez@email.com" required />
                    </div>
                    <div className="form-group">
                      <label htmlFor="profile-phone">Teléfono</label>
                      <input type="tel" id="profile-phone" defaultValue="987654321" />
                    </div>
                    <div className="form-group full-width">
                      <label htmlFor="profile-address">Dirección</label>
                      <textarea id="profile-address" rows="3" defaultValue="Av. Principal 123"></textarea>
                    </div>
                  </div>
                </div>

                <div className="form-actions">
                  <button type="submit" className="btn-primary">Guardar Cambios</button>
                  <button type="button" className="btn-outline">Cancelar</button>
                </div>
              </form>
            </div>

            {/* Pestaña: Notificaciones */}
            <div id="notificaciones" className={`tab-content ${activeTab === 'notificaciones' ? 'active' : ''}`}>
              <div className="tab-header">
                <h2>Notificaciones</h2>
                <div className="notification-actions">
                  <button className="btn-outline">Marcar todas como leídas</button>
                  <button className="btn-outline">Limpiar notificaciones</button>
                </div>
              </div>

              <div className="notifications-list">
                <div className="notification-item">
                  <div className="notification-icon">📚</div>
                  <div className="notification-content">
                    <h4>Préstamo por vencer</h4>
                    <p>Tu préstamo de "Cien años de soledad" vence en 2 días</p>
                    <small>Hace 1 día</small>
                  </div>
                </div>
                <div className="notification-item">
                  <div className="notification-icon">💰</div>
                  <div className="notification-content">
                    <h4>Multa generada</h4>
                    <p>Se ha generado una multa por retraso en la devolución</p>
                    <small>Hace 3 días</small>
                  </div>
                </div>
              </div>

              <div className="empty-state" style={{ display: 'none' }} id="empty-notifications">
                <div className="empty-icon">🔔</div>
                <h3>No tienes notificaciones</h3>
                <p>Las notificaciones importantes aparecerán aquí.</p>
              </div>
            </div>
          </section>
        </div>
      </main>

      {/* Modal para confirmación de pago */}
      {showPaymentModal && (
        <div className="modal active">
          <div className="modal-content">
            <div className="modal-header">
              <h3>Confirmar Pago</h3>
              <button onClick={() => setShowPaymentModal(false)}>&times;</button>
            </div>
            <div className="modal-body">
              <div className="payment-confirmation">
                {selectedFine && (
                  <>
                    <div className="payment-details">
                      <h4>Detalles del Pago</h4>
                      {selectedFine.tipo === 'todas' ? (
                        <div className="payment-item">
                          <p><strong>Concepto:</strong> Todas las multas pendientes</p>
                          <p><strong>Cantidad:</strong> {finesData.length} multas</p>
                          <p><strong>Total a pagar:</strong> S/ {selectedFine.monto.toFixed(2)}</p>
                        </div>
                      ) : (
                        <div className="payment-item">
                          <p><strong>Concepto:</strong> {selectedFine.concepto}</p>
                          <p><strong>Libro:</strong> {selectedFine.libro}</p>
                          <p><strong>Monto:</strong> S/ {selectedFine.monto.toFixed(2)}</p>
                        </div>
                      )}
                    </div>
                    
                    <div className="payment-warning">
                      <p>⚠️ Serás redirigido a la página de pago para completar la transacción.</p>
                    </div>
                  </>
                )}
              </div>
            </div>
            <div className="modal-footer">
              <button className="btn-primary" onClick={confirmPayment}>
                Continuar al Pago
              </button>
              <button className="btn-outline" onClick={() => setShowPaymentModal(false)}>
                Cancelar
              </button>
            </div>
          </div>
        </div>
      )}

      <Footer />
    </div>
  )
}

export default PanelUsuario