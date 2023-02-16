package backend.kagukar.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class GeneralInfo {

    private String userName;

    private String email;

    private String phoneNumber;

    private String password;

    private Boolean isVerified;

    private String dateOfBirth;
}
