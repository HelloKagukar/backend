package backend.kagukar.service.user.appUser;

import backend.kagukar.data.user.AppUser;
import backend.kagukar.dto.request.RegisterUserDto;
import backend.kagukar.exception.PasswordException;
import backend.kagukar.repository.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImplementation implements AppUserService{

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public AppUser registerUser(RegisterUserDto user) throws PasswordException {
        if(user.getPassword().length() < 8){
            throw new PasswordException("Password should not be less than 8 characters");
        }
        AppUser newUser = new AppUser();
        newUser.setUserName(user.getUserName());
        newUser.setUserType(user.getUserType());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setIsVerified(user.getIsVerified());
        newUser.setDateOfBirth(user.getDateOfBirth());
        return appUserRepository.save(newUser);
    }
}
