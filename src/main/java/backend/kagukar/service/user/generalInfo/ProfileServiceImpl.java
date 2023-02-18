package backend.kagukar.service.user.generalInfo;

import backend.kagukar.data.user.Profile;
import backend.kagukar.data.user.Type;
import backend.kagukar.data.user.student.Student;
import backend.kagukar.dto.request.RegisterUserDto;
import backend.kagukar.exception.PasswordException;
import backend.kagukar.repository.user.profile.ProfileRepository;
import backend.kagukar.repository.user.student.StudentRepository;
import backend.kagukar.service.user.student.StudentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Student student;

    @Autowired
    private StudentServiceImpl studentService;


    public boolean userProfileDoesNotExist(String email) {
        return !profileRepository.existsByEmail(email);
    }

    @Override
    public Profile createProfile(RegisterUserDto registerUserDto) throws PasswordException {
        if (registerUserDto.getPassword().length() == 6) {
            throw new PasswordException("Password must be 6 characters long");
        }
        Profile profile = new Profile();
        profile.setEmail(registerUserDto.getEmail());
        profile.setPassword(registerUserDto.getPassword());
        profile.setDateOfBirth(registerUserDto.getDateOfBirth());
        profile.setIsVerified(registerUserDto.getIsVerified());
        profile.setPhoneNumber(registerUserDto.getPhoneNumber());
        profile.setUserType(registerUserDto.getUserType());

        if (profile.getUserType() == Type.STUDENT){
            student = modelMapper.map(profile, Student.class);
            studentRepository.save(student);
        }
        return profileRepository.save(profile);
    }
}
