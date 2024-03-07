package projekt.beta.Controlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import projekt.beta.DTOs.MembersDTO;
import projekt.beta.Services.MembersService;

@RestController
public class ProfileController {
    private final MembersService membersService;

    public ProfileController(MembersService membersService) {
        this.membersService = membersService;
    }

    @GetMapping("/profile")
    public ResponseEntity<MembersDTO> getInfo(@RequestHeader("Authorization") String token){
        return membersService.getUserInfo(token).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
