package backend.kagukar.controller;

import backend.kagukar.data.user.Type;
import backend.kagukar.dto.request.RegisterUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ProfileControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

//    private RegisterUserDto registerUserDto;

//    @BeforeEach
//    public void setUp(){
//        objectMapper = new ObjectMapper();
//        registerUserDto = new RegisterUserDto();
//    }

    @Test
    @DisplayName("Create account")
    public void test_createAccount() throws Exception {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUserName("I love Jesus");
        registerUserDto.setEmail("ezekielakintunde1@gmail.com");
        registerUserDto.setUserType(Type.STUDENT);
        registerUserDto.setPassword("testpassword");
        registerUserDto.setPhoneNumber("123456");

        this.mockMvc.perform(post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerUserDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}