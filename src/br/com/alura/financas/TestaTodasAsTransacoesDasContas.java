package br.com.alura.financas;

import java.util.List;

import javax.persistence.EntityManager;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import br.com.alura.financas.model.Conta;
import br.com.alura.financas.util.PersistenceUtil;

public class TestaTodasAsTransacoesDasContas {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestaTodasAsTransacoesDasContas.class);
	
	private static EntityManager entityManager = PersistenceUtil.createEntityManager();

	public static void main(String[] args) {

		try {			
			List<Conta> contas = entityManager.createNamedQuery("findAllContas", Conta.class).getResultList();
			contas.stream().forEach(c -> {
				System.out.println("Nome do títular: " + c.getTitular().getNome());
				System.out.println("Transações realizadas: ");
				System.out.println(c.getTransacoes());
			});
		} catch (Exception e) {
			LOGGER.error("[TestaTodasAsTransacoesDasContas]".concat(e.getMessage()));
		}
		
	}
}