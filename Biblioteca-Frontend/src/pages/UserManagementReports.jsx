import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import Header from '../components/Header'
import Footer from '../components/Footer'
import '../styles/Gestion_Usuarios_Reportes.css'

const GestionUsuariosReportes = () => {
  const [activeTab, setActiveTab] = useState('usuarios')
  const [searchQuery, setSearchQuery] = useState('')
  const [startDate, setStartDate] = useState('')
  const [endDate, setEndDate] = useState('')
  const [reportFormat, setReportFormat] = useState('pdf')
  const [reportSearch, setReportSearch] = useState('')

    const reportsList = [
    {
      id: 1,
      title: 'Reporte de Préstamos Activos',
      description: 'Lista detallada de todos los préstamos en curso con información de usuarios y fechas de vencimiento',
      type: 'préstamos-activos',
      icon: '📚',
      category: 'Préstamos'
    },
    {
      id: 2,
      title: 'Préstamos por Vencer',
      description: 'Préstamos que están próximos a vencer en los próximos 7 días para seguimiento preventivo',
      type: 'préstamos-por-vencer',
      icon: '⏰',
      category: 'Préstamos'
    },
    {
      id: 3,
      title: 'Estadísticas de Préstamos',
      description: 'Métricas y tendencias de préstamos por mes, tipo de usuario y categoría de libros',
      type: 'estadísticas-préstamos',
      icon: '📈',
      category: 'Préstamos'
    },
    {
      id: 4,
      title: 'Reporte de Multas',
      description: 'Multas generadas, pagadas y pendientes con detalles de usuarios y montos',
      type: 'multas',
      icon: '💰',
      category: 'Multas'
    },
    {
      id: 5,
      title: 'Multas por Cobrar',
      description: 'Lista de multas pendientes de pago organizadas por antigüedad y monto',
      type: 'multas-por-cobrar',
      icon: '📋',
      category: 'Multas'
    },
    {
      id: 6,
      title: 'Ingresos por Multas',
      description: 'Reporte financiero de ingresos generados por concepto de multas en un período',
      type: 'ingresos-multas',
      icon: '💳',
      category: 'Multas'
    },
    {
      id: 7,
      title: 'Usuarios Activos',
      description: 'Lista completa de usuarios activos con su historial de préstamos y estadísticas',
      type: 'usuarios-activos',
      icon: '👥',
      category: 'Usuarios'
    },
    {
      id: 8,
      title: 'Usuarios Más Activos',
      description: 'Ranking de usuarios con mayor actividad de préstamos y reservas',
      type: 'usuarios-más-activos',
      icon: '⚡',
      category: 'Usuarios'
    },
    {
      id: 9,
      title: 'Estadísticas de Usuarios',
      description: 'Distribución de usuarios por tipo, edad, género y otros datos demográficos',
      type: 'estadísticas-usuarios',
      icon: '📊',
      category: 'Usuarios'
    },
    {
      id: 10,
      title: 'Inventario Completo',
      description: 'Lista completa del inventario de libros con estado, ubicación y disponibilidad',
      type: 'inventario-completo',
      icon: '📖',
      category: 'Inventario'
    },
    {
      id: 11,
      title: 'Libros Más Populares',
      description: 'Ranking de libros más solicitados y prestados en un período determinado',
      type: 'libros-populares',
      icon: '🔥',
      category: 'Inventario'
    },
    {
      id: 12,
      title: 'Movimiento de Inventario',
      description: 'Entradas, salidas y movimientos del inventario de libros',
      type: 'movimiento-inventario',
      icon: '🔄',
      category: 'Inventario'
    },
    {
      id: 13,
      title: 'Reporte Anual',
      description: 'Resumen ejecutivo anual con todas las métricas importantes del sistema',
      type: 'reporte-anual',
      icon: '📅',
      category: 'Especiales'
    },
    {
      id: 14,
      title: 'Auditoría del Sistema',
      description: 'Reporte completo de auditoría con todos los movimientos y cambios del sistema',
      type: 'auditoría-sistema',
      icon: '⚖️',
      category: 'Especiales'
    }
  ]

  const handleSearch = (e) => {
    e.preventDefault()
    // Lógica de búsqueda
    console.log('Buscando usuario:', searchQuery)
  }

  const handleReportSearch = (e) => {
    setReportSearch(e.target.value.toLowerCase())
  }

  const handleGenerateReport = (reportType) => {
    const reportData = {
      type: reportType,
      format: reportFormat,
      dateRange: {
        start: startDate,
        end: endDate
      }
    }
    
    alert(`Generando reporte: ${reportType}\nFormato: ${reportFormat.toUpperCase()}\nPeríodo: ${startDate || 'Sin fecha'} - ${endDate || 'Sin fecha'}`)
    
    // Simular descarga de reporte
    console.log('Datos del reporte:', reportData)
  }

  const handleExportAllReports = () => {
    alert('Exportando todos los reportes disponibles...')
  }

  // Función para formatear fecha
  const formatDate = (dateString) => {
    if (!dateString) return 'No especificada'
    return new Date(dateString).toLocaleDateString('es-ES')
  }

  // Filtrar reportes según la búsqueda
  const filteredReports = reportsList.filter(report => 
    report.title.toLowerCase().includes(reportSearch) ||
    report.description.toLowerCase().includes(reportSearch) ||
    report.category.toLowerCase().includes(reportSearch)
  )

  return (
    <div className="gestion-usuarios-reportes-page">
      <Header />
      <main>
        <div className="admin-dashboard">
          <div className="dashboard-header">
            <h1>Panel de Administración</h1>
            <div className="admin-stats">
              <div className="stat-card">
                <h3>Total Usuarios</h3>
                <span className="stat-number" id="total-users">150</span>
              </div>
              <div className="stat-card">
                <h3>Préstamos Activos</h3>
                <span className="stat-number" id="active-loans">45</span>
              </div>
              <div className="stat-card">
                <h3>Multas Pendientes</h3>
                <span className="stat-number" id="pending-fines">8</span>
              </div>
              <div className="stat-card">
                <h3>Libros Disponibles</h3>
                <span className="stat-number" id="available-books">1,234</span>
              </div>
            </div>
          </div>

          <div className="admin-tabs">
            <nav className="tab-nav">
              <button 
                className={`tab-button ${activeTab === 'usuarios' ? 'active' : ''}`}
                onClick={() => setActiveTab('usuarios')}
              >
                👥 Gestión de Usuarios
              </button>
              <button 
                className={`tab-button ${activeTab === 'reportes' ? 'active' : ''}`}
                onClick={() => setActiveTab('reportes')}
              >
                📊 Reportes y Estadísticas
              </button>
            </nav>

            {/* Pestaña: Gestión de Usuarios */}
            <div id="gestion-usuarios" className={`tab-content ${activeTab === 'usuarios' ? 'active' : ''}`}>
              <div className="tab-header">
                <h2>Gestión de Usuarios</h2>
                <div className="tab-actions">
                  <form className="search-form" onSubmit={handleSearch}>
                    <input 
                      type="text" 
                      placeholder="Buscar usuario por DNI, nombre..."
                      value={searchQuery}
                      onChange={(e) => setSearchQuery(e.target.value)}
                    />
                    <button type="submit">🔍 Buscar</button>
                  </form>
                  <button className="btn-primary" id="add-user-btn">
                    ➕ Agregar Usuario
                  </button>
                </div>
              </div>

              <div className="users-table-container">
                <table className="users-table" id="users-table">
                  <thead>
                    <tr>
                      <th>DNI</th>
                      <th>Nombre</th>
                      <th>Email</th>
                      <th>Tipo</th>
                      <th>Estado</th>
                      <th>Acciones</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>12345678</td>
                      <td>Juan Pérez</td>
                      <td>juan.perez@email.com</td>
                      <td>Socio</td>
                      <td><span className="status-active">Activo</span></td>
                      <td>
                        <button className="btn-action edit">✏️ Editar</button>
                        <button className="btn-action suspend">⏸️ Suspender</button>
                      </td>
                    </tr>
                    <tr>
                      <td>87654321</td>
                      <td>María García</td>
                      <td>maria.garcia@email.com</td>
                      <td>Estudiante</td>
                      <td><span className="status-suspended">Suspendido</span></td>
                      <td>
                        <button className="btn-action edit">✏️ Editar</button>
                        <button className="btn-action activate">▶️ Activar</button>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>

              <div className="user-details-section" id="user-details-section">
                <h3>Detalles del Usuario</h3>
                <div id="user-details-content">
                  <p>Seleccione un usuario para ver sus detalles</p>
                </div>
              </div>
            </div>

            {/* Pestaña: Reportes y Estadísticas - ACTUALIZADA */}
            <div id="reportes-estadisticas" className={`tab-content ${activeTab === 'reportes' ? 'active' : ''}`}>
              <div className="tab-header">
                <h2>Reportes y Estadísticas</h2>
                <div className="report-controls">
                  <div className="date-filters">
                    <div className="filter-group">
                      <label htmlFor="start-date">📅 Desde:</label>
                      <input 
                        type="date" 
                        id="start-date" 
                        value={startDate}
                        onChange={(e) => setStartDate(e.target.value)}
                      />
                    </div>
                    <div className="filter-group">
                      <label htmlFor="end-date">📅 Hasta:</label>
                      <input 
                        type="date" 
                        id="end-date" 
                        value={endDate}
                        onChange={(e) => setEndDate(e.target.value)}
                      />
                    </div>
                    <div className="filter-group">
                      <label htmlFor="report-format">📄 Formato:</label>
                      <select 
                        id="report-format"
                        value={reportFormat}
                        onChange={(e) => setReportFormat(e.target.value)}
                      >
                        <option value="pdf">PDF</option>
                        <option value="excel">Excel</option>
                        <option value="csv">CSV</option>
                        <option value="html">HTML</option>
                      </select>
                    </div>
                    <button className="btn-primary" id="apply-filters">
                      ✅ Aplicar Filtros
                    </button>
                  </div>
                  <button 
                    className="btn-secondary" 
                    id="export-all-reports"
                    onClick={handleExportAllReports}
                  >
                    📦 Exportar Todos los Reportes
                  </button>
                </div>
              </div>

              {/* Barra de búsqueda de reportes - NUEVA */}
              <div className="report-search-section">
                <div className="search-container">
                  <input 
                    type="text" 
                    placeholder="🔍 Buscar reportes por nombre, descripción o categoría..."
                    value={reportSearch}
                    onChange={handleReportSearch}
                    className="report-search-input"
                  />
                  {reportSearch && (
                    <span className="search-results-count">
                      {filteredReports.length} reporte{filteredReports.length !== 1 ? 's' : ''} encontrado{filteredReports.length !== 1 ? 's' : ''}
                    </span>
                  )}
                </div>
              </div>

              {/* Información del período seleccionado */}
              {(startDate || endDate) && (
                <div className="date-range-info">
                  <h4>Período seleccionado:</h4>
                  <p>
                    <strong>Desde:</strong> {formatDate(startDate)} | 
                    <strong> Hasta:</strong> {formatDate(endDate)}
                  </p>
                </div>
              )}

              <div className="reports-grid">
                {filteredReports.map(report => (
                  <div key={report.id} className="report-card">
                    <div className="report-icon">{report.icon}</div>
                    <h3>{report.title}</h3>
                    <p>{report.description}</p>
                    <div className="report-category">
                      <span className="category-tag">{report.category}</span>
                    </div>
                    <button 
                      className="btn-primary" 
                      onClick={() => handleGenerateReport(report.type)}
                    >
                      Generar Reporte
                    </button>
                  </div>
                ))}
              </div>

              {filteredReports.length === 0 && reportSearch && (
                <div className="no-results">
                  <div className="no-results-icon">🔍</div>
                  <h3>No se encontraron reportes</h3>
                  <p>No hay reportes que coincidan con "{reportSearch}"</p>
                  <button 
                    className="btn-primary" 
                    onClick={() => setReportSearch('')}
                  >
                    Mostrar Todos los Reportes
                  </button>
                </div>
              )}

              <div className="charts-section">
                <h3>📈 Gráficos y Estadísticas en Tiempo Real</h3>
                <div className="charts-container" id="charts-container">
                  <div className="chart-placeholder">
                    <div className="chart-icon">📊</div>
                    <h4>Préstamos por Mes</h4>
                    <p>Evolución mensual de préstamos realizados</p>
                  </div>
                  <div className="chart-placeholder">
                    <div className="chart-icon">📚</div>
                    <h4>Libros Más Populares</h4>
                    <p>Top 10 de libros más prestados</p>
                  </div>
                  <div className="chart-placeholder">
                    <div className="chart-icon">👥</div>
                    <h4>Usuarios Activos</h4>
                    <p>Distribución por tipo de usuario</p>
                  </div>
                  <div className="chart-placeholder">
                    <div className="chart-icon">💰</div>
                    <h4>Ingresos por Multas</h4>
                    <p>Evolución de ingresos mensuales</p>
                  </div>
                </div>
              </div>

              {/* Resumen de Métricas */}
              <div className="metrics-summary">
                <h3>📋 Resumen de Métricas</h3>
                <div className="metrics-grid">
                  <div className="metric-item">
                    <span className="metric-value">85%</span>
                    <span className="metric-label">Tasa de renovación</span>
                  </div>
                  <div className="metric-item">
                    <span className="metric-value">12.5</span>
                    <span className="metric-label">Préstamos promedio por usuario</span>
                  </div>
                  <div className="metric-item">
                    <span className="metric-value">3.2</span>
                    <span className="metric-label">Días de retraso promedio</span>
                  </div>
                  <div className="metric-item">
                    <span className="metric-value">94%</span>
                    <span className="metric-label">Satisfacción del usuario</span>
                  </div>
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

export default GestionUsuariosReportes