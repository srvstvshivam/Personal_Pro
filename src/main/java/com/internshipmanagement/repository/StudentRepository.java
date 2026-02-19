package com.internshipmanagement.repository;

import com.internshipmanagement.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    // 🔐 For LOGIN (fetch student by email)
    Optional<Student> findByEmail(String email);

    // ✅ For REGISTER (check duplicate email)
    boolean existsByEmail(String email);

    // 📌 Optional: get only active students
    List<Student> findByStatusTrue();

}
