package csye6220.service;



import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import csye6220.common.HibernateUtil;
import csye6220.model.Customer;


@Service
public class CustomerService {
	
	
	
	public CustomerService() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Customer findByID(int id)
	{
		
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();

         
		try 
		{

		SQLQuery q=sess.createSQLQuery("select * from Customer where CustomerID= :customerID");
		q.setParameter("customerID", id);
		q.addEntity(Customer.class);

		Customer customer=(Customer)q.getSingleResult();
         
		tran.commit();
		sess.close();
		
		return customer;

		}
		catch (Exception e)
		{
		   System.out.println("Failed to get customer by id:"+Integer.toString(id)+e.toString());
		   sess.close();
           return null;
       
		}
		
	}
	
	
	
	
	
	public long save(Customer c)
	{
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
		
		try 
		{

        Long id;
       
        c.encryptPass();
        
     
		id=(Long)sess.save(c);

	
		tran.commit();
		sess.close();
		
		System.out.println("Saved Customer id "+Long.toString(id)+" to DB...");
		
		return id;
		
		}
		catch (Exception e)
		{
			System.out.println("Failed to save Customer to DB..."+e.toString());
			sess.close();
			return -1;
		}
		
	}
	
	
	
	
	
	public boolean update(Customer c)
	{
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
		
		try 
		{

        
		sess.update(c);

	
		tran.commit();
		sess.close();
		
		System.out.println("Updated Customer ......");
		
		return true;
		
		}
		catch (Exception e)
		{
			System.out.println("Failed to update Customer....."+e.toString());
			sess.close();
			return false;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Customer authCustomer(String username, String password)
	{
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
		
		try 
		{

			SQLQuery q=sess.createSQLQuery("select* from Customer where userName=:username");
			q.setParameter("username", username);
			q.addEntity(Customer.class);

			Customer customer=(Customer)q.getSingleResult();
	         
			
			tran.commit();
			sess.close();
					
			System.out.println(customer.passwordCheck(password));
			return customer.passwordCheck(password) ? customer : null;
		
		}
		catch (NoResultException e)
		{
			System.out.println("Authenication failed wrong username and password");
			sess.close();
			return null;
		}
		catch (Exception e)
		{
			System.out.println("Failed....to Authenticate user something worng!!! "+e.toString());
			sess.close();
			return null;
		}
		
		
		
	}
	
	
	
	
	
	
	
// Allow findBy value  customerServiceID, firstName, userName, and cityName	
	public List<Customer> find(String findBy,String value)
	{
		
		
		String query="select customer.customerID, customer.passwordHash, customer.userName, customer.lastName, customer.phoneNumber, customer.firstName \r\n" + 
				"from CustomerServiceDetail csd \r\n" + 
				"join Customer customer on csd.customerID=customer.customerID\r\n" + 
				"join Address addr on csd.addressID=addr.addressID\r\n" + 
				"join City city on addr.cityID=city.cityID\r\n" + 
				"where "+findBy+"='"+value+"'";
				

		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();

         
		try 
		{

		SQLQuery q=sess.createSQLQuery(query);
		q.addEntity(Customer.class);

		List<Customer> customers=q.getResultList();
         
		tran.commit();
		sess.close();
		
		return customers;

		}
		catch (NoResultException e)
		{
		   System.out.println("No result found for customer.....");
		   sess.close();
           return null;
		}
		catch (Exception e)
		{
			 System.out.println("Failed to search by keyword something wrong....."+ e.toString());
			 sess.close();
	         return null;
		}
		
	}
	
	
	
	
	
	public boolean hasActiveService(long id)
	{
		
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();

         
		try 
		{

		SQLQuery q=sess.createSQLQuery("select * from Customer where CustomerID= :customerID");
		q.setParameter("customerID", id);
		q.addEntity(Customer.class);

		Customer customer=(Customer)q.getSingleResult();
         
		tran.commit();
		sess.close();
		
		
		return customer.getServiceDetail()!=null? true:false;

		}
		catch (Exception e)
		{
		   System.out.println("Failed to get customer by id "+e.toString());
		   sess.close();
           return false;
       
		}
		
	}
	
	
	
	public boolean isNewUser(Customer c)
	{
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
		
		try 
		{
		
			SQLQuery q=sess.createSQLQuery("select * from Customer where userName= :userName");
			q.setParameter("userName", c.getUserName());
			q.addEntity(Customer.class);

			Customer customer=(Customer)q.getSingleResult();
	         
			tran.commit();
			sess.close();
			
			return false;
		
		}
		catch (Exception e)
		{
			System.out.println("Not found by userName..."+e.toString());
			sess.close();
			return true;
		}
		
	}
	
	
	
	
	
	
	
	

}
