package projekt.beta.Repozytory;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.beta.Entitys.Address;

public interface AddressRepozytory extends JpaRepository<Address,Long> {
}
