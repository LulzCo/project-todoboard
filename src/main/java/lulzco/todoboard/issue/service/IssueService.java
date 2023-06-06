package lulzco.todoboard.issue.service;

import lulzco.todoboard.issue.data.entity.Issue;

import java.util.List;

public interface IssueService {
    void create(Issue issue);

    Issue getIssueById(Long id);

    void update(Issue issue);

    void delete(Long id);

    List<Issue> getIssueByUserId(String userId);

    List<Issue> getIssueByTag(String userId, String tag);
}

