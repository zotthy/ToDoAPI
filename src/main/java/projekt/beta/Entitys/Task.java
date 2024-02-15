package projekt.beta.Entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.function.Predicate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;

    private LocalDate due_date;

    private String priority;
    private String status;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
