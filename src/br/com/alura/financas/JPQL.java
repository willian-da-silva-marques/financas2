package br.com.alura.financas;

import java.util.List;

import javax.persistence.EntityManager;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import br.com.alura.financas.model.TipoTransacao;
import br.com.alura.financas.model.Transacao;
import br.com.alura.financas.util.PersistenceUtil;

public class JPQL {

	private static final Logger LOGGER = LoggerFactory.getLogger(JPQL.class);

	private static EntityManager entityManager = PersistenceUtil.createEntityManager();
	
	public static void main(String[] args) {
		
		
		try {
			entityManager.getTransaction().begin();
			System.out.println("Lista de Transações:");
			List<Transacao> resultList = entityManager.createNamedQuery("findAllTransacoes", Transacao.class).getResultList();			
			resultList.stream().forEach(System.out::println);
			
			Transacao singleResult1 = entityManager.createNamedQuery("findTransacaoByContaId", Transacao.class).setParameter("pContaId", 4L).getSingleResult();
			System.out.println("Transação que pertence a conta de id = 4:");
			System.out.println(singleResult1);
			
			System.out.println("Transação de ENTRADA:");
			Transacao singleResult2 = entityManager.createNamedQuery("findTransacaoByTipoTransacao", Transacao.class).setParameter("pTipoTransacao", TipoTransacao.ENTRADA).getSingleResult();
			System.out.println(singleResult2);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error("[JPQL]: ".concat(e.getMessage()));
		}
		
	}
}