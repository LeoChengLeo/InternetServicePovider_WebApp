package csye6220.service;


import java.util.List;

import javax.persistence.NoResultException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import csye6220.common.HibernateUtil;
import csye6220.model.City;
import csye6220.model.CityNetwork;
import csye6220.model.Customer;


@Service
public class CityNetworkService {

	public CityNetworkService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public  CityNetwork getByID(int id)
	{
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
       
		try 
		{

		SQLQuery q=sess.createSQLQuery("select * from CityNetwork where cityNetworkID=:id");
		q.setParameter("id", id);
		q.addEntity(CityNetwork.class);

		 CityNetwork cityNetwork= (CityNetwork)q.getSingleResult();
		 
		tran.commit();
		sess.close();
		
		return cityNetwork;

		}
		catch (Exception e)
		{
		   System.out.println("Failed to get cityNetwork  "+e.toString());
		   sess.close();
           return null;
       
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
