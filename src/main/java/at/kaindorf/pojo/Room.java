package at.kaindorf.pojo;

import at.kaindorf.bl.Floor;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
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
