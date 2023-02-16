package backend.kagukar.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class GeneralInfo {

    private String name;

    private String email;

    private String phoneNumber;

    private String password;

    private String dateOfBirth;
}
