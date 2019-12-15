package csye6220.service;
import java.util.List;

import javax.persistence.NoResultException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import csye6220.common.HibernateUtil;
import csye6220.model.City;
import csye6220.model.CityGateway;
import csye6220.model.Customer;


@Service
public class GatewayService {

	public GatewayService() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public  CityGateway getByID(int id)
	{
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
       
		try 
		{

		SQLQuery q=sess.createSQLQuery("select * from CityGateway where cityGatewayID=:id");
		q.setParameter("id", id);
		q.addEntity(CityGateway.class);

		 CityGateway cityGty= (CityGateway)q.getSingleResult();
		 
		tran.commit();
		sess.close();
		
		return cityGty;

		}
		catch (Exception e)
		{
		   System.out.println("Failed to get cityGateway  "+e.toString());
		   sess.close();
           return null;
       
		}
	}
	
	
	
	
	
	
	
	
	
	
}
