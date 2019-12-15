package csye6220.service;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import csye6220.common.HibernateUtil;
import csye6220.model.City;
import csye6220.model.ClientIP;
import csye6220.model.Customer;

@Service
public class ClientIPService {

	
	public ClientIPService() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ClientIP getNextFreeClientIP(int networkID)
	{
		
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
       
		try 
		{

		SQLQuery q=sess.createSQLQuery("select top(1) ip.client_IPv4,ip.cityNetworkID from ClientIP ip "
				+ "left join Address a on ip.client_IPv4=a.client_IPv4 "
				+ "where ip.cityNetworkID=:networkID and a.addressID is null");
		
		
		q.setParameter("networkID", networkID);
		q.addEntity(ClientIP.class);

		ClientIP clientIP= (ClientIP)q.getSingleResult();
		 
		 
		tran.commit();
		sess.close();
		
		return clientIP;

		}
		catch (Exception e)
		{
		   System.out.println("Failed to get next free clientIP "+e.toString());
		   sess.close();
           return null;
       
		}
	}
	
	
	
	public ClientIP findByID(String ip)
	{
		
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
       
		try 
		{

		SQLQuery q=sess.createSQLQuery("select * from ClientIP where client_IPv4=:ip");
			
		q.setParameter("ip", ip);
		q.addEntity(ClientIP.class);

		ClientIP clientIP= (ClientIP)q.getSingleResult();
		 
		 
		tran.commit();
		sess.close();
		
		return clientIP;

		}
		catch (Exception e)
		{
		   System.out.println("Failed to get clientIP "+e.toString());
		   sess.close();
           return null;
       
		}
	}
	
	
	
	//Return a list of active ClientIP in certain cityNetwork 
	public List<ClientIP> getActiveClientIPByCityNetworkID(int cityNetworkID)
	{
		
		
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();
       
		try 
		{

		SQLQuery q=sess.createSQLQuery("select * from ClientIP _ip "
				+ "left join Address addr on _ip.client_IPv4=addr.client_IPv4 "
				+ "where _ip.cityNetworkID=:id and addr.addressID is  not null");
			
		q.setParameter("id", cityNetworkID);
		q.addEntity(ClientIP.class);

		List<ClientIP> clientIPList= q.getResultList();
		 
		 
		tran.commit();
		sess.close();
		
		return clientIPList;

		}
		catch (Exception e)
		{
		   System.out.println("Failed to get clientIPList "+e.toString());
		   sess.close();
		   List<ClientIP> list= new ArrayList<ClientIP>();
           return list;
		}
       
		}
	
	
	
	
	
	
	
	
	
}
