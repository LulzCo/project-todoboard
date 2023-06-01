package lulzco.todoboard.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
