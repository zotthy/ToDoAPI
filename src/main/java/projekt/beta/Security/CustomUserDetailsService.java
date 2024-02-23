package projekt.beta.Security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projekt.beta.DTOs.MembersLoginDto;
import projekt.beta.Services.MembersService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MembersService membersService;

    public CustomUserDetailsService(MembersService membersService) {
        this.membersService = membersService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return membersService.findCredentialsByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", username)));
    }

    private UserDetails createUserDetails(MembersLoginDto credentials) {
        return User.builder()
                .username(credentials.email())
                .password(credentials.password())
                .roles(credentials.roles().toArray(String[]::new))
                .build();
    }
}

