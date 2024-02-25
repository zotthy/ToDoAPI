package projekt.beta.DTOs;

import org.springframework.stereotype.Service;
import projekt.beta.Entitys.Members;
import projekt.beta.Entitys.UserRole;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MembersLoginDtoMapper{
    public static MembersLoginDto map(Members user){
        String email = user.getEmail();
        String password = user.getPassword();
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new MembersLoginDto(email, password, roles);
    }
}
