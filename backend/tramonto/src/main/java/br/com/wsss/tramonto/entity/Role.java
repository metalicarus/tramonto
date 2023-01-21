package br.com.wsss.tramonto.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLES")
@Getter
@Setter
public class Role extends BaseEntity implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private br.com.wsss.tramonto.domain.type.Role role;
	
	@ElementCollection
	private Set<String> routes;

	@Override
	public String getAuthority() {
		return this.role.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		Role r = (Role) o;
		return role.equals(r.getRole()) && getId().equals(r.getId());
	}

	@Override
	public String toString() {
		return "Role {"
				+ "id=" + getId()
				+ "role=" + getRole()
				+"}";
	}
}
