package ba.polozi.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "`question`")
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String img_url;
    private String isTrueAnswer;
    private String text;
    private String type;
}

