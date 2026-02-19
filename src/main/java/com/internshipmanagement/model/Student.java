package com.internshipmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

@Document(collection = "students")
public class Student {

    @Id
    private String id;

    // Basic Information 

       private String profileImageUrl;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Father's name is required")
    private String fatherName;

    @NotBlank(message = "Date of birth is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in format YYYY-MM-DD")
    private String dob; 

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be exactly 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Gender is required")
    private String gender;

    // Login Details 

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
      message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

   
    private Boolean status; 
    
    //  Embedded Address 

    @NotNull(message = "Address details are required")
    @Valid // This ensures the validations inside the Address class are checked
    private Address address;

    //  Education Details 

    @NotBlank(message = "College name is required")
    private String collegeName;

    @NotBlank(message = "University name is required")
    private String university;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Graduation discipline is required")
    private String graduationDiscipline; 

    @NotNull(message = "Grade/CGPA is required")
    @Min(value = 0, message = "Grade cannot be negative")
    private Double grade;

    @NotBlank(message = "School name is required")
    private String schoolName;

    @NotNull(message = "12th Marks are required")
    @Min(value = 0, message = "Marks cannot be negative")
    @Max(value = 100, message = "Marks cannot exceed 100") // Assuming percentage
    private Double marks12;

    @NotNull(message = "10th Marks are required")
    @Min(value = 0, message = "Marks cannot be negative")
    @Max(value = 100, message = "Marks cannot exceed 100")
    private Double marks10;

   
     private String marksheet10thUrl;

     private String marksheet12thUrl;
    
    private List<String> degreeDocumentUrls; 
    
    //  Backlog Details 

    @NotNull(message = "Backlog status is required")
    private Boolean hasActiveBacklogs; 

    @Min(value = 0, message = "Number of backlogs cannot be negative")
    private Integer numberOfBacklogs;  

    private List<String> backlogDocumentUrls; 
    
    // Forgotten email
    private String otp;
    
	private Long otpExpiry;

    public Student() {}

    // Getters and Setters remain the same...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    public String getCollegeName() { return collegeName; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }
    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }
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
    public String getOtp() {return otp;	}
	public void setOtp(String otp) {this.otp = otp;	}
	public Long getOtpExpiry() {return otpExpiry;}
	public void setOtpExpiry(Long otpExpiry) {this.otpExpiry = otpExpiry;}
    // --- Inner Address Class with Validations ---
    public static class Address {
        
        @NotBlank(message = "Address Line 1 is required")
        private String line1;
        
        private String line2; // Optional
        
        @NotBlank(message = "State is required")
        private String state;
        
        @NotBlank(message = "District is required")
        private String district;
        
        @NotBlank(message = "Postal code is required")
        @Pattern(regexp = "^\\d{6}$", message = "Postal code must be 6 digits")
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