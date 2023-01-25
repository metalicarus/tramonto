package br.com.wsss.tramonto.repository.contract.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsss.tramonto.entity.TestChecklist;
import br.com.wsss.tramonto.entity.pk.TestChecklistPk;

@Repository
public interface TestChecklistRepository extends JpaRepository<TestChecklist, TestChecklistPk>{}
