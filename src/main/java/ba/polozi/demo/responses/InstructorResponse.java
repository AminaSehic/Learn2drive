package ba.polozi.demo.responses;

import ba.polozi.demo.models.Instructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String phoneNumber;
    private String role;

    public InstructorResponse(Instructor i) {
        this.id = i.getId();
        this.firstName = i.getFirstName();
        this.lastName = i.getLastName();
        this.phoneNumber = i.getPhoneNumber();
        this.username = i.getUsername();
        this.email = i.getEmail();
        this.role = "ROLE_INSTRUCTOR";
    }
}