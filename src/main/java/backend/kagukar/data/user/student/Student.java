package backend.kagukar.data.user.student;

import backend.kagukar.data.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    private String id;

    private AppUser student;


}
