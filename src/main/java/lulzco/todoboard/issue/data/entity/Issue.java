package lulzco.todoboard.issue.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lulzco.todoboard.issue.data.DueType;
import lulzco.todoboard.issue.data.IssueStatus;

import java.time.LocalDateTime;

@Data
@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String userId;

    @Column
    private String title;

    @Column
    private String tagName;

    @Column
    @NonNull
    private IssueStatus status;

    @Column
    private String contents;

    @Column
    @NonNull
    private LocalDateTime createdAt;

    @Column
    @NonNull
    private LocalDateTime updatedAt;

    @Column
    @NonNull
    private DueType dueType;

    @Column
    private LocalDateTime dueDate;

    public Issue() {

    }
}
