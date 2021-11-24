package ba.polozi.demo.responses;

import ba.polozi.demo.models.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private InstructorResponse instructor;

    public CandidateResponse(Candidate c) {
        this.id= c.getId();
        this.firstName = c.getFirstName();
        this.lastName = c.getLastName();
        this.email = c.getEmail();
        this.phoneNumber = c.getPhoneNumber();
        this.instructor = new InstructorResponse(c.getInstructor());
    }
}