package com.lti.test;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import com.lti.Dao.CustomerDao;
import com.lti.Dao.GenericDao;
import com.lti.entity.Customer;
import com.lti.entity.Order;
import com.lti.entity.Payment;

public class CustomerDaoTest {

	@Test
	public void testAdd() {
		Customer c = new Customer();
		//INSERT
		c.setName("Sri");
		c.setEmail("sri@gmail.com");
		c.setCity("Vizag");
		
		//c.setDateOfBirth(dateOfBirth); //try this on your own
		
		CustomerDao dao= new CustomerDao();
		dao.insertOrupdate(c);
	}
	
	@Test
	public void testFetch( ) {
		CustomerDao dao = new CustomerDao();
		Customer cust= dao.fetchById(39);//FETCH
		
		System.out.println(cust.getName());
		System.out.println(cust.getEmail());
		System.out.println(cust.getDateOfBirth());
		System.out.println(cust.getCity());
		
	}
	
	@Test
	public void testUpDate() {
		CustomerDao dao = new CustomerDao();
		Customer cust= dao.fetchById(40);     //first fetch then update
		cust.setName("Nidhi");
		cust.setCity("Italy");
		dao.insertOrupdate(cust);//UPDATE
		
	}
	
	@Test
	public void testFetchAll( ) {
		CustomerDao dao = new CustomerDao();
		List<Customer> cust= dao.fetchAll();//FETCH
		
		for(Customer c : cust) {
		System.out.println(c.getName());
		System.out.println(c.getEmail());
		System.out.println(c.getDateOfBirth());
		System.out.println(c.getCity());
		}
	
	}
	@Test
	public void testFetchAllByEmail( ) {
		CustomerDao dao = new CustomerDao();
		List<Customer> cust= dao.fetchAllByEmail("sri@gmail.com");//FETCH
		
		for(Customer c : cust) {
			System.out.println(c.getName());
			System.out.println(c.getEmail());
			System.out.println(c.getDateOfBirth());
			System.out.println(c.getCity());
			}
	
		
}
	
	@Test
	public void placeOrder() {
		
		GenericDao dao= new GenericDao();
       Customer cust = (Customer) dao.fetchById(Customer.class,40);
		
       Order o = new Order();
		o.setOrderDate(new Date());
		o.setAmount(5000);
		o.setCustomer(cust);
		
		dao.save(o);
		
		
	}
	@Test
	public void fetchOrders( ) {
		GenericDao dao= new GenericDao();
	      List <Order> orders = (List<Order>) dao.fetchAll(Order.class);
		
		for(Order o : orders) {
		System.out.println(o.getId());
		System.out.println(o.getAmount());
		System.out.println(o.getOrderDate());
		System.out.println(o.getCustomer().getEmail());
		}
	
	}
	
	@Test
		public void addpayment() {
		
		GenericDao dao = new GenericDao();
		Payment payment = new Payment();
		
		payment.setAmount(15000);
		payment.setPaymentMode("Credit Card");
		payment.setPaymentStatus("Success");
		
		//order.setPayment(payment.);
		
		dao.save(payment);		
	}

	@Test
	public void linkOrderAndPayment() {
		
		GenericDao dao = new GenericDao();
		
		Order order = (Order) dao.fetchById(Order.class, 101);
		
		Payment payment = (Payment)dao.fetchById(Payment.class, 121);
		
		order.setPayment(payment);
		
		dao.save(order);		
	}
}
