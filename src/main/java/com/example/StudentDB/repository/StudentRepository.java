package com.example.StudentDB.repository;

import com.example.StudentDB.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}