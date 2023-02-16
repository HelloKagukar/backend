package backend.kagukar.security;

import backend.kagukar.data.user.Parent;
import backend.kagukar.data.user.Student;
import backend.kagukar.data.user.Teacher;
import backend.kagukar.exception.AttemptAuthenticationException;
import backend.kagukar.repository.user.ParentRepository;
import backend.kagukar.repository.user.StudentRepository;
import backend.kagukar.repository.user.TeacherRepository;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.request.LoginDto;
import dto.request.ResponseDto;
import dto.request.UnsuccessfulLogin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import com.auth0.jwt.JWT;

import static backend.kagukar.security.SecurityConstants.*;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final TeacherRepository teacherRepository;

    LoginDto credential = new LoginDto();

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext context) {
        this.authenticationManager = authenticationManager;
        studentRepository = context.getBean(StudentRepository.class);
        parentRepository = context.getBean(ParentRepository.class);
        teacherRepository = context.getBean(TeacherRepository.class);
        setFilterProcessesUrl("/user/login");
    }

    @Override
    @ExceptionHandler(AttemptAuthenticationException.class)
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            credential = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credential.getEmail(), credential.getPassword(), new ArrayList<>()));
        } catch (IOException exception) {
            throw new RuntimeException("User authentication failed", exception);
        }
    }

    @SneakyThrows
    @Override
    @ExceptionHandler(AttemptAuthenticationException.class)
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes(StandardCharsets.UTF_8)));
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto responseDto;
        String studentEmail = ((User) authResult.getPrincipal()).getUsername();
        String teacherEmail = ((User) authResult.getPrincipal()).getUsername();
        String parentEmail = ((User) authResult.getPrincipal()).getUsername();

        if (credential.getType() != null && credential.getType().equals("STUDENT")) {
            Student student = studentRepository.findByEmail(studentEmail);
            if (student == null) {
                throw new javax.security.sasl.AuthenticationException("Student details should not be empty.");
            }
            responseDto = new ResponseDto();
            responseDto.setId(student.getId());
            responseDto.setCreatedDate(student.getCreatedDate().toString());
            responseDto.setUserName(student.getUserName());
            responseDto.setIsActive(student.getIsVerified());
            responseDto.setIsVerified(student.getIsVerified());
            responseDto.setPhoneNumber(student.getEmail());
            responseDto.setToken(token);
        }

        if (credential.getType() != null && credential.getType().equals("PARENT")) {
            Parent parent = parentRepository.findByEmail(parentEmail);
            if (parent == null) {
                throw new javax.security.sasl.AuthenticationException("Parent details should not be empty.");
            }
            responseDto = new ResponseDto();
            responseDto.setId(parent.getId());
            responseDto.setCreatedDate(parent.getCreatedDate().toString());
            responseDto.setUserName(parent.getUserName());
            responseDto.setIsActive(parent.getIsVerified());
            responseDto.setIsVerified(parent.getIsVerified());
            responseDto.setPhoneNumber(parent.getEmail());
            responseDto.setToken(token);
        }

        if (credential.getType() != null && credential.getType().equals("TEACHER")) {
            Teacher teacher = teacherRepository.findByEmail(teacherEmail);
            if (teacher == null) {
                throw new javax.security.sasl.AuthenticationException("Teacher details should not be empty.");
            }
            responseDto = new ResponseDto();
            responseDto.setId(teacher.getId());
            responseDto.setCreatedDate(teacher.getCreatedDate().toString());
            responseDto.setUserName(teacher.getUserName());
            responseDto.setIsActive(teacher.getIsVerified());
            responseDto.setIsVerified(teacher.getIsVerified());
            responseDto.setPhoneNumber(teacher.getEmail());
            responseDto.setToken(token);
        }
        else {
            throw new AttemptAuthenticationException("Authentication failed, void user");
        }
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        response.getOutputStream().print("{ \"data\": " + objectMapper.writeValueAsString(responseDto) + "}");
        response.flushBuffer();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        UnsuccessfulLogin responseDetails = new UnsuccessfulLogin(LocalDateTime.now(), "Login error. Incorrect email or password.", "Bad request", "/user/login");
        response.getOutputStream().print("{ \"message\": " + responseDetails + "}");
    }
}
