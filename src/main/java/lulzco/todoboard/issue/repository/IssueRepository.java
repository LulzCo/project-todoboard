package lulzco.todoboard.issue.repository;

import lulzco.todoboard.issue.data.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByUserId(String userId);
}
