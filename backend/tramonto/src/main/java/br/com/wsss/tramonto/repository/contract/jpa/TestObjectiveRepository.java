package br.com.wsss.tramonto.repository.contract.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsss.tramonto.entity.TestObjective;

@Repository
public interface TestObjectiveRepository extends JpaRepository<TestObjective, UUID>{}
