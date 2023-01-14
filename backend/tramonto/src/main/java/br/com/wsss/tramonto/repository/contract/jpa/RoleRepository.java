package br.com.wsss.tramonto.repository.contract.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsss.tramonto.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID>{
	Optional<Role> findByRole(br.com.wsss.tramonto.domain.type.Role role);
}
