package ru.kholmogorova.kotiki2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kholmogorova.kotiki2.DTO.AuthenticationResponseDTO;
import ru.kholmogorova.kotiki2.DTO.SignInDTO;
import ru.kholmogorova.kotiki2.DTO.SignUpDTO;
import ru.kholmogorova.kotiki2.Entity.OwnerEntity;
import ru.kholmogorova.kotiki2.Entity.Role;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final OwnerService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public AuthenticationResponseDTO signUp(SignUpDTO request) {

        var user = OwnerEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public AuthenticationResponseDTO signIn(SignInDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(jwt);
    }
}
