package ba.polozi.demo.responses;

import ba.polozi.demo.models.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String phoneNumber;
    private String role;

    public AdminResponse(Admin admin) {
        this.id = admin.getId();
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.phoneNumber = admin.getPhoneNumber();
        this.username = admin.getUsername();
        this.email = admin.getEmail();
        this.role = "ROLE_ADMIN";
    }
}
