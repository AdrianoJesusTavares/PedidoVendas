package gyn.jesus.repository;

import gyn.jesus.NegocioException;
import gyn.jesus.filtros.UsuarioFilter;
import gyn.jesus.model.Grupo;
import gyn.jesus.model.Usuario;


import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class Usuarios extends RepositoryGenerico<Long, Usuario>{

	private static final long serialVersionUID = 1L;
	
	
	
	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}
	
	public List<Usuario> vendedores() {
		// TODO filtrar apenas vendedores (por um grupo específico)
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

	public Usuario porEmail(String email) {
		Usuario usuario = null;
		try{
		usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
				.setParameter("email", email.toLowerCase()).getSingleResult();
		}catch(NoResultException e){
			//Nenhum usuario encontrado pelo email
		}
		return usuario;
	}
	
	public List<Grupo> grupos(){
		
		return this.manager.createQuery("from Grupo", Grupo.class).getResultList();
	}
	
	

	
	public void remover(Usuario usuario){
		try{
		usuario = this.porId(usuario.getId());
		super.delete(usuario);
		manager.flush();
		}catch(PersistenceException e){
		//	e.printStackTrace();
			throw new NegocioException("Este Usuario nao pode ser excluido");
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter usuario) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);


		
		if (StringUtils.isNotBlank(usuario.getNome())) {
			criteria.add(Restrictions.ilike("nome", usuario.getNome(), MatchMode.ANYWHERE));
		}
		if (usuario.getGrupo() != null && usuario.getGrupo().size() > 0) {
			// adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
			criteria.add(Restrictions.in("descricao", usuario.getGrupo()));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

}