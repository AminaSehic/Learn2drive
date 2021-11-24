package ba.polozi.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateRequest {

    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    Long instructorId;
}