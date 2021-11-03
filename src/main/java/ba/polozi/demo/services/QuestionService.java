package ba.polozi.demo.services;

import ba.polozi.demo.models.Question;
import ba.polozi.demo.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService {
    @Autowired
    private final QuestionRepository questionRepository;

    public List<Question> findAll() {
        return new ArrayList<Question>((Collection<? extends Question>) questionRepository.findAll());
    }

    public Question saveQuestion(Question q) {
        return questionRepository.save(q);
    }

    public Question findQuestionById(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            return null;
        }
    }

    public List<Question> generateGeneralQuestions() {
        return questionRepository.randomGeneralQuestions();
    }
    public List<Question> generateSignQuestions() {
        return questionRepository.randomSingQuestions();
    }
    public List<Question> generateIntersectionQuestions() {
        return questionRepository.randomIntersectionQuestions();
    }
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }
}