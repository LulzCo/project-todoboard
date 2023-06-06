package lulzco.todoboard.issue.service;

import lulzco.todoboard.issue.tag.Tag;
import lulzco.todoboard.issue.tag.TagService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TagServiceTest {

    private final TagService tagService;

    @Autowired
    TagServiceTest(TagService tagService) {
        this.tagService = tagService;
    }


    @DisplayName("tag 생성 후 조회")
    @Test
    void create() {
        Tag tag = createTag();

        Tag createdTag = tagService.getTagById(tag.getId());

        assertThat(createdTag).isEqualTo(tag);
    }

    @DisplayName("tag 수정 후 조회")
    @Test
    void update() {
        Tag tag = createTag();

        String tagName = "test Tag Name 2222";
        tagService.updateTagName(tag.getId(), tagName);

        Tag updatedTag = tagService.getTagById(tag.getId());

        assertThat(updatedTag).isEqualTo(updatedTag);
    }

    @DisplayName("tag 삭제 후 조회")
    @Test
    void delete() {
        Tag tag = createTag();

        tagService.delete(tag.getId());

        Tag deletedTag = tagService.getTagById(tag.getId());

        assertThat(deletedTag).isNull();

    }

    private Tag createTag() {
        String userId = "test userId";
        String tagName = "test tag";

        Tag tag = new Tag();
        tag.setUserId(userId);
        tag.setTagName(tagName);

        tagService.create(tag);
        return tag;
    }
}