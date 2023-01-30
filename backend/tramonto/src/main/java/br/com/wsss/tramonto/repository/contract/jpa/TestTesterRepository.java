package br.com.wsss.tramonto.repository.contract.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsss.tramonto.entity.TestTester;
import br.com.wsss.tramonto.entity.pk.TestTesterPk;

@Repository
public interface TestTesterRepository extends JpaRepository<TestTester, TestTesterPk>{}
