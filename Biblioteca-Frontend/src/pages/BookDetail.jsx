import React, { useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import Header from '../components/Header'
import Footer from '../components/Footer'
import '../styles/Detalle_de_Obra_.css'

const DetalleObra = () => {
  const navigate = useNavigate()
  const [book, setBook] = useState(null)
  const [available, setAvailable] = useState(true)

  useEffect(() => {
    // Simular carga de datos del libro
    const mockBook = {
      title: 'Cien años de soledad',
      author: 'Gabriel García Márquez',
      isbn: '978-8437604947',
      language: 'Español',
      topic: 'Ficción',
      year: 1967,
      pages: 471,
      publisher: 'Ediciones Saberum',
      description: 'Una obra maestra del realismo mágico que narra la historia de la familia Buendía en el pueblo ficticio de Macondo.'
    }
    setBook(mockBook)
  }, [])

  const handleLoanRequest = () => {
    navigate('/solicitud-prestamo')
  }

  const handleReserve = () => {
    alert('Libro reservado exitosamente')
  }

  if (!book) return <div>Cargando...</div>

  return (
    <div className="detalle-obra-page">
      <Header />
      <main>
        <div className="breadcrumb">
          <Link to="/catalogo">Catálogo - </Link> 
          <span id="breadcrumb-title">Detalle de Obra</span>
        </div>

        <section className="book-detail">
          <div className="book-cover">
            <img id="book-image" src="/IMG/cien_años.jpg" alt="Portada del libro" />
            <div className="availability-status" id="availability-status">
              <span className={`status ${available ? 'available' : 'unavailable'}`}>
                {available ? 'Disponible' : 'No Disponible'}
              </span>
            </div>
          </div>
          
          <div className="book-info">
            <h1 id="book-title">{book.title}</h1>
            <p className="author" id="book-author">{book.author}</p>
            <div className="book-meta">
              <div className="meta-item">
                <strong>ISBN:</strong> <span id="book-isbn">{book.isbn}</span>
              </div>
              <div className="meta-item">
                <strong>Idioma:</strong> <span id="book-language">{book.language}</span>
              </div>
              <div className="meta-item">
                <strong>Área temática:</strong> <span id="book-topic">{book.topic}</span>
              </div>
              <div className="meta-item">
                <strong>Año de publicación:</strong> <span id="book-year">{book.year}</span>
              </div>
              <div className="meta-item">
                <strong>Número de páginas:</strong> <span id="book-pages">{book.pages}</span>
              </div>
              <div className="meta-item">
                <strong>Editorial:</strong> <span id="book-publisher">{book.publisher}</span>
              </div>
            </div>
            
            <div className="book-description">
              <h3>Descripción</h3>
              <p id="book-description-text">{book.description}</p>
            </div>
            
            <div className="action-buttons">
              <button id="loan-btn" className="btn-primary" onClick={handleLoanRequest}>
                Solicitar Préstamo
              </button>
              <button id="reserve-btn" className="btn-secondary" onClick={handleReserve}>
                Reservar
              </button>
              <Link to="/catalogo" className="btn-outline">
                Volver al Catálogo
              </Link>
            </div>
          </div>
        </section>

        <section className="related-books">
          <h2>Obras relacionadas</h2>
          <div id="related-books-container">
            <p>Libros similares cargados dinámicamente...</p>
          </div>
        </section>
      </main>
      <Footer />
    </div>
  )
}

export default DetalleObra