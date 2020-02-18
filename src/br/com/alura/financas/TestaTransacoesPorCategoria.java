package br.com.alura.financas;

import java.util.List;

import javax.persistence.EntityManager;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import br.com.alura.financas.model.Categoria;
import br.com.alura.financas.model.Transacao;
import br.com.alura.financas.util.PersistenceUtil;

public class TestaTransacoesPorCategoria {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JPQL.class);

	private static EntityManager entityManager = PersistenceUtil.createEntityManager();

	public static void main(String[] args) {
		
		try {
			entityManager.getTransaction().begin();
			Categoria categoria = entityManager.createNamedQuery("findCategoriaById", Categoria.class).setParameter("pId", 4L).getSingleResult();
			List<Transacao> transacoes = entityManager
				.createNamedQuery("findTransacaoByCategoria", Transacao.class)
				.setParameter("pCategoria", categoria)
				.getResultList();
			System.out.println("Transações: \n" + transacoes);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error("[TestaTransacoesPorCategoria]: ".concat(e.getMessage()));
		}

	}

}
