package backend.kagukar.data.user.parent;

import backend.kagukar.data.user.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Parent {

    @Id
    private String id;

    private Profile profile;
}
