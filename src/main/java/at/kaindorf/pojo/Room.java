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
        /*@NamedQuery(name = "Room.findByClassName",query = "SELECT r FROM Room r INNER JOIN Classname cn ON r.classname.classId = cn.classId WHERE cn.name = :className"),*/
        @NamedQuery(name = "Room.findByClassName",query = "SELECT r FROM Room r WHERE r.classname.name = :className"),
        @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r ORDER BY r.roomId"),
        @NamedQuery(name = "Room.findByFloor", query = "SELECT r FROM Room r WHERE r.floor = :floor ORDER BY r.roomId"),
        @NamedQuery(name = "Room.countAll", query = "SELECT COUNT(r) FROM Room r")
})
public class Room {
    @Id
    @GeneratedValue
    private int roomId;
    @NonNull
    private String name;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Floor floor;

    @OneToOne(mappedBy = "room")
    @ToString.Exclude
    private Classname classname;
}
