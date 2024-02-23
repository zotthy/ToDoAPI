package projekt.beta.Services;

import org.springframework.stereotype.Service;
import projekt.beta.DTOs.MembersLoginDto;
import projekt.beta.DTOs.MembersLoginDtoMapper;
import projekt.beta.Repozytory.MembersRepozytory;

import java.util.Optional;

@Service
public class MembersService {
    private final MembersRepozytory membersRepozytory;

    public MembersService(MembersRepozytory membersRepozytory) {
        this.membersRepozytory = membersRepozytory;
    }

    public Optional<MembersLoginDto> findCredentialsByEmail(String email) {
        return membersRepozytory.findByEmail(email)
                .map(MembersLoginDtoMapper::map);
    }
}
