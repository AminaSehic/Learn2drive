package ba.polozi.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "`question`")
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 50)
    private String answer1;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 50)
    private String answer2;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 50)
    private String answer3;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 50)
    private String answer4;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 100)
    private String img_url;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 50)
    private String isTrueAnswer;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 50)
    private String text;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 50)
    private String type;
}

