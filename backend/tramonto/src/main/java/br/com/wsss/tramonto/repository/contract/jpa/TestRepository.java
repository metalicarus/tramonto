package br.com.wsss.tramonto.repository.contract.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.wsss.tramonto.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, UUID> {
	
	@Modifying
	@Query(value= "DELETE FROM TestChecklist tc WHERE tc.pk.test.id = :testId")
	void deleteAllChecklists(@Param("testId") UUID testId);
	
	@Modifying
	@Query(value= "DELETE FROM TestObjective to WHERE to.test.id = :testId")
	void deleteAllObjectives(@Param("testId") UUID testId);
	
	@Query(value= "SELECT t FROM Test t"
			+ " LEFT JOIN FETCH t.checklists tc"
			+ " LEFT JOIN FETCH tc.pk.checklist tcc"
			+ " LEFT JOIN FETCH t.type tt"
			+ " LEFT JOIN FETCH t.objectives to"
			+ " WHERE t.id = :id")
	Optional<Test> findById(@Param("id") UUID id);
	
	Page<Test> findByTitleContaining(String title, Pageable pageable);
}
