package lulzco.todoboard.issue.service;

import lulzco.todoboard.issue.data.dto.CreateIssueDto;
import lulzco.todoboard.issue.data.entity.Issue;

import java.util.List;

public interface IssueService {
    Issue create(CreateIssueDto createIssueDto);

    Issue getIssueById(Long id);

    void update(Issue issue);

    void delete(Long id);

    List<Issue> getIssueByUserId(String userId);

    List<Issue> getIssueByTagName(String userId, String tag);
}

