package com.example.StudentDB.service;

import com.example.StudentDB.entity.Student;
import com.example.StudentDB.repository.StudentRepository;
import com.example.StudentDB.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceDefault implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student getStudent(Long id) {
        Optional<Student> studentId = studentRepository.findById(id);
        return studentId.orElse(null);
    }

    public Student getStudentByNumber(String number) {
        Optional<Student> studentByNumber = Optional.ofNullable(studentRepository.findByNumber(number));
        return studentByNumber.orElse(null);
    }

    @Override
    public boolean add(Student student) {
        studentRepository.save(student);
        return true;
    }


    @Override
    public boolean remove(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public boolean update(Student student, Long id) {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            studentRepository.save(student);
            return true;
        }
        return false;
    }


}

