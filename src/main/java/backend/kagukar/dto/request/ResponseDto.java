package backend.kagukar.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private String id;

    private String token;

    private String userName;

    private String phoneNumber;

    private String email;

    private Boolean isVerified;

    private String createdDate;

    private String verificationToken;

    private Boolean isActive;

    private String modifiedDate;
}
