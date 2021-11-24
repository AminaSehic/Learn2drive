package ba.polozi.demo.services;

import ba.polozi.demo.models.Admin;
import ba.polozi.demo.models.Instructor;
import ba.polozi.demo.repositories.AdminRepository;
import ba.polozi.demo.repositories.InstructorRepository;
import ba.polozi.demo.requests.AdminRequest;
import ba.polozi.demo.requests.InstructorRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {
    @Autowired
    private final AdminRepository adminRepository;
    @Autowired
    private final InstructorRepository instructorRepository;

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public Admin saveAdmin(Admin a) {
        return adminRepository.save(a);
    }

    public boolean exist(AdminRequest adminRequest) {
        if (adminRepository.existsByUsername(adminRequest.getUsername())) {
            return true;
        }
        return false;
    }

    public boolean existInstructor(InstructorRequest instructorRequest) {
        if (instructorRepository.existsByUsername(instructorRequest.getUsername())) {
            return true;
        }
        return false;
    }

    public Admin create(AdminRequest adminRequest) {
        Admin admin = new Admin(adminRequest);
        String encodedPassword = passwordEncoder.encode(adminRequest.getPassword());
        admin.setPassword(encodedPassword);
        return adminRepository.save(admin);
    }

    public Instructor createInstructor(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor(instructorRequest);
        String encodedPassword = passwordEncoder.encode(instructorRequest.getPassword());
        instructor.setPassword(encodedPassword);
        return instructorRepository.save(instructor);
    }
}
