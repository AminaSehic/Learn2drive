package ba.polozi.demo.controllers;

import ba.polozi.demo.models.Candidate;
import ba.polozi.demo.requests.CandidateRequest;
import ba.polozi.demo.responses.CandidateResponse;
import ba.polozi.demo.services.CandidateService;
import ba.polozi.demo.services.InstructorService;
import ba.polozi.demo.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ba.polozi.demo.models.ErrorMessage;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("candidates")
public class CandidateController {
    @Autowired
    public CandidateService candidateService;
    public InstructorService instructorService;
    public QuestionService questionService;

    @GetMapping("")
    public ResponseEntity<?> getClients() {
        List<Candidate> candidates = candidateService.findAll();
        return ResponseEntity.ok().body(candidates);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Candidate candidate = candidateService.findCandidateById(id);
        CandidateResponse candidateResponse = new CandidateResponse(candidate);
        return ResponseEntity.ok().body(candidateResponse);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CandidateRequest candidateRequest) {
        try {
            final Candidate candidate = candidateService.saveCandidate(new Candidate(candidateRequest, instructorService.findById(candidateRequest.getInstructorId())));
            return ResponseEntity.created(URI.create(String.format("/candidates/%s", candidate.getId()))).body(candidate);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Invalid input, try again!")));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            candidateService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Client with id: %s does not exist!", id)));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody CandidateRequest candidateRequest) {
        try {
            Candidate c = candidateService.findCandidateById(id);
            c.setFirstName(candidateRequest.getFirstName());
            c.setLastName(candidateRequest.getLastName());
            c.setEmail(candidateRequest.getEmail());
            c.setPhoneNumber(candidateRequest.getPhoneNumber());
            final Candidate updated = candidateService.saveCandidate(c);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Please enter fields or candidate with id: %s does not exist!", id)));
        }
    }

    @GetMapping("test")
    public ResponseEntity<?> test() {
        CandidateRequest c = new CandidateRequest();
        c.setFirstName("amina");
        c.setLastName("sehic");
        c.setEmail("nekimail");
        c.setPhoneNumber("123456");
        Candidate updated = new Candidate(c, null);
        var k = candidateService.saveCandidate(updated);
        return ResponseEntity.ok().body(k);
    }
}