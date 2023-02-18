package backend.kagukar.service.user.generalInfo;

import backend.kagukar.data.user.Profile;
import backend.kagukar.dto.request.RegisterUserDto;
import backend.kagukar.exception.PasswordException;

public interface ProfileService {

    Profile createProfile(RegisterUserDto registerUserDto) throws PasswordException;
}
