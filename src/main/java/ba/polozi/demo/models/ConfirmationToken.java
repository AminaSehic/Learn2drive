package ba.polozi.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Data
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String token;

    @Column(nullable = false)
    @NotBlank
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @NotBlank
    private LocalDateTime expireAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name="app_user_id"
    )
    private User user;

    public ConfirmationToken(String token, User user) {
        this.token = token;
        this.createdAt = LocalDateTime.now();
        this.expireAt = LocalDateTime.now().plusMinutes(15);
        this.user = user;
    }
}
