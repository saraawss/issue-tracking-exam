package au.com.domain.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ISSUE", schema="ISSUE_TRACKER")
public class Issue implements Serializable{
	
	public Issue(Long id, String title, String description, String status, Long reporter, Long assignee, Date created, Date completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.reporter = reporter;
        this.assignee = assignee;
        this.created = created;
        this.completed = completed;
        
    }

	public Issue() {
		
	}
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "REPORTER")
	private Long reporter;
	
	@Column(name = "ASSIGNEE")
	private Long assignee;
	
	@Column(name = "CREATED")
	private Date created;
	
	@Column(name = "COMPLETED")
	private Date completed;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="id")
	private List<Comment> commentList;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getReporter() {
		return reporter;
	}
	public void setReporter(Long reporter) {
		this.reporter = reporter;
	}
	public Long getAssignee() {
		return assignee;
	}
	public void setAssignee(Long assignee) {
		this.assignee = assignee;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getCompleted() {
		return completed;
	}
	public void setCompleted(Date completed) {
		this.completed = completed;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	
	
}
