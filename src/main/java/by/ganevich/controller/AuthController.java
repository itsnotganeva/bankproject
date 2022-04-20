package by.ganevich.controller;


import by.ganevich.config.security.jwt.JwtProvider;
import by.ganevich.dto.AuthResponseDto;
import by.ganevich.dto.RegistrationRequestDto;
import by.ganevich.entity.Client;
import by.ganevich.entity.ClientType;
import by.ganevich.entity.User;
import by.ganevich.service.ClientService;
import by.ganevich.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final ClientService clientService;
    private UserService userService;
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegistrationRequestDto registrationRequest) {
        User u = new User();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        userService.saveUser(u, "ROLE_CLIENT");

        Client client = new Client();
        client.setName(registrationRequest.getName());
        client.setType(ClientType.valueOf(registrationRequest.getType()));
        client.setUser(u);
        clientService.save(client);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponseDto auth(@RequestBody RegistrationRequestDto request) {
        User userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthResponseDto(token);
    }
}
