package at.kaindorf.pojo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "ClassTeacher.findByName",query = "SELECT ct FROM ClassTeacher ct WHERE ct.lastname = :lastname"),
        @NamedQuery(name = "ClassTeacher.findByClassname", query = "SELECT ct FROM ClassTeacher ct INNER JOIN Classname cn ON ct.classname.classId = cn.classId WHERE cn.name = :className"),
        @NamedQuery(name = "ClassTeacher.findByGrade", query = "SELECT ct FROM ClassTeacher ct INNER JOIN Classname cn ON cn.classId = ct.classname.classId WHERE cn.grade = :grade ORDER BY ct.teacherId"),
        @NamedQuery(name = "ClassTeacher.countAll", query = "SELECT COUNT(ct) FROM ClassTeacher ct")
})
public class ClassTeacher {
    @Id
    @GeneratedValue
    private int teacherId;
    @NonNull
    @Column(length = 2)
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
