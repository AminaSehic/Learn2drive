package ba.polozi.demo.models;

import ba.polozi.demo.requests.ExamRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "`exam`")
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "candidate_id"
    )
    private Candidate candidate;

    @NotBlank
    @Column(nullable = false)
    private Integer score;

    @ManyToMany(targetEntity = Question.class)
    @JoinTable(name = "exam_question", joinColumns = @JoinColumn(name = "exam_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions = new ArrayList<>();

    public Exam(ExamRequest examRequest) {
        setScore(examRequest.getScore());
    }

}
