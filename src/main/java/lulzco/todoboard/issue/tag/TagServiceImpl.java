package lulzco.todoboard.issue.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    @Override
    public void create(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public Tag getTagById(Long id) {
        Optional<Tag> tag = tagRepository.findById(id);
        Tag result = null;
        if (tag.isPresent()) {
            result = tag.get();
        }
        return result;
    }

    @Override
    public void updateTagName(Long id, String tagName) {
        Tag tag = getTagById(id);
        tag.setTagName(tagName);
        tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> getTagByUserId(String userId) {
        return tagRepository.findByUserId(userId);
    }
}
