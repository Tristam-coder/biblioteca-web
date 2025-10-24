import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/api";

export default function Perfil() {
  const [usuario, setUsuario] = useState(null);
  const [prestamos, setPrestamos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const cargarDatosUsuario = async () => {
      try {
        const token = localStorage.getItem("token");
        if (!token) {
          navigate("/login");
          return;
        }

        const usuarioData = localStorage.getItem("usuario");
        if (!usuarioData) {
          navigate("/login");
          return;
        }

        const usuarioObj = JSON.parse(usuarioData);
        setUsuario(usuarioObj);

        // Obtener préstamos del usuario
        const response = await api.get(`/prestamos/usuario/${usuarioObj.id}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setPrestamos(response.data);
      } catch (err) {
        console.error("Error cargando datos:", err);
        setError("Error al cargar los datos del usuario");
      } finally {
        setLoading(false);
      }
    };

    cargarDatosUsuario();
  }, [navigate]);

  const handleDevolver = async (prestamoId) => {
    try {
      const token = localStorage.getItem("token");
      await api.put(
        `/prestamos/devolver/${prestamoId}`,
        {},
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );

      // Recargar los préstamos después de la devolución
      const usuarioData = JSON.parse(localStorage.getItem("usuario"));
      const response = await api.get(`/prestamos/usuario/${usuarioData.id}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setPrestamos(response.data);
      alert("Libro devuelto exitosamente");
    } catch (err) {
      console.error("Error devolviendo libro:", err);
      alert("Error al devolver el libro");
    }
  };

  if (loading) {
    return (
      <div style={{ textAlign: "center", padding: "50px" }}>Cargando...</div>
    );
  }

  if (error) {
    return <div style={{ color: "red", textAlign: "center" }}>{error}</div>;
  }

  return (
    <div style={{ maxWidth: 800, margin: "40px auto", padding: "0 20px" }}>
      <h2>Mi Perfil</h2>

      {/* Información del usuario */}
      <div
        style={{
          background: "#f8f9fa",
          padding: "20px",
          borderRadius: "8px",
          marginBottom: "30px",
        }}
      >
        <h3>Información Personal</h3>
        <p>
          <strong>Nombre:</strong> {usuario?.nombre} {usuario?.apellido}
        </p>
        <p>
          <strong>Email:</strong> {usuario?.email}
        </p>
        <p>
          <strong>ID de usuario:</strong> {usuario?.id}
        </p>
      </div>

      {/* Préstamos activos */}
      <div>
        <h3>Mis Préstamos Activos</h3>

        {prestamos.length === 0 ? (
          <p>No tienes préstamos activos</p>
        ) : (
          <div style={{ display: "grid", gap: "15px" }}>
            {prestamos.map((prestamo) => (
              <div
                key={prestamo.id}
                style={{
                  border: "1px solid #ddd",
                  borderRadius: "8px",
                  padding: "15px",
                  background: "#fff",
                }}
              >
                <div
                  style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "start",
                  }}
                >
                  <div>
                    <h4 style={{ margin: "0 0 10px 0" }}>
                      {prestamo.libro?.titulo}
                    </h4>
                    <p>
                      <strong>Autor:</strong> {prestamo.libro?.autor}
                    </p>
                    <p>
                      <strong>Fecha de préstamo:</strong>{" "}
                      {new Date(prestamo.fecha_prestamo).toLocaleDateString()}
                    </p>
                    <p>
                      <strong>Fecha de devolución:</strong>{" "}
                      {new Date(prestamo.fecha_devolucion).toLocaleDateString()}
                    </p>
                    <p>
                      <strong>Estado:</strong>
                      <span
                        style={{
                          color:
                            prestamo.estado === "activo" ? "green" : "gray",
                          fontWeight: "bold",
                          marginLeft: "5px",
                        }}
                      >
                        {prestamo.estado}
                      </span>
                    </p>
                  </div>

                  {prestamo.estado === "activo" && (
                    <button
                      onClick={() => handleDevolver(prestamo.id)}
                      style={{
                        background: "#dc3545",
                        color: "white",
                        border: "none",
                        padding: "8px 16px",
                        borderRadius: "4px",
                        cursor: "pointer",
                      }}
                    >
                      Devolver
                    </button>
                  )}
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}
