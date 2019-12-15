package csye6220.service;

import javax.persistence.NoResultException;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import csye6220.common.HibernateUtil;
import csye6220.model.Customer;
import csye6220.model.CustomerServiceDetail;


@Service
public class ServiceDetailService {
	
	
	public ServiceDetailService() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public CustomerServiceDetail findByCustomerID(int customerID)
	{
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();

         
		try 
		{

		SQLQuery q=sess.createSQLQuery("select * from CustomerServiceDetail where customerID=:id");
		q.setParameter("id",customerID);
		q.addEntity(CustomerServiceDetail.class);

		
		Object o=q.getSingleResult();
		CustomerServiceDetail serviceDetail=(CustomerServiceDetail)o;
		
         
		tran.commit();
		sess.close();
		
		return serviceDetail;

		}
		catch (NoResultException e)
		{
		   System.out.println("No current Service on customerID "+Integer.toString(customerID));
		   sess.close();
           return null;  
		}
		catch (Exception e) {
			System.out.println("Failed to get service by customerID"+Integer.toString(customerID)+e.toString());
			sess.close();
	        return null;  
		}
		
	}
	
	
	
	public long save(CustomerServiceDetail serviceDetail)
	{
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
		
		try 
		{

        Long id;
       
		id=(Long)sess.save(serviceDetail);

		
		tran.commit();
		sess.close();
		
		System.out.println("Saved new CustomerServiceDetail id "+Long.toString(id)+" to DB...");
		
		return id;
		
		}
		catch (Exception e)
		{
			System.out.println("Failed to save CustomerServiceDetail to DB..."+e.toString());
			sess.close();
			return -1;
		}
		
	}
	
	
	
	
	public boolean update(CustomerServiceDetail serviceDetail)
	{
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
		
		try 
		{

        
	    sess.update(serviceDetail);
		tran.commit();
		sess.close();
		
		System.out.println("udpated new CustomerServiceDetail");
		return true;
		
		}
		catch (Exception e)
		{
			System.out.println("Failed to update CustomerServiceDetail ..."+e.toString());
			sess.close();
			return false;
		}
		
	}
	
	
	

}
