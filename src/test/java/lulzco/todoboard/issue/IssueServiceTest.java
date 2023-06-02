package lulzco.todoboard.issue;

import lulzco.todoboard.issue.data.DueType;
import lulzco.todoboard.issue.data.IssueStatus;
import lulzco.todoboard.issue.data.entity.Issue;
import lulzco.todoboard.issue.service.IssueService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

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
        Issue issue = createIssue();

        Issue createdIssue = issueService.getIssueById(issue.getId());

        assertThat(createdIssue).isEqualTo(issue);
    }

    @DisplayName("수정 후 수정 테스트")
    @Test
    void update() {
        Issue issue = createIssue();

        String userId2 = "test user2";
        String title2 = "test title2";
        String tag2 = "test tag2";
        String contents2 = "test contents2";
        LocalDateTime updatedAt2 = LocalDateTime.now();
        DueType dueType2 = DueType.SCHECDULE;
        LocalDateTime dueDate2 = LocalDateTime.of(2025, 12, 25, 23, 59);

        issue.setUserId(userId2);
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
        Issue issue = createIssue();

        issueService.delete(issue.getId());
        Issue deletedIssue = issueService.getIssueById(issue.getId());

        assertThat(deletedIssue).isNull();

    }

    @DisplayName("사용자 아이디로 이슈 조회하기")
    @Test
    void getIssueByUserId() {
        Issue issue = createIssue();

        List<Issue> founds = issueService.getIssueByUserId(issue.getUserId());

        assertThat(founds.get(founds.size() - 1)).isEqualTo(issue);

    }

    private Issue createIssue() {
        String userId = "test user";
        String title = "test title";
        String tag = "test tag";
        IssueStatus status = IssueStatus.TODO;
        String contents = "test contents";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        DueType dueType = DueType.DEADLINE;
        LocalDateTime dueDate = LocalDateTime.of(2024, 12, 25, 23, 59);

        Issue issue = new Issue();
        issue.setUserId(userId);
        issue.setTitle(title);
        issue.setTag(tag);
        issue.setStatus(status);
        issue.setContents(contents);
        issue.setCreatedAt(createdAt);
        issue.setUpdatedAt(updatedAt);
        issue.setDueType(dueType);
        issue.setDueDate(dueDate);
        issueService.create(issue);
        return issue;
    }
}