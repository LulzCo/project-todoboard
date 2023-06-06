package lulzco.todoboard.issue.tag;

public interface TagService {
    void create(Tag tag);

    Tag getTagById(Long id);

    void updateTagName(Long id, String tagName);

    void delete(Long id);
}
