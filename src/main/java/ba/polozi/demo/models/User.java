package ba.polozi.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Table(name = "`user`")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(min = 2, max = 10, message =
            "Please do not use any accents or special characters. Do not use a nickname.\n" +
                    "Min. 2, max. 10 characters. ")
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(min = 2, max = 10, message =
            "Please do not use any accents or special characters. Do not use a nickname.\n" +
                    "Min. 2, max. 10 characters. ")
    private String lastName;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2, max = 10, message = "Please enter numbers only (numerical digits), without spaces, e.g. 12345678 ")
    @Pattern(regexp = "^[0-9]*$")
    private String phoneNumber;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 7, max = 10, message = "min. 7, max. 10 characters\n" +
            "Both number and letters are obligatory,\n" +
            "We accept upper and lower-case letters, numbers and special characters.")
    private String password;

    @Column(nullable = false)
    @NotBlank
    @Email(regexp = ".*@.*\\..*", message = "Email should be valid, e.g. exaple@gmail.com")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public UserDetails toCurrentUserDetails() {
        return UserPrincipal.create(this);
    }

    public User(String firstName, String lastName, String phoneNumber, String email, String username, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(role);
    }

    public User(String firstName, String lastName, String phoneNumber, String username, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String toString() {
        return id.toString() + firstName.toString() + lastName.toString();
    }
}