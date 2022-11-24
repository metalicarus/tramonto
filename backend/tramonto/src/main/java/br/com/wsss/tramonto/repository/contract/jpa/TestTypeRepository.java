package br.com.wsss.tramonto.repository.contract.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsss.tramonto.entity.TestType;

@Repository
public interface TestTypeRepository extends JpaRepository<TestType, UUID> {}