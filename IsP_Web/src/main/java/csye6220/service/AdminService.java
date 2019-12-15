package csye6220.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import csye6220.common.HibernateUtil;
import csye6220.model.Admin;
import csye6220.model.Customer;


@Service
public class AdminService {
	
	
	public AdminService() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Admin authEmployee(String username, String password)
	{
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
		
		try 
		{

			SQLQuery q=sess.createSQLQuery("select* from Admin where employeeID=:username and password=:pass");
			q.setParameter("username", username);
			q.setParameter("pass", password);
			q.addEntity(Admin.class);

			Admin employee=(Admin)q.getSingleResult();
	         
			tran.commit();
			sess.close();
			return employee;
		
		}
		catch (NoResultException e)
		{
			System.out.println("Authenication failed wrong username and password");
			sess.close();
			return null;
		}
		catch (Exception e)
		{
			System.out.println("Failed....to Authenticate admin user something worng!!! "+e.toString());
			sess.close();
			return null;
		}
	}
	
}
