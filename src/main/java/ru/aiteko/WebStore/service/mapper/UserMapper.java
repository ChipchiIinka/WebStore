package ru.aiteko.WebStore.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aiteko.WebStore.authfacade.IAuthenticationFacade;
import ru.aiteko.WebStore.dto.SignUpDto;
import ru.aiteko.WebStore.dto.UserDto;
import ru.aiteko.WebStore.entity.Users;
import ru.aiteko.WebStore.exception.AitekoException;
import ru.aiteko.WebStore.exception.ErrorType;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserDto toDto(Users user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    private String getAccessPassword(SignUpDto signUpDto){
        if (signUpDto.getConfirmPassword().equals(signUpDto.getPassword())){
            return signUpDto.getConfirmPassword();
        }
        else throw new AitekoException(ErrorType.CLIENT_ERROR, "Пароли должны совподать!");
    }

    public Users toEntity(SignUpDto signUpDto){
        Users user = new Users();

        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(getAccessPassword(signUpDto)));
        user.setEmail(signUpDto.getEmail());

        return user;
    }
}