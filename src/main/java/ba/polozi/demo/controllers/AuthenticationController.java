package ba.polozi.demo.controllers;

import ba.polozi.demo.services.AuthService;
import ba.polozi.demo.services.ConfirmationTokenService;
import ba.polozi.demo.models.ConfirmationToken;
import ba.polozi.demo.models.ErrorMessage;
import ba.polozi.demo.models.User;
import ba.polozi.demo.requests.LoginRequest;
import ba.polozi.demo.requests.RegistrationRequest;
import ba.polozi.demo.responses.LoginResponse;
import ba.polozi.demo.responses.RegistrationResponse;
import ba.polozi.demo.responses.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthService authService;
    private final ConfirmationTokenService confirmationTokenService;

    @PostMapping(path = "registration")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        if (authService.exist(request)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "User already exists! Please try again.")));
        }
        User result = authService.createUserAccount(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/**")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(
                new RegistrationResponse(
                        new UserResponse(result)
                )
        );
    }

    @PostMapping(path = "login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            String jwt = authService.signInUser(loginRequest);
            UserResponse userResponse = new UserResponse(authService.getUserByUsername(loginRequest.getEmail()));
            confirmationTokenService.saveConfirmationToken(new ConfirmationToken(jwt, authService.findUserById(userResponse.getId())));
            return ResponseEntity.ok(new LoginResponse(jwt, userResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage())));
        }
    }
}