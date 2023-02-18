package backend.kagukar.service.user.appUser;

import backend.kagukar.data.user.AppUser;
import backend.kagukar.dto.request.RegisterUserDto;
import backend.kagukar.exception.PasswordException;

public interface AppUserService {

    AppUser registerUser(RegisterUserDto user) throws PasswordException;

}
