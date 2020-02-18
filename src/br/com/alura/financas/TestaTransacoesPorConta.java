package br.com.alura.financas;

import java.util.List;

import javax.persistence.EntityManager;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import br.com.alura.financas.model.Conta;
import br.com.alura.financas.model.Transacao;
import br.com.alura.financas.util.PersistenceUtil;

public class TestaTransacoesPorConta {
		
	private static final Logger LOGGER = LoggerFactory.getLogger(TestaTransacoesPorConta.class);
	
	private static EntityManager entityManager = PersistenceUtil.createEntityManager();

	public static void main(String[] args) {
		
		try {			
			entityManager.getTransaction().begin();
			
			Conta conta = entityManager
					.createNamedQuery("findContaById", Conta.class)
					.setParameter("pId", 1L)
					.getSingleResult();
			
			List<Transacao> transacoes = entityManager
					.createNamedQuery("findTransacaoByConta", Transacao.class)
					.setParameter("pConta", conta)
					.getResultList();
			
			transacoes.forEach(System.out::println);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error("[TestaTransacoesPorConta]".concat(e.getMessage()));
		}
	}
}