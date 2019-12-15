package csye6220.common;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import csye6220.model.Address;
import csye6220.model.Admin;
import csye6220.model.City;
import csye6220.model.CityGateway;
import csye6220.model.CityNetwork;
import csye6220.model.ClientIP;
import csye6220.model.Customer;
import csye6220.model.CustomerServiceDetail;
import csye6220.model.ServicePackage;

public class HibernateUtil {

	
	private static SessionFactory sessionFectory=null;
	
	
	static 
	{
		try
		{
		    System.out.println("Start Initializing Hibernate Utility Class.......");
			loadSessionFectory();
			
		}
		catch (Exception e)
		{
			System.err.print("Failed to initialize Hibernate Utility class..."+e.toString());
		}
	}
	
	
	public static void loadSessionFectory()
	{
		Configuration cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Customer.class);
		cfg.addAnnotatedClass(CustomerServiceDetail.class);
		cfg.addAnnotatedClass(ServicePackage.class);
		cfg.addAnnotatedClass(CityGateway.class);
		cfg.addAnnotatedClass(CityNetwork.class);
		cfg.addAnnotatedClass(City.class);
		cfg.addAnnotatedClass(Address.class);
		cfg.addAnnotatedClass(ClientIP.class);
		cfg.addAnnotatedClass(Admin.class);
		
		ServiceRegistry srvReg= new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		sessionFectory=cfg.buildSessionFactory(srvReg);
	}
	
	
	public static Session getSession() throws HibernateException
	{
		try
		{
			
		  if (sessionFectory==null) loadSessionFectory();
		  return  sessionFectory.openSession();	
		}
		catch (Throwable t)
		{
			System.err.println("Failed to get a new Session in HibernateUtil..");
			t.printStackTrace();
			return null;
		}
		
	}
	
	
}
