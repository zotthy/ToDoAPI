package projekt.beta.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projekt.beta.DTOs.*;
import projekt.beta.Entitys.Address;
import projekt.beta.Entitys.Members;
import projekt.beta.Entitys.UserRole;
import projekt.beta.Exceptions.ExistsException;
import projekt.beta.Repozytory.MembersRepozytory;
import projekt.beta.Repozytory.UserRoleRepozytory;
import projekt.beta.Security.JwtService;

import java.util.Optional;

@Service
public class MembersService {
    private final MembersRepozytory membersRepozytory;
    private final UserRoleRepozytory userRoleRepozytory;
    private final PasswordEncoder passwordEncoder;
    private final MembersDTOMapper membersDTOMapper;

    private final JwtService jwtService;
    private final String USER_ROLE = "USER";

    public MembersService(MembersRepozytory membersRepozytory, PasswordEncoder passwordEncoder,
                          UserRoleRepozytory userRoleRepozytory, MembersDTOMapper membersDTOMapper,
                          JwtService jwtService) {
        this.membersRepozytory = membersRepozytory;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepozytory = userRoleRepozytory;
        this.membersDTOMapper = membersDTOMapper;
        this.jwtService = jwtService;
    }

    public Optional<MembersLoginDto> findCredentialsByEmail(String email) {
        return membersRepozytory.findByEmail(email)
                .map(MembersLoginDtoMapper::map);
    }

    @Transactional
    public void register(MembersRegisterDto membersRegisterDto) {
        if (membersRepozytory.existsByEmail(membersRegisterDto.getEmail())) {
            throw new ExistsException("User with the same email!");
        }
        String encodePassword = passwordEncoder.encode(membersRegisterDto.getPassword());
        Members saveMember = MembersRegisterDtoMapper.map(membersRegisterDto);
        saveMember.setPassword(encodePassword);
        UserRole userRole = userRoleRepozytory.findByName(USER_ROLE)
                .orElseThrow(() -> new RuntimeException("Not found"));
        saveMember.getRoles().add(userRole);
        membersRepozytory.save(saveMember);
    }

    public Long getIdByEmail(String email) {
        return membersRepozytory.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Not found with email")).getId();
    }

    public Optional<Members> getMemberByEmail(String email) {
        return membersRepozytory.findByEmail(email);
    }

    public Optional<MembersDTO> getUserInfo(String token) {
        String email = getIdFromToken(token);
        return membersRepozytory.findByEmail(email).map(membersDTOMapper::mapToDto);
    }

    private String getIdFromToken(String token){
        String actualToken = token.replace("Bearer ", "");
        String email = jwtService.getEmailFromToken(actualToken);
        System.out.println(email);
        return email;
    }

}
