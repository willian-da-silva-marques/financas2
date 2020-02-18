package br.com.alura.financas.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import br.com.alura.financas.exception.RegistroNaoLocalizadoException;
import br.com.alura.financas.model.Categoria;
import br.com.alura.financas.model.Cliente;
import br.com.alura.financas.model.Conta;
import br.com.alura.financas.model.Endereco;
import br.com.alura.financas.model.QueryModel;
import br.com.alura.financas.util.PersistenceUtil;

@SuppressWarnings("unchecked")
public class CrudRepository<T> implements Repository<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrudRepository.class);

	private EntityManager getEntityManager() {
		return PersistenceUtil.createEntityManager();
	}

	@Override
	public void persist(T t) {
		EntityManager entityManager = this.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(t);
			entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<T> find(Class<T> clazz) {
		EntityManager entityManager = this.getEntityManager();
		QueryModel queryModel = this.getQueryModel(clazz);
		try {
			String query = "SELECT " + queryModel.getAlias() + " FROM " + queryModel.getClassName()
			+ " " + queryModel.getAlias() + " WHERE " + queryModel.getAlias().concat(".").concat(queryModel.getColumnName()) + " IS NOT NULL";
			List<T> resultList = entityManager.createQuery(query, clazz).getResultList();
			if (resultList == null)
				return resultList = new ArrayList<>();
			return resultList;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Optional<T> find(Class<T> clazz, Long id) {
		EntityManager entityManager = this.getEntityManager();
		QueryModel queryModel = this.getQueryModel(clazz);
		try {
			String query = "SELECT " + queryModel.getAlias() + " FROM " + queryModel.getClassName()
			+ " " + queryModel.getAlias() + " WHERE " + queryModel.getAlias().concat(".").concat(queryModel.getColumnName()) + " = :id";
			T singleResult = entityManager.createQuery(query, clazz).setParameter("id", id).getSingleResult();
			if (singleResult != null)
				return Optional.of(singleResult);
		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage());
		} finally {
			entityManager.close();
		}
		throw new RegistroNaoLocalizadoException(queryModel.getClassName().concat(" não localizado(a)."));
	}

	@Override
	public Optional<T> update(Class<T> clazz, T t) {
		EntityManager entityManager = this.getEntityManager();
		QueryModel queryModel = this.getQueryModel(clazz);
		Object object = this.getValue(t);
		try {
			if (object != null)
				if (object instanceof Categoria) {
					Categoria categoria = (Categoria) object;
					Optional<Categoria> categoriaFinded = (Optional<Categoria>) this.getSingleResult(entityManager, queryModel, clazz, categoria.getId());
					if (!categoriaFinded.isPresent()) {
						throw new RegistroNaoLocalizadoException(queryModel.getClassName().concat(" não localizado(a)."));
					} else {
						Categoria categoriaEdited = categoriaFinded.get();
						categoriaEdited.setTipoCategoria(categoria.getTipoCategoria());
						entityManager.getTransaction().begin();
						Categoria categoriaSaved = entityManager.merge(categoriaEdited);
						entityManager.getTransaction().commit();
						return (Optional<T>) Optional.of(categoriaSaved);
					}
				} else if (object instanceof Cliente) {
					Cliente cliente = (Cliente) object;
					Optional<Cliente> clienteFinded = (Optional<Cliente>) this.getSingleResult(entityManager, queryModel, clazz, cliente.getId());
					if (!clienteFinded.isPresent()) {
						throw new RegistroNaoLocalizadoException(queryModel.getClassName().concat(" não localizado(a)."));
					} else {
						Cliente clienteEdited = clienteFinded.get();
						clienteEdited.setNome(cliente.getNome());
						clienteEdited.setProfissao(cliente.getProfissao());
						clienteEdited.setConta(cliente.getConta());
						clienteEdited.setEndereco(cliente.getEndereco());
						entityManager.getTransaction().begin();
						Cliente categoriaSaved = entityManager.merge(clienteEdited);
						entityManager.getTransaction().commit();
						return (Optional<T>) Optional.of(categoriaSaved);
					}
				} else if (object instanceof Conta) {
					Conta conta = (Conta) object;
					Optional<Conta> contaFinded = (Optional<Conta>) this.getSingleResult(entityManager, queryModel, clazz, conta.getId());
					if (!contaFinded.isPresent()) {
						throw new RegistroNaoLocalizadoException(queryModel.getClassName().concat(" não localizado(a)."));
					} else {
						Conta contaEdited = contaFinded.get();
						contaEdited.setAgencia(conta.getAgencia());
						contaEdited.setBanco(conta.getBanco());
						contaEdited.setNumero(conta.getNumero());
						contaEdited.setTitular(conta.getTitular());
						entityManager.getTransaction().begin();
						Conta contaSaved = entityManager.merge(contaEdited);
						entityManager.getTransaction().commit();
						return (Optional<T>) Optional.of(contaSaved);
					}
				} else if (t instanceof Endereco) {
					Endereco endereco = (Endereco) object;
					Optional<Endereco> enderecoFinded = (Optional<Endereco>) this.getSingleResult(entityManager, queryModel, clazz, endereco.getId());
					if (!enderecoFinded.isPresent()) {
						throw new RegistroNaoLocalizadoException(queryModel.getClassName().concat(" não localizado(a)."));
					} else {
						Endereco enderecoEdited = enderecoFinded.get();
						enderecoEdited.setBairro(endereco.getBairro());
						enderecoEdited.setCep(endereco.getCep());
						enderecoEdited.setCidade(endereco.getCidade());
						enderecoEdited.setComplemento(endereco.getComplemento());
						enderecoEdited.setLogradouro(endereco.getLogradouro());
						enderecoEdited.setPais(endereco.getPais());
						endereco.setUf(endereco.getUf());
						entityManager.getTransaction().begin();
						Endereco enderecoSaved = entityManager.merge(enderecoEdited);
						entityManager.getTransaction().commit();
						return (Optional<T>) Optional.of(enderecoSaved);
					}
				}
		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage());
		} finally {
			entityManager.close();
		}
		throw new RegistroNaoLocalizadoException(queryModel.getClassName().concat(" não localizado(a)."));
	}

	@Override
	public void delete(Class<T> clazz, Long id) {
		EntityManager entityManager = this.getEntityManager();
		QueryModel queryModel = this.getQueryModel(clazz);
		try {
			String query = "SELECT " + queryModel.getAlias() + " FROM " + queryModel.getClassName()
			+ " " + queryModel.getAlias() + " WHERE " + queryModel.getAlias().concat(".").concat(queryModel.getColumnName()) + " = :id";
			T singleResult = entityManager.createQuery(query, clazz).setParameter("id", id).getSingleResult();
			if (singleResult == null) {
				throw new RegistroNaoLocalizadoException(queryModel.getClassName().concat(" não localizado(a)."));
			} else {
				entityManager.getTransaction().begin();
				entityManager.remove(singleResult);
				entityManager.getTransaction().commit();
			}
		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage());
		} finally {
			entityManager.close();
		}
	}
}
