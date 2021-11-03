package ba.polozi.demo.controllers;

import ba.polozi.demo.models.Client;
import ba.polozi.demo.models.Exam;
import ba.polozi.demo.models.Question;
import ba.polozi.demo.requests.ClientRequest;
import ba.polozi.demo.responses.ClientResponse;
import ba.polozi.demo.services.ClientService;
import ba.polozi.demo.services.ExamService;
import ba.polozi.demo.services.InstructorService;
import ba.polozi.demo.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ba.polozi.demo.models.ErrorMessage;

import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("clients")
public class ClientController {
    @Autowired
    public ClientService clientService;
    public InstructorService instructorService;
    public ExamService examService;
    public QuestionService questionService;

    @GetMapping("")
    public ResponseEntity<?> getClients() {
        List<Client> clients = clientService.findAll();
        return ResponseEntity.ok().body(clients);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Client client = clientService.findClientById(id);
        ClientResponse clientResponse = new ClientResponse(client);
        return ResponseEntity.ok().body(clientResponse);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ClientRequest clientRequest) {
        try {
            final Client client = clientService.saveClient(new Client(clientRequest, instructorService.findById(clientRequest.getInstructorId())));
            return ResponseEntity.created(URI.create(String.format("/clients/%s", client.getId()))).body(client);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Invalid input, try again!")));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Client with id: %s does not exist!", id)));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientRequest clientRequest) {
        try {
            Client c = clientService.findClientById(id);
            c.setFirstName(clientRequest.getFirstName());
            c.setLastName(clientRequest.getLastName());
            c.setEmail(clientRequest.getEmail());
            c.setPhoneNumber(clientRequest.getPhoneNumber());
            final Client updated = clientService.saveClient(c);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Please enter fields or client with id: %s does not exist!", id)));
        }
    }

    @GetMapping("test")
    public ResponseEntity<?> test() {
        ClientRequest c = new ClientRequest();
        c.setFirstName("amina");
        c.setLastName("sehic");
        c.setEmail("nekimail");
        c.setPhoneNumber("123456");
        Client updated = new Client(c, null);
        var k = clientService.saveClient(updated);
        return ResponseEntity.ok().body(k);
    }

    @GetMapping("proba")
    public ResponseEntity<?> proba() {
        Exam e = new Exam();
        examService.saveExam(e);
        Question q = questionService.findQuestionById(1L);
        e.getQuestions().add(q);
        examService.saveExam(e);
        return ResponseEntity.ok(e);
    }
}