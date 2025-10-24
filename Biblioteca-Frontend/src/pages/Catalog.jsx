import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import Header from '../components/Header'
import Footer from '../components/Footer'
import '../styles/index.css'

const Catalogo = () => {
  const [searchTerm, setSearchTerm] = useState('')
  const [languageFilter, setLanguageFilter] = useState('')
  const [topicFilter, setTopicFilter] = useState('')
  const [results, setResults] = useState([])

  // Base de datos simulada de libros
  const booksData = [
    {
      id: 1,
      title: 'Cien años de soledad',
      author: 'Gabriel García Márquez',
      year: 1967,
      language: 'español',
      topic: 'ficcion',
      available: true,
      isbn: '978-8437604947'
    },
    {
      id: 2,
      title: '1984',
      author: 'George Orwell',
      year: 1949,
      language: 'inglés',
      topic: 'ficcion',
      available: false,
      isbn: '978-0451524935'
    },
    {
      id: 3,
      title: 'El amor en los tiempos del cólera',
      author: 'Gabriel García Márquez',
      year: 1985,
      language: 'español',
      topic: 'ficcion',
      available: true,
      isbn: '978-0307389732'
    },
    {
      id: 4,
      title: 'Breve historia del tiempo',
      author: 'Stephen Hawking',
      year: 1988,
      language: 'inglés',
      topic: 'ciencia',
      available: true,
      isbn: '978-0553380163'
    },
    {
      id: 5,
      title: 'Sapiens: De animales a dioses',
      author: 'Yuval Noah Harari',
      year: 2011,
      language: 'español',
      topic: 'historia',
      available: true,
      isbn: '978-8499926223'
    },
    {
      id: 6,
      title: 'La guerra y la paz',
      author: 'León Tolstói',
      year: 1869,
      language: 'francés',
      topic: 'ficcion',
      available: false,
      isbn: '978-0140447934'
    },
    {
      id: 7,
      title: 'El origen de las especies',
      author: 'Charles Darwin',
      year: 1859,
      language: 'inglés',
      topic: 'ciencia',
      available: true,
      isbn: '978-0451529060'
    },
    {
      id: 8,
      title: 'Historia de Roma',
      author: 'Indro Montanelli',
      year: 1959,
      language: 'español',
      topic: 'historia',
      available: true,
      isbn: '978-8435010716'
    }
  ]

  const handleSearch = (e) => {
    e.preventDefault()
    
    // Si no hay término de búsqueda ni filtros, mostrar todos los libros
    if (!searchTerm && !languageFilter && !topicFilter) {
      setResults(booksData)
      return
    }

    // Filtrar libros según los criterios
    const filteredResults = booksData.filter(book => {
      const matchesSearch = !searchTerm || 
        book.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
        book.author.toLowerCase().includes(searchTerm.toLowerCase()) ||
        book.isbn.includes(searchTerm)

      const matchesLanguage = !languageFilter || book.language === languageFilter
      const matchesTopic = !topicFilter || book.topic === topicFilter

      return matchesSearch && matchesLanguage && matchesTopic
    })

    setResults(filteredResults)
  }

  const handleClearFilters = () => {
    setSearchTerm('')
    setLanguageFilter('')
    setTopicFilter('')
    setResults([])
  }

  // Función para obtener el texto del área temática
  const getTopicText = (topic) => {
    const topics = {
      'ficcion': 'Ficción',
      'ciencia': 'Ciencia',
      'historia': 'Historia'
    }
    return topics[topic] || topic
  }

  return (
    <div className="catalogo-page">
      <Header />
      <main>
        <section className="search-section">
          <h2>Búsqueda en Catálogo</h2>
          <form className="search-form" onSubmit={handleSearch}>
            <input 
              type="text" 
              id="search-term" 
              placeholder="Título, autor, ISBN..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
            <select 
              id="language-filter"
              value={languageFilter}
              onChange={(e) => setLanguageFilter(e.target.value)}
            >
              <option value="">Todos los idiomas</option>
              <option value="español">Español</option>
              <option value="inglés">Inglés</option>
              <option value="francés">Francés</option>
            </select>
            <select 
              id="topic-filter"
              value={topicFilter}
              onChange={(e) => setTopicFilter(e.target.value)}
            >
              <option value="">Todas las áreas</option>
              <option value="ficcion">Ficción</option>
              <option value="ciencia">Ciencia</option>
              <option value="historia">Historia</option>
            </select>
            <div className="search-buttons">
              <button type="submit" id="search-btn" className="btn-primary">
                🔍 Buscar
              </button>
              <button 
                type="button" 
                className="btn-outliner" 
                onClick={handleClearFilters}
              >
                🗑️ Limpiar
              </button>
            </div>
          </form>

          {/* Mostrar filtros activos */}
          {(searchTerm || languageFilter || topicFilter) && (
            <div className="active-filters">
              <h3>Filtros activos:</h3>
              <div className="filters-list">
                {searchTerm && (
                  <span className="filter-tag">
                    Búsqueda: "{searchTerm}"
                    <button onClick={() => setSearchTerm('')}>×</button>
                  </span>
                )}
                {languageFilter && (
                  <span className="filter-tag">
                    Idioma: {languageFilter}
                    <button onClick={() => setLanguageFilter('')}>×</button>
                  </span>
                )}
                {topicFilter && (
                  <span className="filter-tag">
                    Área: {getTopicText(topicFilter)}
                    <button onClick={() => setTopicFilter('')}>×</button>
                  </span>
                )}
              </div>
            </div>
          )}
        </section>

        <section className="results-section">
          <div className="results-header">
            <h2>Resultados de búsqueda</h2>
            {results.length > 0 && (
              <span className="results-count">
                {results.length} libro{results.length !== 1 ? 's' : ''} encontrado{results.length !== 1 ? 's' : ''}
              </span>
            )}
          </div>
          
          <div id="results-container">
            {results.length === 0 ? (
              <div className="no-results">
                <div className="no-results-icon">📚</div>
                <h3>No se encontraron resultados</h3>
                <p>
                  {searchTerm || languageFilter || topicFilter 
                    ? "Intenta con otros términos de búsqueda o elimina algunos filtros."
                    : "Realiza una búsqueda para ver los libros disponibles."
                  }
                </p>
                <button 
                  className="btn-primary" 
                  onClick={() => setResults(booksData)}
                >
                  Ver Todos los Libros
                </button>
              </div>
            ) : (
              results.map(book => (
                <div key={book.id} className="book-card">
                  <div className="book-header">
                    <h3>{book.title}</h3>
                    <span className={`availability ${book.available ? 'available' : 'unavailable'}`}>
                      {book.available ? 'Disponible' : 'Prestado'}
                    </span>
                  </div>
                  <p className="book-author">por {book.author}</p>
                  <div className="book-details">
                    <p><strong>Año:</strong> {book.year}</p>
                    <p><strong>Idioma:</strong> {book.language}</p>
                    <p><strong>Área:</strong> {getTopicText(book.topic)}</p>
                    <p><strong>ISBN:</strong> {book.isbn}</p>
                  </div>
                  <Link to="/detalle-obra" state={{ book }} className="btn-primary">
                    Ver Detalles
                  </Link>
                </div>
              ))
            )}
          </div>
        </section>
      </main>
      <Footer />
    </div>
  )
}

export default Catalogo