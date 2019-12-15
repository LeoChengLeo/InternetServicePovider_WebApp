package csye6220.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import csye6220.model.Admin;
import csye6220.model.Customer;
import csye6220.service.AdminService;
import csye6220.service.CustomerService;

@Controller
public class CustomerController {
	
	
	@Autowired
	private CustomerService customerRepo;
	
	@Autowired
	private AdminService adminService;

	@RequestMapping(value="/")
	public ModelAndView hello() {
             
		return new ModelAndView("home");
	}
	
	
	
	@RequestMapping(value="/home")
	public ModelAndView home() {
             
		return new ModelAndView("homePage");
	}
	
	
	
	@RequestMapping(value="/test")
	public ModelAndView test(HttpServletRequest request) {
             
	    
		return new ModelAndView("test");
	}
	
	

	@RequestMapping(value="/loginPage")
	public ModelAndView loginPage() {
             
		return new ModelAndView("loginPage");
	}
	
	

	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam(required=true) String  username,
			                  @RequestParam(required=true) String  password,
			                  @RequestParam(required=true) String  role,
			                  HttpServletRequest request) 
	{   
		

		
	if (role.equals("customer"))
	{
		
		Customer customer=customerRepo.authCustomer(username, password);
		
		if(customer!=null)
		{
			HttpSession sess= request.getSession();
			sess.setAttribute("Authenticated", "true");
			sess.setAttribute("AuthCustomer",customer);
			return new ModelAndView("homePage");
		}
		else
		{
			return new ModelAndView("loginPage","inValidMessage","Invalid username or password !! <br><br>");
		}
		
	}
	else
	{
		
		
		Admin employee = adminService.authEmployee(username, password);
		
		if(employee!=null)
		{
			HttpSession sess= request.getSession();
			sess.setAttribute("AdminUser", "true");
			sess.setAttribute("AuthEmployee",employee);
			return new ModelAndView("adminPage");
		}
		else
		{
			return new ModelAndView("loginPage","inValidMessage","Invalid username or password !! <br><br>");
		}
		
	}
		
		
	
	}
	
	
	@RequestMapping(value="/logout")
	public ModelAndView logout( HttpServletRequest request) {
             request.getSession().invalidate();
		return new ModelAndView("homePage");
	}
	
	
	
	
	@RequestMapping(value="/createCustomerPage")
	public ModelAndView createCustomerPage() {
         		
		return new ModelAndView("createCustomer","customer",new Customer());
	}
	
	

	
	
	
	@RequestMapping(value="/createCustomerAction")
	public ModelAndView createCustomerAction(@ModelAttribute("customer") Customer customer,
			                              HttpServletRequest request
			) 
    {
         
	
		 if(!customerRepo.isNewUser(customer)) return new ModelAndView("createCustomer","customer",new Customer())
				                                          .addObject("errorMessage", "Invalid userName please use another !! <br><br>");
		 		 
		 
		 //Controller Logic
		 if (customerRepo.save(customer)>0)
		 {
			 
			 try
			 {
				HttpSession session=request.getSession();
				System.out.println(customer.getCustomerID());
				session.setAttribute("Authenticated","true");
				session.setAttribute("AuthCustomer", customer);
			 }
			 catch (Exception e)
			 {
				 System.out.print("Failed to handle new customer session ..."+e.toString());
			 }
			 
			 return new ModelAndView("homePage");
		 }
		 else
		 {
			 return new ModelAndView("failPage", "errorMessage","Failed to create new customer");
		 }
		
		
		
		
	}
	
	
	
	@RequestMapping(value="customer/update")
	public ModelAndView updateCustomerPage(HttpServletRequest request) {
         		
		Customer c=(Customer)request.getSession().getAttribute("AuthCustomer");
		
		return new ModelAndView("updateCustomer","customer",c);
	}
	
	
	
	@RequestMapping(value="customer/updateAction")
	public ModelAndView updateCustomerAction(@ModelAttribute("customer") Customer customer,
			HttpServletRequest request) {
         		
		HttpSession sess= request.getSession();
		
		Customer c= (Customer)sess.getAttribute("AuthCustomer");
		
		c=customerRepo.findByID(c.getCustomerID().intValue());
		
		c.setUserName(customer.getUserName());
		c.setFirstName(customer.getFirstName());
		c.setLastName(customer.getLastName());
		c.setPhoneNumber(customer.getPhoneNumber());
		
		System.out.print("updating....");
		customerRepo.update(c);
		
		//update Session Object
		sess.setAttribute("AuthCustomer",c);
	
		return new ModelAndView("homePage","updateMessage","Your personal info has been updated successfully!");
	}

	
}
