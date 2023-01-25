package br.com.wsss.tramonto.repository.contract.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsss.tramonto.entity.TestStrategy;
import br.com.wsss.tramonto.entity.pk.TestStrategyPk;

@Repository
public interface TestStrategyRepository extends JpaRepository<TestStrategy, TestStrategyPk>{}
