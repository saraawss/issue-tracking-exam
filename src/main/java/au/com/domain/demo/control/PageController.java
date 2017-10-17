package au.com.domain.demo.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.domain.demo.entity.Issue;

@Controller
public class PageController {
	
	@RequestMapping (value =  "/home" , method = RequestMethod. GET )  
	public String home() {
		return "home";
	}
	
	@RequestMapping (value =  "/createform" , method = RequestMethod. GET )  
	public String createForm() {
		return "createform";
	}
	
	@RequestMapping (value =  "/issue_report" , method = RequestMethod. GET )  
	public String issueReport() {
		return "issue_report";
	}
	
	
	
}
