import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import "../CSS/Register.css"; // Use Register.css to keep the glass theme consistent

function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/auth/login",
        { email, password },
        { headers: { "Content-Type": "application/json" } }
      );

      localStorage.setItem("token", response.data);
      alert("Login Successful! 🔓");
      navigate("/success");
    } catch (error) {
      console.log("Login Error:", error.response?.data);
      alert(error.response?.data || "Login Failed ❌");
    }
  };

  const handleReset = () => {
    setEmail("");
    setPassword("");
  };

  return (
    <div className="register-page">
      {/* Background Blobs */}
      <div className="blob"></div>
      <div className="blob2"></div>

      <div className="register-container" style={{ maxWidth: "450px" }}>
        <div className="glass-header">
          <h2>Student Login</h2>
          <p>Enter your credentials to continue</p>
        </div>

        <form onSubmit={handleLogin} className="register-form">
          <div className="form-section">
            <div className="input-grid" style={{ gridTemplateColumns: "1fr" }}>
              <input
                type="email"
                placeholder="Enter Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
              <input
                type="password"
                placeholder="Enter Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>
            
            <div style={{ display: "flex", justifyContent: "flex-end", marginTop: "10px" }}>
              <span 
                onClick={() => navigate("/forgot-password")} 
                className="login-link" 
                style={{ fontSize: "0.85rem" }}
              >
                Forgot Password?
              </span>
            </div>
          </div>

          <button type="submit" className="register-submit-btn">
            Login
          </button>
          
          <button 
            type="button" 
            className="register-submit-btn" 
            style={{ background: "rgba(255,255,255,0.1)", marginTop: "10px", boxShadow: "none" }} 
            onClick={handleReset}
          >
            Reset
          </button>
        </form>

        <div className="login-link-section">
          <p>New User? 
            <Link to="/register" className="login-link">
               Register here
            </Link>
          </p>
        </div>
      </div>
    </div>
  );
}

export default Login; // <--- CRITICAL: Do not remove this!