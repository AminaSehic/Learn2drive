package ba.polozi.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Email(regexp = ".*@.*\\..*", message = "Email should be valid, e.g. exaple@gmail.com")
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 7, max = 10, message = "min. 7, max. 10 characters\n" +
            "Both number and letters are obligatory,\n" +
            "We accept upper and lower-case letters, numbers and special characters.")
    private String password;
    private GrantedAuthority authority;
    private Collection<Role> roles;

    @Override
    public String getUsername() {
        return email;
    }

    public static UserDetails create(User entity){
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        return new UserPrincipal(entity.getId(), entity.getEmail(), entity.getPassword(), authority,  entity.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorites = new ArrayList<>();
        authorites.add(authority);
        return authorites;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
