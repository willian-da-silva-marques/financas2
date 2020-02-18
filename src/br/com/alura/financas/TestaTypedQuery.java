package br.com.alura.financas;

import java.text.NumberFormat;
import java.util.List;

import javax.persistence.EntityManager;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import br.com.alura.financas.model.Conta;
import br.com.alura.financas.model.TipoTransacao;
import br.com.alura.financas.util.PersistenceUtil;

public class TestaTypedQuery {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestaTypedQuery.class);
	
	private static final NumberFormat FORMAT = NumberFormat.getCurrencyInstance(); 
	
	private static EntityManager entityManager = PersistenceUtil.createEntityManager();

	public static void main(String[] args) {
		
		try {
			Conta conta = entityManager.createNamedQuery("findContaById", Conta.class).setParameter("pId", 1L).getSingleResult();
			
			String query = "SELECT avg(t.valor) FROM Transacao t WHERE t.conta = :pConta and t.tipoTransacao = :pTipoTransacao GROUP BY day(t.dataHora), month(t.dataHora), year(t.dataHora)";
			
			List<Double> mediasPorDia = entityManager.createQuery(query, Double.class).setParameter("pConta",conta).setParameter("pTipoTransacao", TipoTransacao.SAIDA).getResultList();
			
			System.out.println("Media do dia 15: " + FORMAT.format(mediasPorDia.get(0)));
			System.out.println("Media do dia 25: " + FORMAT.format(mediasPorDia.get(1)));
			
		} catch (Exception e) {
			LOGGER.error("[TestaTypedQuery]".concat(e.getMessage()));
		}
	}
}