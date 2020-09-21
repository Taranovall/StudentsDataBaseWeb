package com.example.StudentDB.sql;

import com.example.StudentDB.entity.Student;
import com.example.StudentDB.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SqlCommandsDefault implements SqlCommands {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Student getStudent(Long id) {
        return studentRepository.getOne(id);

    }

    @Override
    public void add(Student student) {
        studentRepository.save(student);
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

