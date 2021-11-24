package ba.polozi.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest {
    @NotBlank
    @Column(nullable = false)
    @Size(min = 2, max = 10, message =
            "Please do not use any accents or special characters. Do not use a nickname.\n" +
                    "Min. 2, max. 10 characters. ")
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2, max = 10, message =
            "Please do not use any accents or special characters. Do not use a nickname.\n" +
                    "Min. 2, max. 10 characters. ")
    private String lastName;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2, max = 10, message = "Please enter numbers only (numerical digits), without spaces, e.g. 12345678 ")
    private String phoneNumber;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;

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
}