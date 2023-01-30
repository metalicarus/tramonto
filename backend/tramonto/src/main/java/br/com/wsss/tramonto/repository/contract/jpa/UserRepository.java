package br.com.wsss.tramonto.repository.contract.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.wsss.tramonto.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	@Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.roles ur WHERE u.email = :email")
	Optional<User> findByEmail(@Param("email") String email);

	@Query(nativeQuery = true, value = "SELECT	c.*\n"
			+ "FROM		roles			a\n"
			+ "JOIN		users_roles		b	on	b.role_id = a.id\n"
			+ "JOIN		users			c	on	c.id = b.user_id\n"
			+ "WHERE	a.role like %:roleParam%\n"
			+ "AND 		c.id != :currentUserId")
	List<User> findDifferentTesters(@Param("currentUserId") String currentUserId, @Param("roleParam") String roleParam);
}
