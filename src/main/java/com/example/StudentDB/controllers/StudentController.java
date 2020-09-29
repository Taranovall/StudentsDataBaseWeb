package com.example.StudentDB.controllers;


import com.example.StudentDB.entity.Student;
import com.example.StudentDB.repository.StudentRepository;
import com.example.StudentDB.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    private StudentRepository studentRepository;


    @GetMapping("/")
    public String allStudents(Model model) {
        Iterable<Student> students = studentService.getAll();
        model.addAttribute("students", students);
        return "studentslist";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("name");
        return "addStudent";
    }

    @PostMapping("/add")
    public String postAdd(@RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam int age,
                          @RequestParam String number,
                          @RequestParam int clas,
                          @RequestParam String subClass, Model model) {
        Student student = new Student(firstName, lastName, number, age, clas, subClass);
        model.addAttribute("student", student);
        boolean validate = validator(student, model);
        if (validate) {
            return "addStudent";
        }
        studentService.add(student);
        return "redirect:/";

    }


    @GetMapping("/edit/{id}")
    public String studentInfo(@PathVariable(value = "id") Long id, Model model) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return "redirect:/";
        }
        model.addAttribute("student", student);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String studentInfoEdit(@PathVariable(value = "id") Long id,
                                  @RequestParam String firstName,
                                  @RequestParam String lastName,
                                  @RequestParam int age,
                                  @RequestParam String number,
                                  @RequestParam int clas,
                                  @RequestParam String subClass, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        boolean validate = validator(student,firstName,lastName,number,model);
        if (validate) {
            return "edit";
        }
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        student.setNumber(number);
        student.setClas(clas);
        student.setSubClass(subClass);
        studentService.update(student, id);
        return "redirect:/";
    }

    @PostMapping("/remove/{id}")
    public String removeStudent(@PathVariable(value = "id") Long id) {
        studentService.remove(id);
        return "redirect:/";
    }

    private boolean validator(Student student, Model model) {
        Student st = studentRepository.findByFirstNameAndLastName(student.getFirstName(),student.getLastName());
        if (st != null && student.getFirstName().equals(st.getFirstName()) && student.getLastName().equals(st.getLastName())) {
                model.addAttribute("studentIsExist", true);
                return true;
        }
        st = studentService.getStudentByNumber(student.getNumber());
        if (st != null && student.getNumber().equals(st.getNumber())) {
            model.addAttribute("numberIsExist", true);
            return true;
        }
        return false;
    }

    private boolean validator(Student student,String firstName,String lastName,String number,Model model) {
        Student st = studentService.getStudentByNumber(number);
        if (st != null && !student.getNumber().equals(number)) {
            model.addAttribute("numberIsExist",true)
            .addAttribute("number", number);
            return true;
        }
        st = studentRepository.findByFirstNameAndLastName(firstName,lastName);
        if (st != null && !(student.getFirstName().equals(firstName) && student.getLastName().equals(lastName))) {
            model.addAttribute("studentIsExist",true)
            .addAttribute("firstName",firstName)
            .addAttribute("lastName",lastName);
            return true;
        }
        return false;
    }

}
