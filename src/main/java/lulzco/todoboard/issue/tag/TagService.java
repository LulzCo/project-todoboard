package lulzco.todoboard.issue.tag;

import java.util.List;

public interface TagService {
    void create(Tag tag);

    Tag getTagById(Long id);

    void updateTagName(Long id, String tagName);

    void delete(Long id);

    List<Tag> getTagByUserId(String userId);
}
