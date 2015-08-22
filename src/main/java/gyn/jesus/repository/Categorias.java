package gyn.jesus.repository;

import gyn.jesus.model.Categoria;


import java.util.List;

public class Categorias extends RepositoryGenerico<Long, Categoria> {


	private static final long serialVersionUID = 1L;
	

	
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
