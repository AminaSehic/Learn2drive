package ba.polozi.demo.controllers;

import ba.polozi.demo.models.Admin;
import ba.polozi.demo.models.ErrorMessage;
import ba.polozi.demo.models.Instructor;
import ba.polozi.demo.requests.AdminRequest;
import ba.polozi.demo.requests.InstructorRequest;
import ba.polozi.demo.responses.AdminResponse;
import ba.polozi.demo.responses.InstructorResponse;
import ba.polozi.demo.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping(path = "createAdmin")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody AdminRequest adminRequest) {
        if (adminService.exist(adminRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Admin already exists! Please try again.")));
        }
        Admin result = adminService.create(adminRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/**")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(
                new AdminResponse(
                        result.getId(),
                        result.getFirstName(),
                        result.getLastName(),
                        result.getPhoneNumber(),
                        result.getUsername(),
                        result.getEmail(),
                        "ROLE_ADMIN"
                )
        );
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "createInstructor")
    public ResponseEntity<?> createInstructor(@Valid @RequestBody InstructorRequest instructorRequest) {
        if (adminService.existInstructor(instructorRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Instructor already exists! Please try again.")));
        }
        Instructor result = adminService.createInstructor(instructorRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/**")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(
                new InstructorResponse(
                        result.getId(),
                        result.getFirstName(),
                        result.getLastName(),
                        result.getPhoneNumber(),
                        result.getUsername(),
                        result.getEmail(),
                        "ROLE_INSTRUCTOR"
                )
        );
    }
}