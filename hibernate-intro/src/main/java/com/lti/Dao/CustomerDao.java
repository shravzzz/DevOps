package com.lti.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.lti.entity.Customer;

public class CustomerDao {

	//to insert
	/*public void databaseIlAddSeiyavum(Customer customer) {
		//Step 1.  Load/Create EntityManagerFactory object
		//During this step, META-INF/persistence.xml is read
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("oracle-pu");
		
		//Step 2. Load/Create EntityManager object
		EntityManager em = emf.createEntityManager();
		
		//Step 3 Start /Participate in a transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		//Now we can insert/update/delete/select whatever we want
		//the object (customer) should be an object of an entity class always.
		em.persist(customer); //persist method generates insert query
		
		tx.commit();
		
		//below code should be in finally block
		em.close();
		emf.close();
	}*/
	
	//to fetch
	public Customer fetchById(int custId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("oracle-pu");
		EntityManager em = emf.createEntityManager();
		
		//find method generates select query
		Customer c = em.find(Customer.class,	custId);
		
		em.close();
		emf.close();
		
		return c;
	}
	
	
	//to update
	public void insertOrupdate(Customer customer) {
		
		EntityManagerFactory emf= null;
		EntityManager em =  null;
		try {
			
		 emf = Persistence.createEntityManagerFactory("oracle-pu");
		 em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.merge(customer);
		
		tx.commit();
		
	}
	finally {
		
		em.close();
		emf.close();
	}
	}
	
	public List<Customer> fetchAll(){
		EntityManagerFactory emf= null;
		EntityManager em =  null;
		try {
			
		 emf = Persistence.createEntityManagerFactory("oracle-pu");
		 em = emf.createEntityManager();
		
		
		//Introducing JP-QL
		Query q = em.createQuery("select c from Customer c");
		List<Customer> list= q.getResultList();
		return list;
		}
		
		finally {
			
		em.close();
		emf.close();
		}
	
		
	}
	public List<Customer> fetchAllByEmail(String email){
		EntityManagerFactory emf= null;
		EntityManager em =  null;
		try {
			
		 emf = Persistence.createEntityManagerFactory("oracle-pu");
		 em = emf.createEntityManager();
		
		//Introducing JP-QL
		Query q = em.createQuery("select c from Customer as c where c.email like :em");
		q.setParameter("em", "%" + email + "%");
	//q.setParameter(1,email); ///instead of :em use ? in the above query
		List<Customer> list= q.getResultList();
		return list;
		}
	finally {
		em.close();
		emf.close();
	}
		
	}
}
