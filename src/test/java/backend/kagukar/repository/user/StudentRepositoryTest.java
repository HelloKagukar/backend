package backend.kagukar.repository.user;

import backend.kagukar.data.user.Student;
import backend.kagukar.data.user.Type;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Slf4j
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    public void setUp(){
        student = new Student();
    }

    @Test
    @DisplayName("Create Students")
    public void test_createStudent(){
        student.setEmail("okoroaforkelechi123@gmail.com");
        student.setCreatedDate(LocalDate.now());
        student.setUserType(Type.STUDENT);
        student.setName("Kelechi Divine");
        student.setDateOfBirth("16/12/2002");
        student.setPassword("passwordIsStrong");
        student.setPhoneNumber("09152624528");

        studentRepository.save(student);
        Assertions.assertThat(student.getId()).isNotNull();
        log.info("Created student successfully -> {}", student);
    }

    @Test
    @DisplayName("Find a student by id")
    public void test_findStudentWithA_GivenId() {
        student = studentRepository.findById("63ed8f653074127b84a4d48b").orElse(null);
        log.info("Student details --> {}", student);
    }

    @Test
    @DisplayName("Find all students")
    public void test_findAllStudents() {
        List<Student> students = studentRepository.findAll();
        log.info("List of students --> {}", students);
    }

    @Test
    @DisplayName("Delete a student account")
    public void test_deleteStudentAccount () {
        student = studentRepository.findById("63ed8f653074127b84a4d48a").orElse(null);
        Assertions.assertThat(studentRepository.existsById("63ed8f653074127b84a4d48a")).isTrue();
        studentRepository.deleteById("63ed8f653074127b84a4d48a");
    }

    @Test
    @DisplayName("Update student account")
    public void test_updateA_StudentAccount(){
        student = studentRepository.findById("63ed8f653074127b84a4d48b").orElse(null);
        Assertions.assertThat(student).isNotNull();
        student.setPhoneNumber("08082167764");
        studentRepository.updateStudent(student);
        org.junit.jupiter.api.Assertions.assertEquals("08082167764", student.getPhoneNumber());
    }

}