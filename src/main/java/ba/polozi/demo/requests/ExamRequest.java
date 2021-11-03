package ba.polozi.demo.requests;

//import ba.polozi.demo.models.ExamQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRequest {
    String clientName;
    Integer score;
}