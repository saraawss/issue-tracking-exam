package au.com.domain.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import au.com.domain.demo.entity.Issue;

public interface IssueRepository extends CrudRepository<Issue,Long>{

	@Query("SELECT s FROM Issue s WHERE s.reporter = :reporter AND s.assignee = :assignee AND s.created > :fromdate AND s.created < :enddate")
    List<Issue> findByRepoterAndAssigneeAndCreated(@Param("reporter") long repoter, @Param("assignee") long assignee, @Param("fromdate") Date fromdate, @Param("enddate") Date enddate);
	
}
