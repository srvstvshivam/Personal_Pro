package com.internshipmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

@Document(collection = "students")
public class Student {
A
    @Id
    private String id;

    private String internshipId;

    // ================= BASIC INFO (Registration Required) =================

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6)
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).+$",
        message = "Password must contain upper, lower, number and special character"
    )
    private String password;

    @NotBlank(message = "College name is required")
    private String collegeName;

    @NotBlank(message = "University name is required")
    private String university;

    private Boolean status = true;

    // ================= PROFILE INFO (Filled After Login) =================

    private String profileImageUrl;

    private String fatherName;

    private String dob; // YYYY-MM-DD

    private String mobileNumber;

    private String gender;

    @Valid
    private Address address;

    private String city;

    private String graduationDiscipline;

    private Double grade;

    private String schoolName;

    private Double marks12;

    private Double marks10;

    private String marksheet10thUrl;

    private String marksheet12thUrl;

    private List<String> degreeDocumentUrls;

    private Boolean hasActiveBacklogs;

    private Integer numberOfBacklogs;

    private List<String> backlogDocumentUrls;

    // ================= OTP =================

    private String otp;
    private Long otpExpiry;

    public Student() {}

    // ================= GETTERS & SETTERS =================

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getInternshipId() { return internshipId; }
    public void setInternshipId(String internshipId) { this.internshipId = internshipId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public String getCollegeName() { return collegeName; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }

    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }

    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }

    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getGraduationDiscipline() { return graduationDiscipline; }
    public void setGraduationDiscipline(String graduationDiscipline) { this.graduationDiscipline = graduationDiscipline; }

    public Double getGrade() { return grade; }
    public void setGrade(Double grade) { this.grade = grade; }

    public String getSchoolName() { return schoolName; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }

    public Double getMarks12() { return marks12; }
    public void setMarks12(Double marks12) { this.marks12 = marks12; }

    public Double getMarks10() { return marks10; }
    public void setMarks10(Double marks10) { this.marks10 = marks10; }

    public String getMarksheet10thUrl() { return marksheet10thUrl; }
    public void setMarksheet10thUrl(String marksheet10thUrl) { this.marksheet10thUrl = marksheet10thUrl; }

    public String getMarksheet12thUrl() { return marksheet12thUrl; }
    public void setMarksheet12thUrl(String marksheet12thUrl) { this.marksheet12thUrl = marksheet12thUrl; }

    public List<String> getDegreeDocumentUrls() { return degreeDocumentUrls; }
    public void setDegreeDocumentUrls(List<String> degreeDocumentUrls) { this.degreeDocumentUrls = degreeDocumentUrls; }

    public Boolean getHasActiveBacklogs() { return hasActiveBacklogs; }
    public void setHasActiveBacklogs(Boolean hasActiveBacklogs) { this.hasActiveBacklogs = hasActiveBacklogs; }

    public Integer getNumberOfBacklogs() { return numberOfBacklogs; }
    public void setNumberOfBacklogs(Integer numberOfBacklogs) { this.numberOfBacklogs = numberOfBacklogs; }

    public List<String> getBacklogDocumentUrls() { return backlogDocumentUrls; }
    public void setBacklogDocumentUrls(List<String> backlogDocumentUrls) { this.backlogDocumentUrls = backlogDocumentUrls; }

    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }

    public Long getOtpExpiry() { return otpExpiry; }
    public void setOtpExpiry(Long otpExpiry) { this.otpExpiry = otpExpiry; }

    // ================= ADDRESS CLASS =================

    public static class Address {

        private String line1;
        private String line2;
        private String state;
        private String district;
        private String postalCode;

        public String getLine1() { return line1; }
        public void setLine1(String line1) { this.line1 = line1; }

        public String getLine2() { return line2; }
        public void setLine2(String line2) { this.line2 = line2; }

        public String getState() { return state; }
        public void setState(String state) { this.state = state; }

        public String getDistrict() { return district; }
        public void setDistrict(String district) { this.district = district; }

        public String getPostalCode() { return postalCode; }
        public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    }
}
