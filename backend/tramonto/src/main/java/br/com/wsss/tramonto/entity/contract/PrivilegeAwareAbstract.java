package br.com.wsss.tramonto.entity.contract;

import br.com.wsss.tramonto.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PrivilegeAwareAbstract extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean belongsToCurrentUser = false;

}
