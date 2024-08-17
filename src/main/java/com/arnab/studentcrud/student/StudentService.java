package com.arnab.studentcrud.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return (Student)this.studentRepository.save(student);
    }

    public Student getStudentById(long id) {
        Optional<Student> student1 = this.studentRepository.findById(id);
        if (student1.isEmpty()) {
            throw new RuntimeException("Student not found");
        } else {
            return (Student)student1.get();
        }
    }

    public List<Student> getAllStudent() {
        return this.studentRepository.findAll();
    }

    public Student updateStudent(Student student) {
        Optional<Student> oldStudent = this.studentRepository.findById(student.getId());
        if (oldStudent.isEmpty()) {
            throw new RuntimeException("Student not found");
        } else {
            Student udateStudent = (Student)oldStudent.get();
            udateStudent.setName(student.getName());
            udateStudent.setGrp(student.getGrp());
            udateStudent.setAge(student.getAge());
            udateStudent.setId(student.getId());
            return (Student)this.studentRepository.save(udateStudent);
        }
    }

    public void deleteStudent(long id) {
        Optional<Student> student = this.studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new RuntimeException("No student found");
        } else {
            this.studentRepository.deleteById(id);
        }
    }
}
