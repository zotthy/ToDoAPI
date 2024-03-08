package projekt.beta.DTOs;

import org.springframework.stereotype.Service;
import projekt.beta.Entitys.Members;

@Service
public class MembersDTOMapper {
    public MembersDTO mapToDto(Members members){
        MembersDTO membersDTO = new MembersDTO();
        membersDTO.setId(members.getId());
        membersDTO.setName(members.getName());
        membersDTO.setSurname(members.getSurname());
        membersDTO.setEmail(members.getEmail());
        membersDTO.setAddress(members.getAddress());
        return membersDTO;
    }
}
