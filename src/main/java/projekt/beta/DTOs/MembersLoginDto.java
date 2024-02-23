package projekt.beta.DTOs;

import java.util.Set;

public record MembersLoginDto(String email, String password, Set<String> roles) {
}
