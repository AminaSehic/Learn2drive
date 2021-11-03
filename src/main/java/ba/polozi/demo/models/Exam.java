package ba.polozi.demo.models;

import ba.polozi.demo.requests.ExamRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String clientName;
    private Integer score;
    @ManyToMany(targetEntity = Question.class)
    @JoinTable(name = "exam_question", joinColumns = @JoinColumn(name = "exam_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions = new ArrayList<>();

    public Exam(ExamRequest examRequest) {
        setClientName(examRequest.getClientName());
        setScore(examRequest.getScore());
    }

}
