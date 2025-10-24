import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/api";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const res = await api.post("/usuarios/login", { email, password });

      // Guardar tanto usuario como token
      localStorage.setItem(
        "usuario",
        JSON.stringify(res.data.usuario || res.data)
      );
      localStorage.setItem("token", res.data.token); // ← Esto es crucial

      // Redirigir a la página principal
      navigate("/perfil");
    } catch (err) {
      console.error(err);
      setError("Credenciales inválidas. Intenta nuevamente.");
    }
  };

  return (
    <div style={{ maxWidth: 400, margin: "80px auto", textAlign: "center" }}>
      <h2>Iniciar sesión</h2>

      <form onSubmit={handleSubmit}>
        <input
          type="email"
          placeholder="Correo electrónico"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          style={{ width: "100%", padding: 10, margin: "10px 0" }}
        />

        <input
          type="password"
          placeholder="Contraseña"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          style={{ width: "100%", padding: 10, margin: "10px 0" }}
        />

        {error && <p style={{ color: "red" }}>{error}</p>}

        <button
          type="submit"
          style={{
            background: "#007bff",
            color: "white",
            border: "none",
            padding: "10px 20px",
            cursor: "pointer",
            borderRadius: 6,
          }}
        >
          Entrar
        </button>
      </form>
    </div>
  );
}
