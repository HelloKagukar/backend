package backend.kagukar.data.user;

import backend.kagukar.data.GeneralInfo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Parent extends GeneralInfo {

    @Id
    private String id;

    private LocalDateTime modifiedDate;

    private LocalDate createdDate;

    private Type userType;
}
