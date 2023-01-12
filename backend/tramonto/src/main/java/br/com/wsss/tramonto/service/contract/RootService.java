package br.com.wsss.tramonto.service.contract;

import java.util.Set;

import br.com.wsss.tramonto.domain.type.Status;

public interface RootService<T, U> {
	Set<T> findAll();
	T findById(U id);
	T update(T dto);
	T save(T dto);
	void active(U id, Status status);
}
