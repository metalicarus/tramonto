package br.com.wsss.tramonto.repository.contract.jpa;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsss.tramonto.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, UUID>{
	Page<Test> findByTitleContaining(String title, Pageable pageable);
//	List<Test> findByTitleContaining(String title);
}
