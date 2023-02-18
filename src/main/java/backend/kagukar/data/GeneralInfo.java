package backend.kagukar.data;

import backend.kagukar.data.user.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class GeneralInfo {

    private String userName;

    private String email;

    private String phoneNumber;

    private String password;

    private Type userType;

    private Boolean isVerified;

    private String dateOfBirth;

    private LocalDate createdDate;
}
