package ba.polozi.demo.controllers;

import ba.polozi.demo.models.ErrorMessage;
import ba.polozi.demo.models.Instructor;
import ba.polozi.demo.requests.InstructorRequest;
import ba.polozi.demo.services.InstructorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("instructors")
public class InstructorController {
    @Autowired
    public InstructorService instructorService;

    @GetMapping("")
    public ResponseEntity<?> getInstructors() {
        List<Instructor> instructors = instructorService.findAll();
        return ResponseEntity.ok().body(instructors);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getInstructorById(@PathVariable Long id) {
        Instructor instructor = instructorService.findById(id);
        return ResponseEntity.ok().body(instructor);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody InstructorRequest instructorRequest) {
        try {
            final Instructor instructor = instructorService.saveInstructor(new Instructor(instructorRequest));
            return ResponseEntity.created(URI.create(String.format("/instructors/%s", instructor.getId()))).body(instructor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Invalid input, try again!")));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable Long id) {
        try {
            instructorService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Instructor with id: %s does not exist!", id)));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateInstructor(@PathVariable Long id, @RequestBody InstructorRequest instructorRequest) {
        try {
            Instructor i = instructorService.findById(id);
            i.setFirstName(instructorRequest.getFirstName());
            i.setLastName(instructorRequest.getLastName());
            i.setEmail(instructorRequest.getEmail());
            i.setPhoneNumber(instructorRequest.getPhoneNumber());
            i.setCar(instructorRequest.getCar());
            final Instructor updated = instructorService.saveInstructor(i);
            return ResponseEntity.ok(updated);
        } catch (Exception c) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Please enter fields or instructor with id: %s does not exist!", id)));
        }
    }

    @GetMapping("test")
    public ResponseEntity<?> test() {
        Instructor i = new Instructor();
        i.setFirstName("amina");
        i.setLastName("sehic");
        i.setEmail("nekimail");
        i.setPhoneNumber("123456");
        i.setCar("golf");
        var k = instructorService.saveInstructor(i);
        return ResponseEntity.ok().body(k);
    }
}