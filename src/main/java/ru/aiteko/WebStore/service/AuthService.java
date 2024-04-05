package ru.aiteko.WebStore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.aiteko.WebStore.dto.LoginDto;
import ru.aiteko.WebStore.dto.SignUpDto;
import ru.aiteko.WebStore.entity.Role;
import ru.aiteko.WebStore.entity.Users;
import ru.aiteko.WebStore.exception.AitekoException;
import ru.aiteko.WebStore.exception.ErrorType;
import ru.aiteko.WebStore.repository.RoleRepository;
import ru.aiteko.WebStore.repository.UserRepository;
import ru.aiteko.WebStore.service.mapper.UserMapper;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;


    public void signUp(SignUpDto signUpDto) {
        log.info("Sign up a new user: {}", signUpDto);

        //Проверка на уникальность username в БД
        if(userRepo.existsByUsername(signUpDto.getUsername())){
            new ResponseEntity<>("Такой Username уже использован кем то!", HttpStatus.BAD_REQUEST);
        }

        //Проверка на уникальность Email в БД
        if(userRepo.existsByEmail(signUpDto.getEmail())){
            new ResponseEntity<>("Такой Email уже использован кем то!", HttpStatus.BAD_REQUEST);
        }

        Users user = userMapper.toEntity(signUpDto);

        Role roles = roleRepo.findByName("ROLE_USER")
                .orElseThrow(() -> new AitekoException(ErrorType.COMMON_ERROR,
                        "Внутренняя ошибка сервера, пожалуйста обратитесь к администратору"));
        user.setRoles(Collections.singleton(roles));

        userRepo.save(user);
        new ResponseEntity<>("Пользователь успешно зарегистрирован", HttpStatus.OK);
    }

    public void authenticateUser(LoginDto loginDto){
        log.info("LogIn a user: {}", loginDto);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
