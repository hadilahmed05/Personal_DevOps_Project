package com.arnab.studentcrud.student;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;
    private AutoCloseable autoCloseable;

    StudentServiceTest() {
    }

    @BeforeEach
    void setUp() {
        this.autoCloseable = MockitoAnnotations.openMocks(this);
        this.studentService = new StudentService(this.studentRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.autoCloseable.close();
    }

    @Test
    void addStudent() {
        Student student = new Student("tariq", "Science", 12);
        this.studentService.addStudent(student);
        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        ((StudentRepository) Mockito.verify(this.studentRepository)).save((Student)captor.capture());
        Student captured = (Student)captor.getValue();
        AssertionsForClassTypes.assertThat(captured).isEqualTo(student);
    }

    @Test
    void getStudentById() {
        long id = 15L;
        Optional<Student> getStudents = this.studentRepository.findById(id);
        ((StudentRepository)Mockito.verify(this.studentRepository)).findById(id);
        AssertionsForClassTypes.assertThat(Optional.empty()).isEqualTo(getStudents);
    }

    @Test
    void canGetAllStudent() {
        List<Student> allStudent = this.studentRepository.findAll();
        ((StudentRepository)Mockito.verify(this.studentRepository)).findAll();
        AssertionsForClassTypes.assertThat(this.studentRepository.count()).isEqualTo((long)allStudent.size());
    }

    @Test
    void deleteStudent() {
        long exstingId = 14L;
        this.studentRepository.deleteById(exstingId);
        ((StudentRepository)Mockito.verify(this.studentRepository)).deleteById(exstingId);
    }
}