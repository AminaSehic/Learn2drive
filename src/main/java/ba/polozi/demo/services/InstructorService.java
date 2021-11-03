package ba.polozi.demo.services;

import ba.polozi.demo.models.Instructor;
import ba.polozi.demo.repositories.InstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> findAll() {
        return (List<Instructor>) instructorRepository.findAll();
    }

    public Instructor saveInstructor(Instructor i) {
        return instructorRepository.save(i);
    }

    public Instructor findById(Long id) {
        try {
            Optional<Instructor> instructor = instructorRepository.findById(id);
            return instructor.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteById(Long id) {
        instructorRepository.deleteById(id);
    }
}