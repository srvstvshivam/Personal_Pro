import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import "../CSS/ForgotPassword.css"; // Create a new CSS file for ForgotPassword styling

function ForgotPassword() {
  const [email, setEmail] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post(
        "http://localhost:8080/api/auth/forgot-password",
        null,
        { params: { email } }
      );
      alert("OTP sent to your email");
      navigate("/verify-otp", { state: { email } });
    } catch (error) {
      alert(error.response?.data || "Something went wrong");
    }
  };

  return (
    <div className="auth-page-wrapper">
      <div className="form-container">
        <h2>Forgot Password?</h2>
        <p>Don't worry! Enter your email below to receive an OTP.</p>
        
        <form onSubmit={handleSubmit}>
          <input
            type="email"
            placeholder="Enter Registered Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <button type="submit">Send OTP</button>
        </form>

        <div style={{ marginTop: "20px" }}>
          <Link to="/" style={{ color: "#a259ff", textDecoration: "none", fontSize: "0.9rem" }}>
            ← Back to Login
          </Link>
        </div>
      </div>
    </div>
  );
}

export default ForgotPassword;