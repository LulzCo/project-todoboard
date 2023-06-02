package lulzco.todoboard.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/issue")
public class IssueController {

    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Issue issue) {
        issueService.create(issue);
        return ResponseEntity.status(HttpStatus.OK).body("이슈 생성이 완료되었습니다.");
    }

}
