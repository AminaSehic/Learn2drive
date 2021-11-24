package ba.polozi.demo.responses;

import ba.polozi.demo.models.Role;
import ba.polozi.demo.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String phoneNumber;
    private List<String> roles;

    public UserResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user
                .getRoles() // pokupi kolekciju objekata tipa Role
                .stream()   // pretvori ih u tok
                .map(role -> { // mapiraj ih tako da jedan element bude string - ime role
                                return role.getName();
                }
        ).collect(Collectors.toList()); //pokupi sve elemente iz toka (to nam daje kolekciju)
        // i tu kolekciju pretvori u listu
    }

}

