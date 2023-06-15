package lulzco.todoboard.issue.service;

import lulzco.todoboard.issue.data.dto.CreateIssueDto;
import lulzco.todoboard.issue.data.entity.Issue;
import lulzco.todoboard.issue.repository.IssueRepository;
import lulzco.todoboard.issue.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final TagService tagService;

    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository, TagService tagService) {
        this.issueRepository = issueRepository;
        this.tagService = tagService;
    }

    @Override
    public Issue create(CreateIssueDto createIssueDto) {
        Issue issue = new Issue();
        issue.setUserId(createIssueDto.getUserId());
        issue.setTitle(createIssueDto.getTitle());
        issue.setTag(tagService.getTagById(createIssueDto.getTagId()));
        issue.setTagName(tagService.getTagById(createIssueDto.getTagId()).getTagName());
        issue.setStatus(createIssueDto.getStatus());
        issue.setContents(createIssueDto.getContents());
        issue.setCreatedAt(LocalDateTime.now());
        issue.setUpdatedAt(LocalDateTime.now());
        issue.setDueType(createIssueDto.getDueType());
        issue.setDueDate(createIssueDto.getDueDate());

        return issueRepository.save(issue);
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
