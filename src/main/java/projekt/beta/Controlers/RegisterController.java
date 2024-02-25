package projekt.beta.Controlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projekt.beta.DTOs.MembersRegisterDto;
import projekt.beta.Exceptions.ExistsException;
import projekt.beta.Services.MembersService;

@RestController
@RequestMapping("/auth")
public class RegisterController {
    private final MembersService membersService;

    public RegisterController(MembersService membersService) {
        this.membersService = membersService;
    }

    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody MembersRegisterDto membersRegisterDto) {
        try {
            membersService.register(membersRegisterDto);
            return new ResponseEntity<>("register is sucessfull", HttpStatus.OK);
        } catch (ExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
