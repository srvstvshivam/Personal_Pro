import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // 1. ADD THIS IMPORT
import axios from "axios";
import "../CSS/Register.css";


function Register() {

    const navigate = useNavigate();
  const [student, setStudent] = useState({
    firstName: "",
    lastName: "",
    fatherName: "",
    dob: "",
    mobileNumber: "",
    gender: "",
    email: "",
    password: "",
    collegeName: "",
    university: "",
    city: "",
    graduationDiscipline: "",
    grade: null,
    schoolName: "",
    marks12: null,
    marks10: null,
    hasActiveBacklogs: false,
    numberOfBacklogs: null,
    address: {
      line1: "",
      line2: "",
      state: "",
      district: "",
      postalCode: ""
    }
  });

  const [files, setFiles] = useState({
    profileImgFile: null,
    file10th: null,
    file12th: null,
    degreeFiles: [],
    backlogFiles: []
  });

  // ===============================
  // Handle Input Change
  // ===============================
  const handleChange = (e) => {
    const { name, value } = e.target;

    // Boolean
    if (name === "hasActiveBacklogs") {
      setStudent({
        ...student,
        hasActiveBacklogs: value === "true"
      });
      return;
    }

    // Numbers
    if (["grade", "marks12", "marks10", "numberOfBacklogs"].includes(name)) {
      setStudent({
        ...student,
        [name]: value === "" ? null : Number(value)
      });
      return;
    }

    // Address
    if (name.startsWith("address.")) {
      const field = name.split(".")[1];
      setStudent({
        ...student,
        address: {
          ...student.address,
          [field]: value
        }
      });
    } else {
      setStudent({
        ...student,
        [name]: value
      });
    }
  };

  // ===============================
  // Handle File Change
  // ===============================
  const handleFileChange = (e) => {
    const { name, files: selectedFiles } = e.target;

    if (name === "degreeFiles" || name === "backlogFiles") {
      setFiles({
        ...files,
        [name]: Array.from(selectedFiles)
      });
    } else {
      setFiles({
        ...files,
        [name]: selectedFiles[0]
      });
    }
  };

  // ===============================
  // Submit
  // ===============================
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const formData = new FormData();

      // Convert student object properly
      formData.append(
        "student",
        new Blob([JSON.stringify(student)], {
          type: "application/json"
        })
      );

      // Mandatory files (if your backend requires them)
      if (files.profileImgFile)
        formData.append("profileImgFile", files.profileImgFile);

      if (files.file10th)
        formData.append("file10th", files.file10th);

      if (files.file12th)
        formData.append("file12th", files.file12th);

      // Degree files
      files.degreeFiles.forEach(file =>
        formData.append("degreeFiles", file)
      );

      // Backlog files
      if (student.hasActiveBacklogs) {
        files.backlogFiles.forEach(file =>
          formData.append("backlogFiles", file)
        );
      }

      const response = await axios.post(
        "http://localhost:8080/api/auth/register",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        }
      );

      alert("Registration Successful 🎉");
      console.log("Success:", response.data);

    } catch (error) {
      console.log("Full Error:", error.response?.data);
      alert(JSON.stringify(error.response?.data) || "Registration Failed ❌");
    }
  };

 return (
  <div className="register-page">
    {/* Floating background blobs for the glass effect */}
    <div className="blob"></div>
    <div className="blob2"></div>
    
    <div className="register-container">
      <div className="glass-header">
        <h2>Student Registration</h2>
        <p>Complete all fields to create your profile</p>
      </div>

      <form onSubmit={handleSubmit} className="register-form">
        
        {/* SECTION 1: Profile Image */}
        <div className="form-section">
          <h3><i className="fas fa-camera"></i> Profile Image</h3>
          <div className="file-box">
            <input type="file" name="profileImgFile" onChange={handleFileChange} />
          </div>
        </div>

        {/* SECTION 2: Basic Information */}
        <div className="form-section">
          <h3>Basic Information</h3>
          <div className="input-grid">
            <input name="firstName" placeholder="First Name" onChange={handleChange} required />
            <input name="lastName" placeholder="Last Name" onChange={handleChange} required />
            <input name="fatherName" placeholder="Father Name" onChange={handleChange} required />
            <input type="date" name="dob" onChange={handleChange} required title="Date of Birth" />
            <input name="mobileNumber" placeholder="Mobile Number" onChange={handleChange} required />
            <select name="gender" onChange={handleChange} required>
              <option value="">Select Gender</option>
              <option value="Male">Male</option>
              <option value="Female">Female</option>
              <option value="Other">Other</option>
            </select>
          </div>
        </div>

        {/* SECTION 3: Login Details */}
        <div className="form-section">
          <h3>Login Details</h3>
          <div className="input-grid">
            <input name="email" placeholder="Email" onChange={handleChange} required />
            <input type="password" name="password" placeholder="Password" onChange={handleChange} required />
          </div>
        </div>

        {/* SECTION 4: Address */}
        <div className="form-section">
          <h3>Address</h3>
          <input name="address.line1" placeholder="Address Line 1" onChange={handleChange} required className="full-width" />
          <input name="address.line2" placeholder="Address Line 2" onChange={handleChange} className="full-width" />
          <div className="input-grid">
            <input name="address.state" placeholder="State" onChange={handleChange} required />
            <input name="address.district" placeholder="District" onChange={handleChange} required />
            <input name="address.postalCode" placeholder="Postal Code" onChange={handleChange} required />
          </div>
        </div>

        {/* SECTION 5: Education */}
        <div className="form-section">
          <h3>Education Information</h3>
          <div className="input-grid">
            <input name="collegeName" placeholder="College Name" onChange={handleChange} required />
            <input name="university" placeholder="University" onChange={handleChange} required />
            <input name="city" placeholder="City" onChange={handleChange} required />
            <input name="graduationDiscipline" placeholder="Graduation Discipline" onChange={handleChange} required />
            <input name="grade" type="number" placeholder="Grade/CGPA" onChange={handleChange} required />
            <input name="schoolName" placeholder="School Name" onChange={handleChange} required />
            <input name="marks12" type="number" placeholder="12th Marks" onChange={handleChange} required />
            <input name="marks10" type="number" placeholder="10th Marks" onChange={handleChange} required />
          </div>
        </div>

        {/* SECTION 6: Document Uploads */}
        <div className="form-section docs">
          <h3>Required Documents</h3>
          <label>10th Marksheet</label>
          <input type="file" name="file10th" onChange={handleFileChange} required />
          
          <label>12th Marksheet</label>
          <input type="file" name="file12th" onChange={handleFileChange} required />
          
          <label>Degree Documents (Multiple)</label>
          <input type="file" name="degreeFiles" multiple onChange={handleFileChange} />
        </div>

        {/* SECTION 7: Backlogs */}
        <div className="form-section">
          <h3>Backlog Details</h3>
          <select name="hasActiveBacklogs" onChange={handleChange}>
            <option value="false">No Active Backlogs</option>
            <option value="true">Yes, I have Active Backlogs</option>
          </select>

          {student.hasActiveBacklogs && (
            <div className="backlog-sub-section">
              <input
                name="numberOfBacklogs"
                type="number"
                placeholder="Number of Backlogs"
                onChange={handleChange}
              />
              <label>Upload Backlog Files</label>
              <input
                type="file"
                name="backlogFiles"
                multiple
                onChange={handleFileChange}
              />
            </div>
          )}
        </div>

        <button type="submit" className="register-submit-btn">
          Complete Registration
        </button>
<div className="login-link-container" style={{ textAlign: 'center', marginTop: '20px', color: 'white' }}>
  <p>Already have an account? <span 
      onClick={() => navigate("/")} 
      style={{ color: '#6366f1', cursor: 'pointer', fontWeight: 'bold' }}>
      Login here
  </span></p>
</div>
      </form>
    </div>
  </div>
);
}
export default Register;
