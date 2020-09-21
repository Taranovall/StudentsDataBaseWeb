package com.example.StudentDB.controllers;


import com.example.StudentDB.entity.Student;
import com.example.StudentDB.sql.SqlCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class MainController {

    private final SqlCommands sql;

    @Autowired
    public MainController(SqlCommands sql) {
        this.sql = sql;
    }


    @GetMapping("/")
    public String allStudents(Model model) {
        Iterable<Student> students = sql.getAll();
        model.addAttribute("students", students);
        return "studentslist";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("name");
        return "index";
    }

    @PostMapping("/add")
    public String postAdd(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age, @RequestParam String number, @RequestParam int clas, @RequestParam String subClass, Model model) {
        Student student = new Student(firstName, lastName, number, age, clas, subClass);
        model.addAttribute("student", student);
        List<Student> students = sql.getAll();
        boolean studentIsExists = false;
        for (int i = 0; i < students.size(); i++) {
            Student st = students.get(i);
            if (student.equals(st)) {
                studentIsExists = true;
            }
        }
        if (!studentIsExists) {
            sql.add(student);
            return "redirect:/";
        } else {
            return "error/sutdentIsExist";
        }
    }


    @GetMapping("/edit/{id}")
    public String studentInfo(@PathVariable(value = "id") Long id, Model model) {
        Student student = sql.getStudent(id);
        if (student == null) {
            return "redirect:/";
        }
        model.addAttribute("student", student);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String studentInfoEdit(@PathVariable(value = "id") Long id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam int age, @RequestParam String number, @RequestParam int clas, @RequestParam String subClass) {
        Student student = sql.getStudent(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        student.setNumber(number);
        student.setClas(clas);
        student.setSubClass(subClass);
        sql.update(student, id);
        return "redirect:/";
    }

    @PostMapping("/remove/{id}")
    public String removeStudent(@PathVariable(value = "id") Long id) {
        sql.remove(id);
        return "redirect:/";
    }


}
