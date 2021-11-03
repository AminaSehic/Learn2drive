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
    private final AuthService appUserService;
    private final ConfirmationTokenService confirmationTokenService;

    @GetMapping("/amina")
    public String hello() {
        return "hello world";
    }

    @PostMapping(path = "registration")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        if (appUserService.exist(request)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "postoji vec taj user")));
        }
        User result = appUserService.createUserAccount(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/**")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(
                new RegistrationResponse(
                        new UserResponse(
                                result.getId(),
                                result.getFirstName(),
                                result.getLastName(),
                                result.getPhoneNumber(),
                                result.getUsername(),
                                result.getEmail()
                        )
                )
        );
    }
    @PostMapping(path = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        String jwt = appUserService.signInUser(loginRequest);
        try {
            UserResponse userResponse = new UserResponse(appUserService.getUserByUsername(loginRequest.getEmail()));
            confirmationTokenService.saveConfirmationToken(new ConfirmationToken(jwt, appUserService.findUserById(userResponse.getId())));
            return ResponseEntity.ok(new LoginResponse(jwt, userResponse));
        } catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage())));
        }
    }

}


//    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
//
//        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
//        final String token = jwtUtil.generateToken(userDetails);
//        return ResponseEntity.ok(new JwtResponse(token));
//    }
//
//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }
//}