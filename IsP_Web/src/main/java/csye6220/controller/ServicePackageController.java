package csye6220.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import csye6220.model.ServicePackage;
import csye6220.service.ServicePackageService;


@Controller
public class ServicePackageController {

	
	@Autowired
	ServicePackageService packageService;
	
	@RequestMapping(value="/servicePackage")
	public ModelAndView getServicePackage(@RequestParam(required=true) int packageID ) {
             
		try
		{
		ServicePackage  servicePackage= packageService.findPackageByID(packageID);
		return new ModelAndView("servicePackageTable","servicePackage",servicePackage);
		}
		catch (Exception e)
		{
			return new ModelAndView("failPage","errorMessage",e.toString());
		}
		
	}
	
	
	@RequestMapping(value="admin/servicePackage")
	public ModelAndView adminServicePackagePage() {
            
		return new ModelAndView("adminServicePackagePage");
		
	}
	
	
	
	@RequestMapping(value="admin/servicePackage/update")
	public ModelAndView adminUpdateServicePackage(@RequestParam(required=true) int packageID) {
            
	     ServicePackage servicePackage=packageService.findPackageByID(packageID);
		
		return new ModelAndView("adminUpdateServicePackage","servicePackage",servicePackage);
		
	}
	
	
	
	
	
	
	
}
