package lulzco.todoboard.issue;

import java.util.List;

public interface IssueService {
    void create(Issue issue);

    Issue getIssueById(Long id);

    void update(Issue issue);

    void delete(Long id);

    List<Issue> getIssueByUserId(String userId);
}
