package backend.kagukar.controller;

import backend.kagukar.dto.request.RegisterUserDto;
import backend.kagukar.dto.response.ResponseDetails;
import backend.kagukar.exception.PasswordException;
import backend.kagukar.service.user.generalInfo.ProfileServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileController {

    @Autowired
    private ProfileServiceImpl userService;

    @PostMapping("/")
    public ResponseEntity<?> registerProfile(@Valid @RequestBody RegisterUserDto registerDto, HttpServletRequest httpServletRequest) throws Exception {
        if (userService.userProfileDoesNotExist(registerDto.getEmail())) {
            userService.createProfile(registerDto);
        }
        else {
            throw new PasswordException("User with that email already exist.");
        }
        ResponseDetails responseDetails = new ResponseDetails(LocalDateTime.now(), "Your account has been created successfully.", HttpStatus.OK.toString());
        return ResponseEntity.status(200).body(responseDetails);
    }
}
