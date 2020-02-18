package br.com.alura.financas;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.persistence.EntityManager;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import br.com.alura.financas.model.Conta;
import br.com.alura.financas.model.TipoTransacao;
import br.com.alura.financas.util.PersistenceUtil;

public class TestaFuncoesJPQL {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestaFuncoesJPQL.class);
	
	private static final NumberFormat FORMAT = NumberFormat.getCurrencyInstance(); 
	
	private static EntityManager entityManager = PersistenceUtil.createEntityManager();
	
	public static void main(String[] args) {
		
		try {			
			Conta conta = entityManager
					.createNamedQuery("findContaById", Conta.class)
					.setParameter("pId", 1L)
					.getSingleResult();
			
			BigDecimal soma = entityManager
					.createNamedQuery("somaValorDeTodasAsTransacoesByContaAndTipoTransacao", BigDecimal.class)
					.setParameter("pConta", conta)
					.setParameter("pTipoTransacao", TipoTransacao.SAIDA)
					.getSingleResult();
			System.out.printf("Soma: %s%n",FORMAT.format(soma));
			
			BigDecimal menor = entityManager
					.createNamedQuery("menorValorDeTodasAsTransacoesByContaAndTipoTransacao", BigDecimal.class)
					.setParameter("pConta", conta)
					.setParameter("pTipoTransacao", TipoTransacao.SAIDA)
					.getSingleResult();
			System.out.printf("Menor valor: %s%n",FORMAT.format(menor));
			
			BigDecimal maior = entityManager
					.createNamedQuery("maiorValorDeTodasAsTransacoesByContaAndTipoTransacao", BigDecimal.class)
					.setParameter("pConta", conta)
					.setParameter("pTipoTransacao", TipoTransacao.SAIDA)
					.getSingleResult();
			System.out.printf("Maior valor: %s%n",FORMAT.format(maior));
			
			Double media = entityManager
					.createNamedQuery("mediaDosValorDeTodasAsTransacoesByContaAndTipoTransacao", Double.class)
					.setParameter("pConta", conta)
					.setParameter("pTipoTransacao", TipoTransacao.SAIDA)
					.getSingleResult();
			System.out.printf("Média: %s%n",FORMAT.format(media));
			
			Long quantidade = entityManager
					.createNamedQuery("contaQuantidadeDeTransacoesByConta", Long.class)
					.setParameter("pConta", conta)
					.getSingleResult();
			System.out.printf("A conta de número " + conta.getNumero() + " realizou um total de %s%n",quantidade + " transações.");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
