package gyn.jesus.repository;


import gyn.jesus.model.Grupo;
import java.util.List;
import javax.persistence.NoResultException;


public class Grupos extends RepositoryGenerico<Long, Grupo>{

	private static final long serialVersionUID = 1L;
	

	
	public Grupo porId(Long id) {
		return this.manager.find(Grupo.class, id);
	}
	
	
	
	public List<Grupo> grupos(){
		
		return this.manager.createQuery("from Grupo", Grupo.class).getResultList();
	}

	public Grupo porNome(String nome) {
		try {
			return manager
					.createQuery("from Grupo where upper(nome) = :nome", Grupo.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}