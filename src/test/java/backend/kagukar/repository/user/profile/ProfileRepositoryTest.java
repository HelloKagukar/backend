package backend.kagukar.repository.user.profile;

import backend.kagukar.data.user.Profile;
import backend.kagukar.data.user.Type;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@SpringBootTest
@Slf4j
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;

    private Profile profile;

    @BeforeEach
    public void setUp(){
        profile = new Profile();
    }

    @Test
    public void test_canCreateProfile(){
        profile.setEmail("okoroaforkelechi123@gmail.com");
        profile.setCreatedDate(LocalDate.now());
        profile.setUserType(Type.STUDENT);
        profile.setUserName("Kelechi Divine mah");
        profile.setDateOfBirth("16/12/2002");
        profile.setPassword("Strong");
        profile.setPhoneNumber("09152624528");

        profileRepository.save(profile);
        Assertions.assertThat(profile.getId()).isNotNull();
        log.info("Created student successfully -> {}", profile);
    }

}