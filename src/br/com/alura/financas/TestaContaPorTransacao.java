package br.com.alura.financas;

import java.util.List;

import javax.persistence.EntityManager;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import br.com.alura.financas.model.Conta;
import br.com.alura.financas.model.Transacao;
import br.com.alura.financas.util.PersistenceUtil;

public class TestaContaPorTransacao {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestaContaPorTransacao.class);

	private static EntityManager entityManager = PersistenceUtil.createEntityManager();

	public static void main(String[] args) {

		try {
			entityManager.getTransaction().begin();

			Conta conta = entityManager.createNamedQuery("findContaById", Conta.class).setParameter("pId", 1L).getSingleResult();
			System.out.println("1º: " + conta.getTitular().getNome());
			List<Transacao> transacoes = entityManager.createNamedQuery("findTransacaoByConta", Transacao.class).setParameter("pConta", conta).getResultList();

			transacoes.forEach(transacao -> {
				Conta conta2 = entityManager
						.createNamedQuery("findContaByTransacoes", Conta.class)
						.setParameter("pTransacoes", transacao)
						.getSingleResult();
				System.out.println("2º: " + conta2.getTitular().getNome());
			});

			entityManager.getTransaction().commit();

		} catch (Exception e) {
			LOGGER.error("[TestaContaPorTransacao]".concat(e.getMessage()));
		}

	}

}
