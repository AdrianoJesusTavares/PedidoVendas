package gyn.jesus.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class RepositoryGenerico<PK, T> implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@Inject
	
	EntityManager manager;

	   public void salvar( T entidade) {
		   manager.merge(entidade);
	    }
}
