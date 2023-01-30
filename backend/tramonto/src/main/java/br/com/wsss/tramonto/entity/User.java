package br.com.wsss.tramonto.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User extends BaseEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6429450831431788484L;

	@Column(name= "FIRST_NAME", nullable= false)
	private String firstName;
	
	@Column(name= "LAST_NAME", nullable= false)
	private String lastName;
	
	@Column(name= "email", nullable= false, unique= true)
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name= "PASSWORD", nullable= false)
	private String password;
	
	@ManyToMany
    @JoinTable(name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

	public User(User u) {
		this.firstName = u.getFirstName();
		this.lastName = u.getLastName();
		this.email = u.getEmail();
		this.roles = u.getRoles();
		this.createdAt = u.getCreatedAt();
		this.updatedAt = u.getUpdatedAt();
		this.status = u.getStatus();
		this.setId(u.getId());
	}

}
