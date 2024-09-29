package ru.kholmogorova.kotiki2;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kholmogorova.kotiki2.DTO.AuthenticationResponseDTO;
import ru.kholmogorova.kotiki2.DTO.SignInDTO;
import ru.kholmogorova.kotiki2.DTO.SignUpDTO;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public AuthenticationResponseDTO signUp(@RequestBody @Valid SignUpDTO request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public AuthenticationResponseDTO signIn(@RequestBody @Valid SignInDTO request) {
        return authenticationService.signIn(request);
    }
}
