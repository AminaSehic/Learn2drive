package ba.polozi.demo.controllers;

import ba.polozi.demo.models.*;

import ba.polozi.demo.requests.QuestionRequest;
import ba.polozi.demo.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    public QuestionService questionService;

    @GetMapping("")
    public ResponseEntity<?> getQuestions() {
        List<Question> questions = questionService.findAll();
        return ResponseEntity.ok().body(questions);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        Question question = questionService.findQuestionById(id);
        return ResponseEntity.ok().body(question);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        try {
            questionService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Question with id: %s does not exist!", id)));
        }
    }

    @PostMapping("")
    public ResponseEntity<?> generisiKonkretnoPitanje(@RequestBody QuestionRequest questionRequest) {
        try {
            Question q = new Question();
            q.setAnswer1(questionRequest.getAnswer1());
            q.setAnswer2(questionRequest.getAnswer2());
            q.setAnswer3(questionRequest.getAnswer3());
            q.setAnswer4(questionRequest.getAnswer4());
            q.setImg_url(questionRequest.getImg_url());
            q.setIsTrueAnswer(questionRequest.getIsTrueAnswer());
            q.setText(questionRequest.getText());
            q.setType(questionRequest.getType());
            final Question newQuestion = questionService.saveQuestion(q);
            return ResponseEntity.ok(newQuestion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Please enter fields!")));
        }
    }

    @GetMapping("generalna")
    public ResponseEntity<?> getRandomGeneralQuestions() {
        return ResponseEntity.ok(questionService.generateGeneralQuestions());
    }

    @GetMapping("znakovi")
    public ResponseEntity<?> getRandomSignQuestions() {
        return ResponseEntity.ok(questionService.generateSignQuestions());
    }

    @GetMapping("raskrsnice")
    public ResponseEntity<?> getRandomIntersectionQuestions() {
        return ResponseEntity.ok(questionService.generateIntersectionQuestions());
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody QuestionRequest questionRequest) {
        try {
            Question q = questionService.findQuestionById(id);

            q.setText(questionRequest.getText());
            q.setImg_url(questionRequest.getImg_url());
            q.setAnswer1(questionRequest.getAnswer1());
            q.setAnswer2(questionRequest.getAnswer2());
            q.setAnswer3(questionRequest.getAnswer3());
            q.setAnswer4(questionRequest.getAnswer4());
            q.setIsTrueAnswer(questionRequest.getIsTrueAnswer());
            final Question updated = questionService.saveQuestion(q);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Please enter fields or question with id: %s does not exist!", id)));
        }
    }

    @GetMapping("test")
    public ResponseEntity<?> test() {
        Question q = new Question();
        q.setAnswer1("a");
        q.setAnswer2("b");
        q.setAnswer3("c");
        q.setAnswer4("d");
        q.setImg_url("null");
        q.setIsTrueAnswer("b");
        q.setText("Question?");
        q.setType("intersection");
        Question updated = questionService.saveQuestion(q);
        return ResponseEntity.ok().body(updated);
    }
}