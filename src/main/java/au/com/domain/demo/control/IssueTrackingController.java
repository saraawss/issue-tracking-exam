package au.com.domain.demo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import au.com.domain.demo.entity.Comment;
import au.com.domain.demo.entity.Issue;
import au.com.domain.demo.entity.User;
import au.com.domain.demo.json.IssueJson;
import au.com.domain.demo.repository.IssueRepository;
import au.com.domain.demo.repository.UserRepository;

@RestController
public class IssueTrackingController {
	
	
	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	UserRepository userRepository;
	
	//--Submit Issue--
	@RequestMapping(value = "/issue/submitData", method = RequestMethod.POST)
    public @ResponseBody Issue createIssue(@RequestBody IssueJson issueJson) {
		Issue issue = new Issue();
		issue.setTitle(issueJson.getTitle());
		issue.setDescription(issueJson.getDescription());
		issue.setStatus(issueJson.getStatus());
		issue.setReporter(issueJson.getReporter());
		issue.setAssignee(issueJson.getAssignee());
		issue.setCreated(new Date());
		issue.setCompleted(issueJson.getCompleted());
		
		issueRepository.save(issue);
		
		return issue;
	}
	
	//--Get Issue--
	@RequestMapping(value = "/issue/getData", method = RequestMethod.GET)
    public @ResponseBody List<IssueJson> getIssueList() {
		
		List<IssueJson> issueJsonList = new ArrayList();
		List<Issue> issueList = (List<Issue>) issueRepository.findAll();
		
		for(Issue issue : issueList) {
			IssueJson issueJson = new IssueJson();
			
			issueJson.setId(issue.getId());
			issueJson.setTitle(issue.getTitle());
			issueJson.setDescription(issue.getDescription());
			issueJson.setStatus(issue.getStatus());
			
			if(issue.getReporter() != null) {
				User userReporter = userRepository.findOne(issue.getReporter());
				issueJson.setReporterName(userReporter.getUsername());
			}else {
				issueJson.setReporterName("-");
			}
			
			if(issue.getAssignee() != null) {
				User userAssignee = userRepository.findOne(issue.getAssignee());
				issueJson.setAssigneeName(userAssignee.getUsername());
			}else {
				issueJson.setAssigneeName("-");
			}
			
			issueJson.setCreated(issue.getCreated());
			issueJson.setCompleted(issue.getCompleted());
			
			issueJsonList.add(issueJson);
		}
		
		return issueJsonList;
	}
	
	//--Update Issue--
	@RequestMapping(value = "/update_issue/{id}", method = RequestMethod.PUT)
    public Issue updateIssue(@PathVariable("id") long id, @RequestBody IssueJson issueJson) {
		Issue issue = new Issue();
		issue.setId(id);
		issue.setTitle(issueJson.getTitle());
		issue.setDescription(issueJson.getDescription());
		issue.setStatus(issueJson.getStatus());
		issue.setReporter(issueJson.getReporter());
		issue.setAssignee(issueJson.getAssignee());
		issue.setCreated(new Date());
		issue.setCompleted(issueJson.getCompleted());
		
		issueRepository.save(issue);
		
		return issue;
	}
	
	//--Delete Issue--
	@RequestMapping(value = "/deleteIssue/{id}", method = RequestMethod.DELETE)
    public boolean deleteIssue(@PathVariable("id") long id) {
		Issue issue = new Issue();
		issue.setId(id);
		issueRepository.delete(issue);
		
		return true;
		
	}
	
	//--Get Issue By Id--
		@RequestMapping(value = "/updateform/{id}", method = RequestMethod.GET)
	    public @ResponseBody IssueJson getIssueById(@PathVariable("id") long id) {
			
			Issue issue = issueRepository.findOne(id);
			
			IssueJson issueJson = new IssueJson();
			issueJson.setId(issue.getId());
			issueJson.setTitle(issue.getTitle());
			issueJson.setDescription(issue.getDescription());
			issueJson.setStatus(issue.getStatus());
			issueJson.setReporter(issue.getReporter());
			issueJson.setAssignee(issue.getAssignee());
			issueJson.setCreated(issue.getCreated());
			issueJson.setCompleted(issue.getCompleted());
			
			return issueJson;
		}
		
		//--get User--
		@RequestMapping(value = "/user/getData", method = RequestMethod.GET)
	    public @ResponseBody List<User> getUserList() {
			List<User> userList = (List<User>) userRepository.findAll();
			return userList;
		}
		
		//--Search Issue--
		@RequestMapping(value = "/issue/search", method = RequestMethod.POST)
	    public @ResponseBody List<Issue> searchIssue(@RequestBody IssueJson issueJson) {
			
			List<Issue> issueList = issueRepository.findByRepoterAndAssigneeAndCreated(issueJson.getReporter(),issueJson.getAssignee(),issueJson.getFromdate(),issueJson.getEnddate());
			return issueList;
		}
	
}
