package ba.polozi.demo.services;

import ba.polozi.demo.models.Exam;
import ba.polozi.demo.models.Question;
import ba.polozi.demo.models.User;
import ba.polozi.demo.repositories.ExamRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExamService {
    @Autowired
    private final ExamRepository examRepository;

    public List<Exam> findAll() {
        return (List<Exam>) examRepository.findAll();
    }

    public Exam saveExam(Exam e) {
        return examRepository.save(e);
    }

    public Exam findExamById(Long id) {
        Optional<Exam> exam = examRepository.findById(id);
        if (exam.isPresent()) {
            return exam.get();
        } else {
            return null;
        }
    }

    public List<Exam> findExamsByCandidateId(Long id){
        return examRepository.findExamById(id);
    }


    public void deleteById(Long id) {
        examRepository.deleteById(id);
    }
}