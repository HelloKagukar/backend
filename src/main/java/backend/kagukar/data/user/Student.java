package backend.kagukar.data.user;

import backend.kagukar.data.GeneralInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends GeneralInfo {

    @Id
    private String id;

    private LocalDateTime modifiedDate;

    private LocalDate createdDate;

    private Type userType;
}
