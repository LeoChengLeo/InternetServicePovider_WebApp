package csye6220.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import csye6220.common.HibernateUtil;
import csye6220.model.Address;
import csye6220.model.ClientIP;
import csye6220.model.Customer;


@Service
public class AddressService {

	
	public AddressService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public long save(Address address)
	{
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
		
		try 
		{

        int id;
       
		id=(Integer)sess.save(address);

		
		tran.commit();
		sess.close();
		
		System.out.println("Saved new Address to DB...");
		
		return id;
		
		}
		catch (Exception e)
		{
			System.out.println("Failed to save Address to DB..."+e.toString());
			sess.close();
			return -1;
		}
			
		
	}
	
	
	
	
	
	public boolean update(Address address)
	{
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
		
		try 
		{
  
	    sess.update(address);
		
		tran.commit();
		sess.close();
		
		System.out.println("Updated Address to DB...");
		
		return true;
		
		}
		catch (Exception e)
		{
			System.out.println("Failed to update Address..."+e.toString());
			sess.close();
			return false;
		}
			
		
	}
	
	
	
	
	
	
	
	
	
	
	public List<Address>  getAddressBycityNetworkID(int cityNetworkID)
	{
		
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
       
		try 
		{

		SQLQuery q=sess.createSQLQuery("SELECT addr.addressID,addr.cityID,addr.client_IPv4,addr.street,addr.zipcode "
				+ "from ClientIP _ip join Address addr on _ip.client_IPv4=addr.client_IPv4 where _ip.cityNetworkID=:id");
			
		q.setParameter("id", cityNetworkID);
		q.addEntity(Address.class);

		List<Address> addressList= q.getResultList();
		 
		 
		tran.commit();
		sess.close();
		
		return addressList;

		}
		catch (Exception e)
		{
		   System.out.println("Failed to get addressList "+e.toString());
		   sess.close();
		   List<Address> addressList= new ArrayList<Address>();
           return addressList;
       
		}
	}
	
	
	
	
	
	
}
