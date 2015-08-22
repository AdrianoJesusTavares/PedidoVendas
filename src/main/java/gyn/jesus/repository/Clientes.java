package gyn.jesus.repository;

import gyn.jesus.NegocioException;
import gyn.jesus.filtros.ClienteFilter;
import gyn.jesus.model.Cliente;



import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Cliente porId(Long id) {
		return this.manager.find(Cliente.class, id);
	}
	
	public List<Cliente> porNome(String nome) {
		return this.manager.createQuery("from Cliente " +
				"where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
	
	
	public Cliente guardar(Cliente cliente){
	
		return this.manager.merge(cliente);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(ClienteFilter cliente) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);

		if (StringUtils.isNotBlank(cliente.getDocumentoReceitaFederal())) {
			criteria.add(Restrictions.eq("documentoReceitaFederal", cliente.getDocumentoReceitaFederal()));
		}
		
		if (StringUtils.isNotBlank(cliente.getNome())) {
			criteria.add(Restrictions.ilike("nome", cliente.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
	public void remover(Cliente cliente){
		try{
		cliente = this.porId(cliente.getId());
		manager.remove(cliente);
		manager.flush();
		}catch(PersistenceException e){
			//e.printStackTrace();
			throw new NegocioException("Este cliente nao pode ser excluido" );
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> clientes() {
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);
		
		 return criteria.addOrder(Order.asc("nome")).list();
	}	
}
