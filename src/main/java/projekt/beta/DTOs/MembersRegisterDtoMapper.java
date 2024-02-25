package projekt.beta.DTOs;

import org.springframework.stereotype.Service;
import projekt.beta.Entitys.Members;

@Service
public class MembersRegisterDtoMapper {
    public static Members map(MembersRegisterDto membersRegisterDto){
        Members members = new Members();
        members.setId(membersRegisterDto.getId());
        members.setName(membersRegisterDto.getName());
        members.setSurname(membersRegisterDto.getSurname());
        members.setEmail(membersRegisterDto.getEmail());
        members.setPassword(membersRegisterDto.getPassword());
        members.setAddress(membersRegisterDto.getAddress());
        return members;
    }
}
