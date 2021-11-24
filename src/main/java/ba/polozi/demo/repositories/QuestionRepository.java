package ba.polozi.demo.repositories;

import ba.polozi.demo.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM question q WHERE q.type = 'general' ORDER BY random() LIMIT 10", nativeQuery = true)
    List<Question> randomGeneralQuestions();

    @Query(value = "SELECT * FROM question q WHERE q.type = 'sing' ORDER BY random() LIMIT 5", nativeQuery = true)
    List<Question> randomSingQuestions();

    @Query(value = "SELECT * FROM question q WHERE q.type = 'intersection' ORDER BY random() LIMIT 2", nativeQuery = true)
    List<Question> randomIntersectionQuestions();
}