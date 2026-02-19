package com.internshipmanagement.controller;

import com.internshipmanagement.config.JwtUtil;
import com.internshipmanagement.model.LoginRequest;
import com.internshipmanagement.model.Student;
import com.internshipmanagement.repository.StudentRepository;
import com.internshipmanagement.service.CloudinaryService;
import com.internshipmanagement.service.EmailService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private EmailService emailService;

    // ===============================
    // 🔹 REGISTER (MULTIPART + JSON)
    // ===============================
    @PostMapping(value = "/register", consumes = "multipart/form-data")
    public ResponseEntity<?> registerStudent(

            @RequestPart("student") @Valid Student student,

            @RequestPart(value = "profileImgFile", required = false) MultipartFile profileImg,
            @RequestPart(value = "file10th", required = false) MultipartFile file10th,
            @RequestPart(value = "file12th", required = false) MultipartFile file12th,

            @RequestPart(value = "degreeFiles", required = false)
            List<MultipartFile> degreeFiles,

            @RequestPart(value = "backlogFiles", required = false)
            List<MultipartFile> backlogFiles
    ) {

        // 🔥 PRINT WHAT IS COMING
        System.out.println("========== STUDENT DATA ==========");
        System.out.println("First Name: " + student.getFirstName());
        System.out.println("Last Name: " + student.getLastName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Grade: " + student.getGrade());
        System.out.println("Marks12: " + student.getMarks12());
        System.out.println("Marks10: " + student.getMarks10());
        System.out.println("Has Backlogs: " + student.getHasActiveBacklogs());
        System.out.println("Number of Backlogs: " + student.getNumberOfBacklogs());

        if (student.getAddress() != null) {
            System.out.println("Address Line1: " + student.getAddress().getLine1());
            System.out.println("State: " + student.getAddress().getState());
            System.out.println("District: " + student.getAddress().getDistrict());
            System.out.println("PostalCode: " + student.getAddress().getPostalCode());
        } else {
            System.out.println("Address is NULL");
        }

        System.out.println("==================================");

        try {

            // 🔹 Email check
            if (studentRepository.existsByEmail(student.getEmail())) {
                return ResponseEntity.badRequest().body("Email already registered");
            }

            // 🔹 Encrypt password
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            student.setStatus(true);

            // 🔹 Upload mandatory files
            student.setProfileImageUrl(cloudinaryService.uploadFile(profileImg));
            student.setMarksheet10thUrl(cloudinaryService.uploadFile(file10th));
            student.setMarksheet12thUrl(cloudinaryService.uploadFile(file12th));

            // 🔹 Degree files
            if (degreeFiles != null) {
                List<String> degreeUrls = new ArrayList<>();
                for (MultipartFile file : degreeFiles) {
                    if (!file.isEmpty()) {
                        degreeUrls.add(cloudinaryService.uploadFile(file));
                    }
                }
                student.setDegreeDocumentUrls(degreeUrls);
            }

            // 🔹 Backlog files (only if YES)
            if (Boolean.TRUE.equals(student.getHasActiveBacklogs()) && backlogFiles != null) {
                List<String> backlogUrls = new ArrayList<>();
                for (MultipartFile file : backlogFiles) {
                    if (!file.isEmpty()) {
                        backlogUrls.add(cloudinaryService.uploadFile(file));
                    }
                }
                student.setBacklogDocumentUrls(backlogUrls);
            }

            studentRepository.save(student);

            return ResponseEntity.ok("Registration Successful");

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Registration Failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginStudent(@RequestBody LoginRequest loginRequest) {

        Optional<Student> optionalStudent =
                studentRepository.findByEmail(loginRequest.getEmail());

        if (optionalStudent.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid Email");
        }

        Student student = optionalStudent.get();

        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                student.getPassword())) {

            return ResponseEntity.badRequest().body("Invalid Password");
        }

        String token = jwtUtil.generateToken(loginRequest.getEmail());

        return ResponseEntity.ok(token);
    }
   

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {

        Optional<Student> optional = studentRepository.findByEmail(email);

        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body("Email not registered");
        }

        Student student = optional.get();

        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);

        student.setOtp(otp);
        student.setOtpExpiry(System.currentTimeMillis() + (1000 * 60 * 10)); // 10 min

        studentRepository.save(student);

        try {
            emailService.sendOtpEmail(email, otp);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error sending email");
        }

        return ResponseEntity.ok("OTP Sent Successfully");
    }
   
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(
            @RequestParam("email") String email,
            @RequestParam("otp") String otp,
            @RequestParam("newPassword") String newPassword)
 {

        Optional<Student> optional = studentRepository.findByEmail(email);

        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid Email");
        }

        Student student = optional.get();

        if (!otp.equals(student.getOtp())) {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }

        if (student.getOtpExpiry() < System.currentTimeMillis()) {
            return ResponseEntity.badRequest().body("OTP Expired");
        }

        student.setPassword(passwordEncoder.encode(newPassword));
        student.setOtp(null);
        student.setOtpExpiry(null);

        studentRepository.save(student);

        return ResponseEntity.ok("Password Reset Successful");
    }

}