package br.com.alura.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
	
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("financas-mysql");

	public static EntityManagerFactory getPersistence() {		
		return ENTITY_MANAGER_FACTORY;
	}
	
	public static EntityManager createEntityManager() {
		return ENTITY_MANAGER_FACTORY.createEntityManager();
	}
	
	public static void close() {
		ENTITY_MANAGER_FACTORY.close();
	}
}
