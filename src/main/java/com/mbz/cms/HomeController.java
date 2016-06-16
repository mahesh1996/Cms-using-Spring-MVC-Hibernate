package com.mbz.cms;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mbz.cms.services.PostService;
import com.mbz.cms.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("second", "yes");
		model.addAttribute("posts", this.postService.getAllPost());
		return "client/home";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginGet(){
		return "client/login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:http://localhost:8080/cms/login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginPost(@RequestParam("username") String username, @RequestParam("password") String password,HttpSession session){
		if(this.userService.isValid(username, password))
		{
			session.setAttribute("loggedinuser", username);
			session.setAttribute("isLoggedIn", "true");
			//System.out.println("yes loggedin");
		}
		return "redirect:admin/home";
	}
	
	@ModelAttribute("isLoggedIn")
	public boolean isloggedIn(HttpSession session){
		//System.out.println("islogged in called");
		if(session.getAttribute("isLoggedIn")!=null)
			return true;
		else return false;
	}
	
}
