package backend.kagukar.data.user;

import backend.kagukar.data.GeneralInfo;
import lombok.*;
import org.springframework.data.annotation.Id;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Profile extends GeneralInfo {

    @Id
    private String id;

}
