package projekt.beta.Controlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cont {
    @GetMapping("/a")
    String get(){
        return "hello";
    }
}
