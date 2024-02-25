package projekt.beta.Services;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projekt.beta.DTOs.MembersLoginDto;
import projekt.beta.DTOs.MembersLoginDtoMapper;
import projekt.beta.DTOs.MembersRegisterDto;
import projekt.beta.DTOs.MembersRegisterDtoMapper;
import projekt.beta.Entitys.Members;
import projekt.beta.Entitys.UserRole;
import projekt.beta.Exceptions.ExistsException;
import projekt.beta.Repozytory.MembersRepozytory;
import projekt.beta.Repozytory.UserRoleRepozytory;

import java.util.Optional;

@Service
public class MembersService {
    private final MembersRepozytory membersRepozytory;
    private final UserRoleRepozytory userRoleRepozytory;
    private final PasswordEncoder passwordEncoder;

    private final MembersRegisterDtoMapper membersRegisterDtoMapper;
    private final String USER_ROLE = "USER";

    public MembersService(MembersRepozytory membersRepozytory, PasswordEncoder passwordEncoder,
                          MembersRegisterDtoMapper membersRegisterDtoMapper,UserRoleRepozytory userRoleRepozytory) {
        this.membersRepozytory = membersRepozytory;
        this.passwordEncoder = passwordEncoder;
        this.membersRegisterDtoMapper = membersRegisterDtoMapper;
        this.userRoleRepozytory = userRoleRepozytory;
    }

    public Optional<MembersLoginDto> findCredentialsByEmail(String email) {
        return membersRepozytory.findByEmail(email)
                .map(MembersLoginDtoMapper::map);
    }

    @Transactional
    public void register(MembersRegisterDto membersRegisterDto) {
        if (!membersRepozytory.existsByEmail(membersRegisterDto.getEmail())) {
            throw new ExistsException("User with the same email!");
        }
        String encodePassword = passwordEncoder.encode(membersRegisterDto.getPassword());
        Members saveMember = membersRegisterDtoMapper.map(membersRegisterDto);
        saveMember.setPassword(encodePassword);
        UserRole userRole = userRoleRepozytory.findByName(USER_ROLE)
                .orElseThrow(() -> new RuntimeException("Not found"));
        saveMember.getRoles().add(userRole);
        membersRepozytory.save(saveMember);
        System.out.println(encodePassword);
        System.out.println(saveMember);
    }

}
