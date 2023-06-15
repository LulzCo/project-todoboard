package lulzco.todoboard.issue.tag;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String userId;

    @Column()
    @NonNull
    private String tagName;

    public Tag() {

    }
}
