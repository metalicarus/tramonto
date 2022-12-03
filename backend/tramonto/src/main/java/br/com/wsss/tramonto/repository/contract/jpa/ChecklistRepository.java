package br.com.wsss.tramonto.repository.contract.jpa;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsss.tramonto.entity.Checklist;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, UUID>{
	Page<Checklist> findByChecklistContaining(String checklist, Pageable pageable);
}
