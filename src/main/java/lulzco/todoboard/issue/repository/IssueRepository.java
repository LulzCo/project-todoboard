package lulzco.todoboard.issue.repository;

import lulzco.todoboard.issue.data.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByUserId(String userId);

    @Query("SELECT i FROM Issue i WHERE i.userId = :userId AND i.tag = :tag")
    List<Issue> findByTag(@Param("userId") String userId, @Param("tag") String tag);
}
