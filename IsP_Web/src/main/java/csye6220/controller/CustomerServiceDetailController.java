package csye6220.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import csye6220.model.Address;
import csye6220.model.City;
import csye6220.model.ClientIP;
import csye6220.model.Customer;
import csye6220.model.CustomerServiceDetail;
import csye6220.model.ServicePackage;
import csye6220.service.AddressService;
import csye6220.service.CityService;
import csye6220.service.ClientIPService;
import csye6220.service.CustomerService;
import csye6220.service.ServiceDetailService;
import csye6220.service.ServicePackageService;


@Controller
public class CustomerServiceDetailController {

	@Autowired
	ServicePackageService packageService;
	
	@Autowired
	CustomerService customerService;
		
	@Autowired
	ServiceDetailService serviceDetailService;
		
	@Autowired
	CityService cityService;
	
	@Autowired
	ClientIPService clientIPService;
	
	@Autowired
	AddressService addressService;
	
	
	@RequestMapping(value="/customer/serviceDetail/get")
	public ModelAndView getServiceDetail(HttpServletRequest request) {
             
		
		 HttpSession session= request.getSession();
		 Customer customer=(Customer)session.getAttribute("AuthCustomer");
		 
		 CustomerServiceDetail c=  serviceDetailService.findByCustomerID(customer.getCustomerID().intValue());
		 
		 
		 if(c==null)
		 {
			 
			 return new ModelAndView("customerProfilePage","NoCurrentService","true");
		 }
		 else
		 {
			 
			 return new ModelAndView("customerProfilePage","serviceDetail",c);
		 }
		 
		

	}
	
	
	
	
	@RequestMapping(value="/customer/serviceDetail/createPage")
	public ModelAndView servicePackagePage(@RequestParam(required=true) int packageID,
			HttpServletRequest request) 
	{

		 
		// check if customer already has IP provisioned 
		 Customer c= (Customer)request.getSession().getAttribute("AuthCustomer");
		 if(customerService.hasActiveService(c.getCustomerID()))
		 {
			 return new ModelAndView("failPage","errorMessage","Our record shows you have been already in-service."
			 		                          + "<br> If you would like to cancel or change any service detail please contact us through number 213-567-9876<br>");
		 }
		
		 
		 List<City> cityList= cityService.getAllCity();
		 
		 return new ModelAndView("serviceDetailForm","address",new Address())
				                .addObject("packageID", packageID)
				                .addObject("cityList", cityList);	             
	}
	
	
	
	@RequestMapping(value="/customer/serviceDetail/create")
	public ModelAndView createServiceDetail(@ModelAttribute("address") Address address,
			@RequestParam(required=true) int cityID,
			@RequestParam(required=true) int packageID,
			HttpServletRequest request) 
	{
		
		
		//Logic
		City city= cityService.getByID(cityID);	
		
		ClientIP ip=clientIPService.getNextFreeClientIP(city.getCityNetwork().getCityNetworkID());
		
		address.setCity(city);
		address.setBind_IPv4(ip);
		
		ServicePackage servicePackage= packageService.findPackageByID(packageID);
		
		Customer customer=(Customer)request.getSession().getAttribute("AuthCustomer");
		
		CustomerServiceDetail serviceDetail= new CustomerServiceDetail();
		
		
		serviceDetail.setAddress(address);
		serviceDetail.setServicePackage(servicePackage);
		serviceDetail.setCustomer(customer);
		serviceDetail.setEndServiceDate(servicePackage.getContractDuration());
		
		
		
		if(addressService.save(address)<0 || serviceDetailService.save(serviceDetail)<0)
		{
			return new ModelAndView("failPage","errorMessage","Something wrong while saving serviceDetail and address info");
		}
		else
		{
			System.out.println(ip.getClient_IPv4()+" is assigned to customer....");
			return new ModelAndView("customerProfilePage","serviceDetail",serviceDetail)
					               .addObject("activatedMessage", "Your service has been activated !!<br>"
					               		+ "Network service will be provisioned to "+address.getStreet()+"<br>");
		}
		

		
		
              
	}
	
	
	@RequestMapping(value="/admin/serviceDetail/update")
	public ModelAndView updateServiceDetailPage(@RequestParam(required=true) int id,
												HttpServletRequest request) 
	{
	
		CustomerServiceDetail serviceDetail=serviceDetailService.findByCustomerID(id);
		
		List<City> cityList= cityService.getAllCity();
		
		
		 return new ModelAndView("updateServiceDetail","address",serviceDetail.getAddress())
				                .addObject("customerID", id)
				                .addObject("cityList",cityList)
				                .addObject("packageID", serviceDetail.getServicePackage().getServicePackageID());
	}
	
	
	
	
	@RequestMapping(value="/admin/serviceDetail/updateAction")
	public ModelAndView updateServiceDetail(@ModelAttribute("address") Address address,
												@RequestParam(required=true) int cityID,
												@RequestParam(required=true) int packageID,
												@RequestParam(required=true) int customerID,
												HttpServletRequest request) 
	{
	    
		
		
		CustomerServiceDetail serviceDetail=serviceDetailService.findByCustomerID(customerID);

		
		String logMessage="";
		
		
		//Start Logic
	     if(serviceDetail.getAddress().getCity().getCityID()!=cityID)
	     {
	    	
	    	 City newCity=cityService.getByID(cityID);
	    	 
	    	 
	    	 logMessage=logMessage+"<p>INFO: customer changed city form "+serviceDetail.getAddress().getCity().getCityName()+" to "+newCity.getCityName()+"</p>";
	    	
	    	 
	    	 logMessage=logMessage+"<p>INFO: changing customer to new cityNetwork "+ newCity.getCityNetwork().getNetwork()+"</p>";
	    	 
	    	 
	    	 logMessage=logMessage+"<p>INFO: customer changed to new address: "+address.getStreet()+", "+address.getZipcode()+"</p>";
	    	    	 
	    	 
	    	 logMessage=logMessage+"<p>INFO: releasing old IP:"+ serviceDetail.getAddress().getBind_IPv4().getClient_IPv4()+"</p>";
	    	 
	    	 
	    	 Address oldAddress=serviceDetail.getAddress();
	    	 
	    	 oldAddress.setCity(newCity);
	    	 oldAddress.setStreet(address.getStreet());
	    	 oldAddress.setZipcode(address.getZipcode());
	    	 
	         ClientIP ip= clientIPService.getNextFreeClientIP(newCity.getCityNetwork().getCityNetworkID());
	         
	         
	         logMessage=logMessage+"<p>INFO: provisioning new IP: <strong>"+ip.getClient_IPv4()+"</strong> to "+address.getStreet()+", "+address.getZipcode()+"</p>";
	    	 
	         oldAddress.setBind_IPv4(ip);	
	         
	         addressService.update(oldAddress);
	         
	         serviceDetail.setAddress(oldAddress);
	    	 
	    	 
	     }
	     
	     
	     
	     
	     if (packageID!=serviceDetail.getServicePackage().getServicePackageID())
	     {
	    	 
	    	  ServicePackage  servicePackage=packageService.findPackageByID(packageID);
	    	 
	         logMessage=logMessage+"<p>INFO: customer changed network service package to "+ servicePackage.getPackageName()+"</p>";
	         
	         serviceDetail.setServicePackage(servicePackage);
	    	 
	     }
	     
	     
         logMessage=logMessage+"<p>INFO: reconfiguring upstream and downstream speed.....</p>";
         
         logMessage=logMessage+"<p>INFO: configuring upstream speed to "+serviceDetail.getServicePackage().getUpStreamSpeed()+" Mbps</p>";
	    	
         logMessage=logMessage+"<p>INFO: configuring downstream speed to "+serviceDetail.getServicePackage().getDownStreamSpeed()+" Mbps</p>";
	    	
 		
         
         System.out.print(serviceDetailService.update(serviceDetail));
         
         
         return new ModelAndView("updateServiceDetailReport","logMessage",logMessage)
        		                   .addObject("customerID", customerID)
                                   .addObject("ip",serviceDetail.getAddress().getBind_IPv4().getClient_IPv4());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
