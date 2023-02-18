package backend.kagukar.dto.request;

import backend.kagukar.data.user.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import backend.kagukar.data.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

    @NotEmpty(message = "User name is required")
    @Size(min = 2, max = 20, message = "First name should be 2 to 20 character long")
    private String userName;

    @NotEmpty(message = "User email is required")
    @Pattern(regexp = "^[@]\\s")
    private String email;

    private Type userType;

    @NotEmpty(message = "Phone number is required")
    private String phoneNumber;

    @NotEmpty(message = "First name should be 2 to 20 character long")
    private String password;

    private Boolean isVerified;

    private String dateOfBirth;
}
