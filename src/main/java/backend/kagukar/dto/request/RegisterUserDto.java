package backend.kagukar.dto.request;

import backend.kagukar.data.user.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import backend.kagukar.data.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

    private String userName;

    private String email;

    private Type userType;

    private String phoneNumber;

    private String password;

    private Boolean isVerified;

    private String dateOfBirth;
}
