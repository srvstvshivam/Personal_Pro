import React from "react";
import { useNavigate } from "react-router-dom";

function Success() {

  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };

  return (
    <div style={{
      height: "100vh",
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      flexDirection: "column",
      background: "#f4f6f9"
    }}>
      <h1 style={{ color: "green" }}>🎉 You Logged In Successfully!</h1>

      <button
        onClick={handleLogout}
        style={{
          marginTop: "20px",
          padding: "10px 20px",
          cursor: "pointer"
        }}
      >
        Logout
      </button>
    </div>
  );
}

export default Success;
