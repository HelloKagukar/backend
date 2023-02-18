package backend.kagukar.data.user.teacher;

import backend.kagukar.data.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teacher {
    @Id
    private String id;

    private AppUser teacher;
}
