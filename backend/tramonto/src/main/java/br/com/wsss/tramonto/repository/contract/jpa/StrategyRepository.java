package br.com.wsss.tramonto.repository.contract.jpa;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsss.tramonto.entity.Strategy;

@Repository
public interface StrategyRepository extends JpaRepository<Strategy, UUID>{
	Page<Strategy> findByStrategyContaining(String strategy, Pageable pageable);
}
