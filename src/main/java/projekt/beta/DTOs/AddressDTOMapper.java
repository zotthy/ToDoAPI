package projekt.beta.DTOs;

import org.springframework.stereotype.Service;
import projekt.beta.Entitys.Address;

@Service
public class AddressDTOMapper {
    public AddressDTO mapToDto(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setZip_code(address.getZip_code());
        return dto;
    }
}
