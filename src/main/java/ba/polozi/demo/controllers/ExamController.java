package ba.polozi.demo.controllers;

import ba.polozi.demo.models.ErrorMessage;
import ba.polozi.demo.models.Exam;
//import ba.polozi.demo.models.ExamQuestion;
import ba.polozi.demo.models.Question;
import ba.polozi.demo.requests.ExamRequest;
//import ba.polozi.demo.services.ExamQuestionService;
import ba.polozi.demo.services.ExamService;
import ba.polozi.demo.services.QuestionService;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("exams")
public class ExamController {
    @Autowired
    public ExamService examService;
    public QuestionService questionService;
//    public ExamQuestionService examQuestionService;

    @GetMapping("")
    public ResponseEntity<?> getExams() {
        List<Exam> exams = examService.findAll();
        return ResponseEntity.ok().body(exams);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getExamById(@PathVariable Long id) {
        Exam exam = examService.findExamById(id);
        return ResponseEntity.ok().body(exam);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteExam(@PathVariable Long id) {
        try {
            examService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Exam with id: %s does not exist!", id)));
        }
    }

    public List<Question> generateQuestion() {
        List<Question> listapitanja = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Long indeks = (long) (Math.random() * 20);
            listapitanja.add(questionService.findQuestionById(indeks));
        }
        return listapitanja;
    }

    //post treba da kad se posalje post zahjev uzeti RANDOM iz baze pitanja, 20 teoretski, 10 znakova, 5 raskrsnica
    @PostMapping("")
    public ResponseEntity<?> generateExam(@RequestBody ExamRequest examRequest) {
        try {
            Exam e = new Exam(examRequest);
            e = examService.saveExam(e);
            List<Question> listGeneralQuestion = questionService.generateGeneralQuestions();
            List<Question> listSignQuestion = questionService.generateSignQuestions();
            List<Question> listIntersectionQuestion = questionService.generateIntersectionQuestions();
            ArrayList<Question> questionsForExam = new ArrayList<>();
            questionsForExam.addAll(listGeneralQuestion);
            System.out.println(listGeneralQuestion);
            questionsForExam.addAll(listSignQuestion);
            questionsForExam.addAll(listIntersectionQuestion);
            e.setQuestions(questionsForExam);
            System.out.println("ovo" + e);
            final Exam finalExam = examService.saveExam(e);
            return ResponseEntity.ok(finalExam);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Please enter fields")));

        }
    }

    @GetMapping("test")
    public ResponseEntity<?> test() {
        Exam exam = new Exam();
        exam.setClientName("Amina");
        exam.setScore(100);
        Exam e = examService.saveExam(exam);
        return ResponseEntity.ok().body(e);
    }
}