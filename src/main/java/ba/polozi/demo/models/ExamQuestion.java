package ba.polozi.demo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "`exam_question`")
@NoArgsConstructor
@AllArgsConstructor
public class ExamQuestion implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Id
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 1, max = 100)
    private String answer;
}