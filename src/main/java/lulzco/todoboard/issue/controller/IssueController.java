package lulzco.todoboard.issue.controller;

import lulzco.todoboard.issue.data.entity.Issue;
import lulzco.todoboard.issue.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue")
public class IssueController {

    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    // 이슈 생성하기
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Issue issue) {
        issueService.create(issue);
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
}
