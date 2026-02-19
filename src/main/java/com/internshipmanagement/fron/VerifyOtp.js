import React, { useState } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import "../CSS/ForgotPassword.css";

function VerifyOtp() {
  const location = useLocation();
  const navigate = useNavigate();
  const email = location.state?.email;

  const [otp, setOtp] = useState("");
  const [newPassword, setNewPassword] = useState("");

  const handleVerify = async (e) => {
    e.preventDefault();

    try {
      await axios.post(
        "http://localhost:8080/api/auth/verify-otp",
        null,
        { params: { email, otp, newPassword } }
      );

      alert("Password Reset Successful");
      navigate("/"); // Redirect to login after success

    } catch (error) {
      alert(error.response?.data || "Verification failed");
    }
  };

  return (
    <div className="auth-page-wrapper">
      <div className="form-container">
        <h2>Verify OTP</h2>
        <p>We've sent a code to <br/><strong>{email}</strong></p>
        
        <form onSubmit={handleVerify}>
          <input
            type="text"
            placeholder="6-Digit OTP"
            value={otp}
            onChange={(e) => setOtp(e.target.value)}
            required
            maxLength="6"
          />
          <input
            type="password"
            placeholder="New Password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
            required
          />
          <button type="submit">Reset Password</button>
        </form>
      </div>
    </div>
  );
}

export default VerifyOtp;