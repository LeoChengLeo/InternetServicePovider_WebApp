
package csye6220.service;


import java.util.List;

import javax.persistence.NoResultException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import csye6220.common.HibernateUtil;
import csye6220.model.City;
import csye6220.model.Customer;

@Service
public class CityService {

	
	public CityService()
	{
		
	}
	
	
	
	public  List<City> getAllCity()
	{
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
       
		try 
		{

		SQLQuery q=sess.createSQLQuery("select * from City");
		q.addEntity(City.class);

		 List<City> list= q.getResultList();
		 
		 
		tran.commit();
		sess.close();
		
		return list;

		}
		catch (Exception e)
		{
		   System.out.println("Failed to get list of city "+e.toString());
		   sess.close();
           return null;
       
		}
	}
	
	
	
	
	
	
	
	public  City getByID(int id)
	{
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
       
		try 
		{

		SQLQuery q=sess.createSQLQuery("select * from City where cityID=:id");
		q.setParameter("id", id);
		q.addEntity(City.class);

		 City city= (City)q.getSingleResult();
		 
		tran.commit();
		sess.close();
		
		return city;

		}
		catch (Exception e)
		{
		   System.out.println("Failed to get city  "+e.toString());
		   sess.close();
           return null;
       
		}
	}
	
	
	
	
	
}










