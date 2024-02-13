package projekt.beta.Entitys;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "app_user",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
        @UniqueConstraint(columnNames = "email")
        })
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @NotNull
    @Email
    private String email;
    @Size(min = 8,message = "Minimum password length: 8 characters")
    private String password;

    //Secutiry dodac
    //@ElementCollection(fetch = FetchType.LAZY)
    //List<AppUserRole> appuserrole;
}
