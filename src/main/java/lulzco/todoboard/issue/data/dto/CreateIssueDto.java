package lulzco.todoboard.issue.data.dto;

import lombok.Data;
import lulzco.todoboard.issue.data.DueType;
import lulzco.todoboard.issue.data.IssueStatus;

import java.time.LocalDateTime;

@Data
public class CreateIssueDto {

    private String userId;

    private String title;

    private Long tagId;

    private IssueStatus status;

    private String contents;

    private DueType dueType;

    private LocalDateTime dueDate;

}
