package at.kaindorf.pojo;

import at.kaindorf.bl.Floor;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Classname.findByClassName",query = "SELECT cn FROM Classname cn WHERE cn.name = :className"),
        @NamedQuery(name = "Classname.findAll", query = "SELECT cn FROM Classname cn ORDER BY cn.classId"),
        @NamedQuery(name = "Classname.findByFloor", query = "SELECT cn FROM Classname cn INNER JOIN Room r ON cn.room.roomId = r.roomId WHERE r.floor = :floor ORDER BY cn.classId"),
        @NamedQuery(name = "Classname.countAll", query = "SELECT COUNT(cn) FROM Classname cn")
})
public class Classname {
    @Id
    @GeneratedValue
    private int classId;
    @NonNull
    private String name;
    @NonNull
    private int grade;
    @NonNull
    private int size;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "roomid")
    private Room room;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacherId")
    private ClassTeacher classTeacher;

    public Classname (String line) {
        String[] lineData = line.split(";");

        this.name = lineData[4];
        this.grade = Integer.parseInt(lineData[4].charAt(0) + "");
        this.size = Integer.parseInt(lineData[5]);

        this.room = new Room(lineData[6], Integer.parseInt(lineData[6].split("\\.")[0]) == 1 ? Floor.GROUND : Floor.FIRST);
        this.classTeacher = new ClassTeacher(lineData[0], lineData[3], lineData[2], lineData[1]);

        this.room.setClassname(this);
        this.classTeacher.setClassname(this);
    }
}
