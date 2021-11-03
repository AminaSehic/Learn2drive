package ba.polozi.demo.requests;

import lombok.*;

@Data
@AllArgsConstructor
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String email;
    private String password;
}
