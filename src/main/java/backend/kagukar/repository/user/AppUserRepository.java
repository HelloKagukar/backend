package backend.kagukar.repository.user;

import backend.kagukar.data.user.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser, String> {

    default  void updateStudent(AppUser appUser){
        appUser.setModifiedDate(LocalDateTime.now());
        save(appUser);
    }

    AppUser findByEmail(String userEmail);
}
