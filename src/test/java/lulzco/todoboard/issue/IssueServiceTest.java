package lulzco.todoboard.issue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IssueServiceTest {

    private final IssueService issueService;

    @Autowired
    IssueServiceTest(IssueService issueService) {
        this.issueService = issueService;
    }

    @DisplayName("생성 후 조회 테스트")
    @Test
    void create() {
        String userId = "test user";
        String title = "test title";
        String tag = "test tag";
        String contents = "test contents";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        DueType dueType = DueType.DEADLINE;
        LocalDateTime dueDate = LocalDateTime.of(2024, 12, 25, 23, 59);

        Issue issue = new Issue();
        issue.setUserId(userId);
        issue.setTitle(title);
        issue.setTag(tag);
        issue.setContents(contents);
        issue.setCreatedAt(createdAt);
        issue.setUpdatedAt(updatedAt);
        issue.setDueType(dueType);
        issue.setDueDate(dueDate);
        issueService.create(issue);

        Issue createdIssue = issueService.getIssueById(issue.getId());

        assertThat(createdIssue).isEqualTo(issue);
    }

    @DisplayName("수정 후 수정 테스트")
    @Test
    void update() {
        String title = "test title";
        String tag = "test tag";
        String contents = "test contents";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        DueType dueType = DueType.DEADLINE;
        LocalDateTime dueDate = LocalDateTime.of(2024, 12, 25, 23, 59);

        Issue issue = new Issue();
        issue.setTitle(title);
        issue.setTag(tag);
        issue.setContents(contents);
        issue.setCreatedAt(createdAt);
        issue.setUpdatedAt(updatedAt);
        issue.setDueType(dueType);
        issue.setDueDate(dueDate);
        issueService.create(issue);

        String title2 = "test title2";
        String tag2 = "test tag2";
        String contents2 = "test contents2";
        LocalDateTime updatedAt2 = LocalDateTime.now();
        DueType dueType2 = DueType.SCHECDULE;
        LocalDateTime dueDate2 = LocalDateTime.of(2025, 12, 25, 23, 59);

        issue.setTitle(title2);
        issue.setTag(tag2);
        issue.setContents(contents2);
        issue.setUpdatedAt(updatedAt2);
        issue.setDueType(dueType2);
        issue.setDueDate(dueDate2);

        issueService.update(issue);
        Issue updatedIssue = issueService.getIssueById(issue.getId());

        assertThat(updatedIssue).isEqualTo(issue);

    }

    @DisplayName("삭제 후 조회 테스트")
    @Test
    void delete() {
        String title = "test title";
        String tag = "test tag";
        String contents = "test contents";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        DueType dueType = DueType.DEADLINE;
        LocalDateTime dueDate = LocalDateTime.of(2024, 12, 25, 23, 59);

        Issue issue = new Issue();
        issue.setTitle(title);
        issue.setTag(tag);
        issue.setContents(contents);
        issue.setCreatedAt(createdAt);
        issue.setUpdatedAt(updatedAt);
        issue.setDueType(dueType);
        issue.setDueDate(dueDate);
        issueService.create(issue);

        issueService.delete(issue.getId());
        Issue deletedIssue = issueService.getIssueById(issue.getId());

        assertThat(deletedIssue).isNull();

    }
}