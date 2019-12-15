package csye6220.service;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import csye6220.common.HibernateUtil;
import csye6220.model.Customer;
import csye6220.model.ServicePackage;

@Service
public class ServicePackageService {

	public ServicePackageService()
	{
		
	}
	
	
	public ServicePackage findPackageByID(int id)
	{
		Session sess= HibernateUtil.getSession();
		Transaction tran= sess.beginTransaction();

         
		try 
		{

		SQLQuery q=sess.createSQLQuery("select * from servicePackage where servicePackageID=:id");
		q.setParameter("id", id);
		q.addEntity(ServicePackage.class);

		ServicePackage p=(ServicePackage)q.getSingleResult();
         
		tran.commit();
		sess.close();
		
		return p;

		}
		catch (Exception e)
		{
		   System.out.println("Failed to get ServicePackage by id:"+Integer.toString(id)+e.toString());
		   sess.close();
           return null;
       
		}
	}
	
}
