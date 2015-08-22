package gyn.jesus.repository;

import gyn.jesus.anotations.Transactional;


import gyn.jesus.model.Categoria;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;




public class RepositoryGenerico<PK, T> implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@Inject
	
	EntityManager manager;
	
	   @Transactional
	   public T salvar( T entidade) {
		   return this.manager.merge(entidade);
	    }
	   
	   
	 
	    public void delete(T entidade) {
	    	this.manager.remove(entidade);
	    }
	 
	    @SuppressWarnings("unchecked")
		public List<T> listarTodos() {
	        return this.manager.createQuery(("FROM " + getTypeClass().getName())).getResultList();
	    }
	    

	    private Class<?> getTypeClass() {
	        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
	                .getGenericSuperclass()).getActualTypeArguments()[1];
	        return clazz;
	    }
	    
	
}
