package au.com.domain.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENT", schema="ISSUE_TRACKER")
public class Comment implements Serializable{
	
	
	public Comment() {
		
	}

	@Id
	@Column(name = "ID")
    @GeneratedValue
    private Long id;
	
	@Column(name = "USERID")
	private Long userid;
	
	@Column(name = "ISSUEID")
	private Long issueid;
	
	@Column(name = "COMMENT")
	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getIssueid() {
		return issueid;
	}

	public void setIssueid(Long issueid) {
		this.issueid = issueid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
