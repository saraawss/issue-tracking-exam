package au.com.domain.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER", schema="ISSUE_TRACKER")
public class User implements Serializable{
	
	public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

	public User() {
		
	}

	@Id
	@Column(name = "ID")
    @GeneratedValue
    private Long id;
	
	@Column(name = "USERNAME")
	private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
}
