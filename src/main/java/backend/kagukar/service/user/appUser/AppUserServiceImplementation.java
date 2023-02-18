package backend.kagukar.service.user.appUser;

import backend.kagukar.data.user.AppUser;
import backend.kagukar.data.user.Type;
import backend.kagukar.dto.request.RegisterUserDto;
import backend.kagukar.exception.PasswordException;
import backend.kagukar.repository.user.AppUserRepository;
import backend.kagukar.repository.user.parent.ParentRepository;
import backend.kagukar.repository.user.student.StudentRepository;
import backend.kagukar.repository.user.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImplementation implements AppUserService{

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ParentRepository parentRepository;


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
