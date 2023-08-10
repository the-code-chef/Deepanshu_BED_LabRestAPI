package com.greatlearning.collegefest.controller;

import com.greatlearning.collegefest.entity.Student;
import com.greatlearning.collegefest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/collegefest")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public String redirectToHomePage() {
        return "redirect:/collegefest/students";
    }
    @GetMapping("/students")
    public String getAllStudentsView(Model model) {
        List<Student> studentList =  studentService.findAll();
        model.addAttribute("studentList", studentList);
        return "index";
    }

    @GetMapping("/students/new")
    public String createStudentView(Model model) {
        Student student = new Student();
        model.addAttribute("newStudent", student);
        return "create";
    }

    @PostMapping("/students/insert")
    public String insertNewStudent(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/collegefest/students";
    }

    @GetMapping("/students/update/{id}")
    public String updateStudentRecord(Model model, @PathVariable Integer id) throws Exception {
        Optional<Student> student = studentService.findById(id);
        model.addAttribute("student", student);
        return "edit";
    }

    @PostMapping("/students/update/{id}")
    public String updateStudentRecord(@PathVariable Integer id, @ModelAttribute Student student) throws Exception {
        studentService.updateStudent(id, student);
        return "redirect:/collegefest/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudentRecord(@PathVariable("id") Integer id) {
        studentService.deleteById(id);
        return "redirect:/collegefest/students";
    }
}
