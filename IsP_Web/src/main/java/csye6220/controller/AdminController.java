package csye6220.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import csye6220.model.Address;
import csye6220.model.City;
import csye6220.model.CityGateway;
import csye6220.model.CityNetwork;
import csye6220.model.ClientIP;
import csye6220.model.Customer;
import csye6220.service.AddressService;
import csye6220.service.CityNetworkService;
import csye6220.service.CityService;
import csye6220.service.ClientIPService;
import csye6220.service.CustomerService;
import csye6220.service.GatewayService;


@Controller
public class AdminController {

	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ClientIPService clientIPService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	CityNetworkService networkService;
	
	@Autowired
	GatewayService gtyService;
	
	@Autowired
	AddressService addressService;
	
	
	@RequestMapping(value="/admin")
	public ModelAndView adminHome() {
             
		return new ModelAndView("adminPage");
	}
	
	
	@RequestMapping(value="/admin/searchPage")
	public ModelAndView adminSearchPage() {
             
		return new ModelAndView("searchPage");
	}
	
	
	
	@RequestMapping(value="/admin/search")
	public ModelAndView adminSearch(@RequestParam(required=true) String  searchBy,
			                        @RequestParam(required=true) String  keyword) 
	{
             		
	 System.out.println(searchBy+" "+keyword);
     List<Customer> customers=customerService.find(searchBy, keyword);
     
     return new ModelAndView("searchResultTable").addObject("customers", customers);
		
	}
	
	
	
	
	
	@RequestMapping(value="/admin/customerProfile")
	public ModelAndView adminCustomerProfile(@RequestParam(required=true) int id) {
             
		Customer customer= customerService.findByID(id);	
     	return new ModelAndView("adminCustomerProfilePage","customer",customer);
	}
	
	
	
	
	
	@RequestMapping(value="/admin/netSpec")
	public ModelAndView adminInfr(@RequestParam(required=true) String ip) {
             
		ClientIP clientIP=clientIPService.findByID(ip);
		
		return new ModelAndView("netSpecPage","clientIP",clientIP);
		
	}
	
	
	
	@RequestMapping(value="/admin/infr")
	public ModelAndView adminInfr() {
             
    
		
		List<City> cityList=cityService.getAllCity();
		
		return new ModelAndView("infrTable","cityList",cityList);
	}
	
	
	@RequestMapping(value="/admin/network")
	public ModelAndView adminNetwork(@RequestParam(required=true) int id) {
             
       CityNetwork net= networkService.getByID(id);
		
         List<ClientIP> list=clientIPService.getActiveClientIPByCityNetworkID(id);
       
		return new ModelAndView("networkTable","network",net)
				.addObject("clientIPList", list);
	}
	
	
	
	@RequestMapping(value="/admin/gateway")
	public ModelAndView adminGateway(@RequestParam(required=true) int id) {
             
   
		CityGateway  gty=gtyService.getByID(id);
		return new ModelAndView("GtyTable","cityGty",gty);
	}
	
	
			
	@RequestMapping(value="/admin/activeService")
	public ModelAndView activeServic(@RequestParam(required=true) int cityNetworkID) {
		            

			List<Address>  addressList= addressService.getAddressBycityNetworkID(cityNetworkID);
			return new ModelAndView("activeServiceTable","addressList",addressList)
					.addObject("netID", cityNetworkID);
		   
	}
			
	
	
}






















