package br.com.alura.financas.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.alura.financas.model.Categoria;
import br.com.alura.financas.model.Cliente;
import br.com.alura.financas.model.Conta;
import br.com.alura.financas.model.Endereco;
import br.com.alura.financas.model.QueryModel;

public interface Repository<T> {

	public void persist(T t);

	List<T> find(Class<T> clazz);

	Optional<T> find(Class<T> clazz, Long id);

	public Optional<T> update(Class<T> clazz, T t);

	public void delete(Class<T> clazz, Long id);

	public default QueryModel getQueryModel(Class<T> clazz) {
		String className = clazz.getSimpleName();
		String alias = className.substring(0, 1).toLowerCase();
		String columnName = Arrays.asList(clazz.getDeclaredFields())
				.stream()
				.filter(field -> field.getType().equals(Long.class))
				.findFirst()
				.get()
				.getName();
		return new QueryModel(className, columnName, alias);
	}
	
	public default Object getValue(T t) {
		if (t instanceof Categoria)
			return ((Categoria) t);
		else if (t instanceof Cliente)
			return ((Cliente) t);
		else if (t instanceof Conta)
			return ((Conta) t);
		else if (t instanceof Endereco)
			return ((Endereco) t);
		else
			return null;
	}
	
	public default Optional<T> getSingleResult(EntityManager entityManager, QueryModel queryModel, Class<T> clazz, Long id) {
		T singleResult = null;
		if (clazz.getSimpleName().equals("Cliente")) {
			String query = "SELECT " + queryModel.getAlias() + " FROM " + queryModel.getClassName() + " "
					+ queryModel.getAlias() + " INNER JOIN FETCH " + queryModel.getAlias().concat(".endereco e")
					+ " INNER JOIN FETCH " + queryModel.getAlias().concat(".conta cc")
					+ " WHERE "	+ queryModel.getAlias().concat(".").concat(queryModel.getColumnName()) + " = :id";
			singleResult = entityManager.createQuery(query, clazz).setParameter("id", id).getSingleResult();
				return Optional.of(singleResult);
		}
		String query = "SELECT " + queryModel.getAlias() + " FROM " + queryModel.getClassName() + " "
				+ queryModel.getAlias() + " WHERE "
				+ queryModel.getAlias().concat(".").concat(queryModel.getColumnName()) + " = :id";
		singleResult = entityManager.createQuery(query, clazz).setParameter("id", id).getSingleResult();
			return Optional.of(singleResult);
	}
}
