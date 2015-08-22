package gyn.jesus.repository;

import gyn.jesus.NegocioException;
import gyn.jesus.anotations.Transactional;
import gyn.jesus.filtros.ProdutoFilter;
import gyn.jesus.model.Produto;


import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class Produtos extends RepositoryGenerico<Long, Produto> {

	private static final long serialVersionUID = 1L;



	public void remover(Produto produto){
		try{
		produto = this.porId(produto.getId());
		super.delete(produto);
		manager.flush();
		}catch(PersistenceException e){
			
			throw new NegocioException("Este produto nao pode ser excluido");
			
		}
	}
	public Produto porSku(String sku) {
		try {
			return manager
					.createQuery("from Produto where upper(sku) = :sku",
							Produto.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoFilter produto) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);

		if (StringUtils.isNotBlank(produto.getSku())) {
			criteria.add(Restrictions.ilike("sku", produto.getSku(), MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(produto.getNome())) {
			criteria.add(Restrictions.ilike("nome", produto.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Produto porId(Long id){
		return this.manager.find(Produto.class, id);
	}

	@Transactional
	public List<Produto> porNome(String nome) {
		
		return this.manager.createQuery("from Produto " +
				"where upper(nome) like :nome", Produto.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
}
