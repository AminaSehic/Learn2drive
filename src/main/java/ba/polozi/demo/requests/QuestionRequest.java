package ba.polozi.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {
    String text;
    String img_url;
    String questionCategory;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String isTrueAnswer;
    String type;
}
