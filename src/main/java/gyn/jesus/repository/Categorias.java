package gyn.jesus.repository;

import gyn.jesus.anotations.Transactional;
import gyn.jesus.model.Categoria;

import java.io.Serializable;
import java.util.List;




import javax.inject.Inject;
import javax.persistence.EntityManager;

public class Categorias implements Serializable {


	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;
	
	@Transactional
	public void salvar(Categoria categoria){
		this.manager.merge(categoria);
	}
	
	public Categoria porId(Long id){
		return manager.find(Categoria.class, id);
	}
	
	public List<Categoria> listar(){
		
	return manager.createQuery("from Categoria where categoriaPai is null", Categoria.class).getResultList();	
	}
	
	public List<Categoria> subCategoriaDe(Categoria categoriaPai){
		return this.manager.createQuery("from Categoria where categoriaPai = :raiz", Categoria.class)
				.setParameter("raiz", categoriaPai).getResultList();
	}

}
