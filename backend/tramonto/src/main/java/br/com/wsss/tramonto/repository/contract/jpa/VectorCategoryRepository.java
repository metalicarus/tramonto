package br.com.wsss.tramonto.repository.contract.jpa;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsss.tramonto.entity.VectorCategory;

@Repository
public interface VectorCategoryRepository extends JpaRepository<VectorCategory, UUID>{
	Page<VectorCategory> findByVectorCategoryContaining(String vectorCategory, Pageable pageable);
}
