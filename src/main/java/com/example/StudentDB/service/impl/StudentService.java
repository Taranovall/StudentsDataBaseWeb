package com.example.StudentDB.service.impl;

import com.example.StudentDB.entity.Student;

import java.util.List;

public interface StudentService {
    Student getStudent(Long id);

    boolean add(Student student);

    boolean remove(Long id);

    List<Student> getAll();

    Student getStudentByNumber(String number);

    boolean update(Student student, Long id);






}
