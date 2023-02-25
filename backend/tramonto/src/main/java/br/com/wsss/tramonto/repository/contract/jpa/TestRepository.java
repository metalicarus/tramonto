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

	Page<Test> findByTitleContaining(String title, Pageable pageable);

	@Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Test t WHERE t.id = :testId AND t.owner.id = :userId")
	boolean isTestOwner(@Param("testId") UUID testId, @Param("userId") UUID userId);

	@Query("SELECT CASE WHEN COUNT(tv) > 0 THEN true ELSE false END FROM TestVector tv WHERE tv.test.owner.id = :userId AND tv.id = :vectorId")
	boolean istTestOwnerByVectorId(@Param("vectorId") UUID vectorId, @Param("userId") UUID userId);

	@Query(nativeQuery = true, value = "SELECT t.* FROM tests t " 
	        + "JOIN users u ON t.owner = u.id "
	        + "LEFT JOIN test_tester tt ON t.id = tt.test_id "
	        + "WHERE ((u.id = :currentUserId) OR (tt.tester_id = :currentUserId)) "
	        + "AND (t.title LIKE %:title%)"
	        + "ORDER BY t.title, t.updated_at",
	        countQuery = "SELECT COUNT(*) FROM tests t " 
	        + "JOIN users u ON t.owner = u.id "
	        + "LEFT JOIN test_tester tt ON t.id = tt.test_id "
	        + "WHERE ((u.id = :currentUserId) OR (tt.tester_id = :currentUserId)) "
	        + "AND (t.title LIKE %:title%)")
	Page<Test> findByUser(@Param("currentUserId") String currentUserId, @Param("title") String title, Pageable pageRequest);

	@Modifying
	@Query(value = "DELETE FROM TestStrategy ts WHERE ts.pk.test.id = :testId")
	void deleteAllStrategies(@Param("testId") UUID testId);

	@Modifying
	@Query(value = "DELETE FROM TestChecklist tc WHERE tc.pk.test.id = :testId")
	void deleteAllChecklists(@Param("testId") UUID testId);

	@Modifying
	@Query(value = "DELETE FROM TestObjective to WHERE to.test.id = :testId")
	void deleteAllObjectives(@Param("testId") UUID testId);

	@Query(value = "SELECT t FROM Test t" + " LEFT JOIN FETCH t.checklists tc" + " LEFT JOIN FETCH tc.pk.checklist tcc"
			+ " LEFT JOIN FETCH t.type tt" + " LEFT JOIN FETCH t.objectives to" + " WHERE t.id = :id")
	Optional<Test> findById(@Param("id") UUID id);
}
