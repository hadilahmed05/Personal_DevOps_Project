package com.arnab.studentcrud.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/"})
@CrossOrigin(
        origins = {"http://98.71.45.41:8081"}
)
public class StudentRestController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    public StudentRestController() {
    }

    // This should map to "/students" for GET requests
    @GetMapping({"/students"})
    public List<Student> getAllStudents() {
        return this.studentService.getAllStudent();
    }

    @PostMapping({"/students"})
    public Student addStudent(@RequestBody Student student) {
        return this.studentService.addStudent(student);
    }

    @GetMapping({"/students/{id}"})
    public Student getStudentById(@PathVariable long id) {
        return this.studentRepository.findById(id).get();
    }

    @PutMapping({"/students/{id}"})
    public Student updateStudent(@RequestBody Student student, @PathVariable long id) {
        Student uStudent = this.studentService.getStudentById(id);
        uStudent.setName(student.getName());
        uStudent.setGrp(student.getGrp());
        uStudent.setAge(student.getAge());
        return this.studentService.updateStudent(uStudent);
    }

    @DeleteMapping({"/students/{id}"})
    public void deleteStudentById(@PathVariable long id) {
        this.studentRepository.deleteById(id);
    }
}
