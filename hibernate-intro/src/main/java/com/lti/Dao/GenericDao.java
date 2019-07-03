package com.lti.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.lti.entity.Customer;

public class GenericDao {

public void save(Object obj) {                         //save is for insert and update
		
		EntityManagerFactory emf= null;
		EntityManager em =  null;
		try {
			
		 emf = Persistence.createEntityManagerFactory("oracle-pu");
		 em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.merge(obj);
		
		tx.commit();
		
	}
	finally {
		
		em.close();
		emf.close();
	}
	}
	

public Object  fetchById(Class classname, Object id) {
	EntityManagerFactory emf= null;
	EntityManager em =  null;
	try {
		
	 emf = Persistence.createEntityManagerFactory("oracle-pu");
	 em = emf.createEntityManager();
	
	//find method generates select query
	Object obj = em.find(classname, id);
	
	return obj;
}
          finally {
	
	em.close();
  	emf.close();
}
}

public <E> List<E> fetchAll(Class<E> clazz){
	EntityManagerFactory emf= null;
	EntityManager em =  null;
	try {
		
	 emf = Persistence.createEntityManagerFactory("oracle-pu");
	 em = emf.createEntityManager();
	
	
	//Introducing JP-QL
	Query q = em.createQuery("select obj from	 "+ clazz.getName()+ " as obj");
	List<E> list= q.getResultList();
	return  list;
	}
	
	finally {
		
	em.close();
	emf.close();
	}

}
}
