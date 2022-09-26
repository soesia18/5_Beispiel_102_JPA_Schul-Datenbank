package at.kaindorf.pojo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class ClassTeacher {
    @Id
    @GeneratedValue
    private int teacherId;
    @NonNull
    private String initials;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @NonNull
    private String title;

    @OneToOne(mappedBy = "classTeacher")
    @ToString.Exclude
    private Classname classname;

}
