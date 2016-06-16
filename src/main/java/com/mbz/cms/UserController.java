package com.mbz.cms;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mbz.cms.services.UserService;

@Controller
@RequestMapping(value="/admin")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		return "admin/home";
	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public String listAllUser(Model model,HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		model.addAttribute("users",this.userService.getAllUser());
		return "admin/user";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addCategory(@RequestParam(value="username") String username,@RequestParam(value="password") String password, RedirectAttributes redirectAttributes,HttpSession session){
		//System.out.println("categry " + category);
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		this.userService.add(username,password);
		redirectAttributes.addFlashAttribute("status", "Added Successfully");
		return "redirect:all";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteCategory(@RequestParam(value="username") String username, RedirectAttributes redirectAttributes,HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		this.userService.delete(username);
		redirectAttributes.addFlashAttribute("status", "Deleted Successfully");
		return "redirect:all";
	}
	
	@ModelAttribute("isLoggedIn")
	public boolean isloggedIn(HttpSession session){
		//System.out.println("islogged in called");
		if(session.getAttribute("isLoggedIn")!=null)
			return true;
		else return false;
	}
	
	public static boolean checkLogin(HttpSession session){
		if(session.getAttribute("loggedinuser") != null)
			return true;
		else return false;
	}
}
