package backend.kagukar.repository.user;

import backend.kagukar.data.user.Student;
import backend.kagukar.data.user.Type;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@RunWith(SpringRunner.class)
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

}