package ba.polozi.demo.requests;
import lombok.*;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class LoginRequest {

    private String email;
    private String password;
}
