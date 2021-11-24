package ba.polozi.demo;
import ba.polozi.demo.controllers.AdminController;
import ba.polozi.demo.controllers.AuthenticationController;
import ba.polozi.demo.requests.AdminRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AdminControllerTest {
    @Autowired
    private AdminController adminController;
    @Test
    public void shouldCreateAdmin(){
        AdminRequest adminRequest = new AdminRequest("Amina", "Sehic", "062453953", "asehic2", "asehic2@etf.unsa.ba", "pasvord");
        ResponseEntity<?> responseEntity = adminController.createAdmin(adminRequest);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }
}
