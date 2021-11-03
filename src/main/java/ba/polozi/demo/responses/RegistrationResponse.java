package ba.polozi.demo.responses;

import lombok.*;

@AllArgsConstructor
@Data
public class RegistrationResponse {
    private UserResponse userResponse;
}