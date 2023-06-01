package lulzco.todoboard.issue;

public interface IssueService {
    void create(Issue issue);

    Issue getIssueById(Long id);

    void update(Issue issue);

    void delete(Long id);
}
