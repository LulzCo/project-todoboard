package lulzco.todoboard.issue.service;

import lulzco.todoboard.issue.data.entity.Issue;
import lulzco.todoboard.issue.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;

    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public void create(Issue issue) {
        issueRepository.save(issue);
    }

    @Override
    public Issue getIssueById(Long id) {
        Issue result = null;
        Optional<Issue> issue = issueRepository.findById(id);
        if (issue.isPresent()) {
            result = issue.get();
        }
        return result;
    }

    @Override
    public void update(Issue issue) {
        issueRepository.save(issue);
    }

    @Override
    public void delete(Long id) {
        issueRepository.deleteById(id);
    }

    @Override
    public List<Issue> getIssueByUserId(String userId) {
        return issueRepository.findByUserId(userId);
    }

    @Override
    public List<Issue> getIssueByTagName(String userId, String tag) {
        return issueRepository.findByTag(userId, tag);
    }
}
