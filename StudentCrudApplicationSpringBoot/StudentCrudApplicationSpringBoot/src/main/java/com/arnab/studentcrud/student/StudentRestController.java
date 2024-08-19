package com.arnab.studentcrud.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping({"/"})
@CrossOrigin(
        origins = {"http://localhost:4200"}
)

public class StudentRestController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    public StudentRestController() {
    }

    @GetMapping({"/"})
    public List<Student> getAllStudents() {
        return this.studentService.getAllStudent();
    }

    @PostMapping({"/students"})
    public Student addStudent(@RequestBody Student student) {
        return this.studentService.addStudent(student);
    }

    @GetMapping({"/students/{id}"})
    public Student getStudentById(@PathVariable long id) {
        return (Student)this.studentRepository.findById(id).get();
    }

    @PutMapping({"/students/{id}"})
    public Student updateStudent(@RequestBody Student student, @PathVariable long id) {
        Student uStudent = this.studentService.getStudentById(id);
        uStudent.setName(student.getName());
        uStudent.setGrp(student.getGrp());
        uStudent.setAge(student.getAge());
        Student updatedStudent = this.studentService.updateStudent(uStudent);
        return updatedStudent;
    }

    @DeleteMapping({"/students/{id}"})
    public void deleteStudentById(@PathVariable long id) {
        this.studentRepository.deleteById(id);
    }
}

