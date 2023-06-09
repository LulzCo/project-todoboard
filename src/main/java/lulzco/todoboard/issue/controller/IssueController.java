package lulzco.todoboard.issue.controller;

import lulzco.todoboard.issue.data.dto.CreateIssueDto;
import lulzco.todoboard.issue.data.entity.Issue;
import lulzco.todoboard.issue.service.IssueService;
import lulzco.todoboard.issue.tag.Tag;
import lulzco.todoboard.issue.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue")
public class IssueController {

    private final IssueService issueService;
    private final TagService tagService;

    @Autowired
    public IssueController(IssueService issueService, TagService tagService) {
        this.issueService = issueService;
        this.tagService = tagService;
    }

    // 이슈 생성하기
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateIssueDto createIssueDto) {
        issueService.create(createIssueDto);
        return ResponseEntity.status(HttpStatus.OK).body("이슈 생성 완료");
    }

    // 사용자 아이디로 모든 이슈 조회하기
    @GetMapping("/read/{userId}")
    public ResponseEntity<List<Issue>> findIssueByUserId(@PathVariable("userId") String userId) {
        List<Issue> found = issueService.getIssueByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(found);
    }

    // 이슈 수정하기
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Issue issue) {
        issueService.update(issue);
        return ResponseEntity.status(HttpStatus.OK).body("이슈 수정 완료");
    }

    // 이슈 삭제하기
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        issueService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("이슈 삭제 완료");
    }

    // userId와 tagId로 이슈 조회하기
    @GetMapping("/read/{userId}/{tagId}")
    public ResponseEntity<List<Issue>> findIssueByUserIdAndTag(@PathVariable("userId") String userId, @PathVariable("tagId") Long tagId) {
        Tag tag = tagService.getTagById(tagId);
        List<Issue> found = issueService.getIssueByTagName(userId, tag.getTagName());
        return ResponseEntity.status(HttpStatus.OK).body(found);
    }
}
