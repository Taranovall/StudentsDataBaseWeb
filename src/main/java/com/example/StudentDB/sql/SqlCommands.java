package com.example.StudentDB.sql;

import com.example.StudentDB.entity.Student;

import java.util.List;

public interface SqlCommands {
    Student getStudent(Long id);

    void add(Student student);

    boolean remove(Long id);

    List<Student> getAll();

    boolean update(Student student, Long id);






}
