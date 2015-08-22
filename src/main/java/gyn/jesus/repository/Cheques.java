package gyn.jesus.repository;

import gyn.jesus.NegocioException;
import gyn.jesus.filtros.ChequeFilter;
import gyn.jesus.model.Cheque;
import java.util.List;
import javax.persistence.PersistenceException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


public class Cheques extends RepositoryGenerico<Long, Cheque> {
	

	private static final long serialVersionUID = 1L;
	

	 
		@SuppressWarnings("unchecked")
		public List<Cheque> filtrados(ChequeFilter filtro) {
			Session session = this.manager.unwrap(Session.class);
			
			Criteria criteria = session.createCriteria(Cheque.class).createAlias("cliente", "c");
        	// fazemos uma associação (join) com cliente e nomeamos como "c"

			if (filtro.getDataCriacaoDe() != null) {
				criteria.add(Restrictions.ge("dataVencimentoChque", filtro.getDataCriacaoDe()));
			}
			
			if (filtro.getDataCriacaoAte() != null) {
				criteria.add(Restrictions.le("dataVencimentoChque", filtro.getDataCriacaoAte()));
			}
			
			if (StringUtils.isNotBlank(filtro.getNomeCliente())) {
				// acessamos o nome do cliente associado ao pedido pelo alias "c", criado anteriormente
				criteria.add(Restrictions.ilike("c.nome", filtro.getNomeCliente(), MatchMode.ANYWHERE));
			}
			
			
			return criteria.addOrder(Order.asc("id")).list();
		}

		public void remover(Cheque cheque) {
			try{
				cheque = this.porId(cheque.getId());
				super.delete(cheque);
				manager.flush();
				}catch(PersistenceException e){
					throw new NegocioException("Este cheque nao pode ser excluido" );
					
				}
			
		}

		public Cheque porId(Long id) {
			
			return this.manager.find(Cheque.class, id);
		}


}
