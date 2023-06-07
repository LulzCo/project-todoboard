package lulzco.todoboard.issue.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Tag tag) {
        tagService.create(tag);
        return ResponseEntity.status(HttpStatus.OK).body("태그 생성 완료");
    }

    @GetMapping("/read/{userId}")
    public ResponseEntity<List<Tag>> getTagByUserId(@PathVariable("userId") String userId) {
        List<Tag> tags = tagService.getTagByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(tags);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Tag tag) {
        tagService.updateTagName(tag.getId(), tag.getTagName());
        return ResponseEntity.status(HttpStatus.OK).body("태그 수정 완료");
    }

    @DeleteMapping("/delete/{tagId}")
    public ResponseEntity<String> delete(@PathVariable("tagId") Long tagId) {
        tagService.delete(tagId);
        return ResponseEntity.status(HttpStatus.OK).body("태그 삭제 완료");
    }
}
