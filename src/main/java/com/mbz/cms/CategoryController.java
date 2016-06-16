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

import com.mbz.cms.services.CategoryService;;
@Controller
@RequestMapping(value="/category")
public class CategoryController {
	
	@Autowired
	private CategoryService catService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public String listAllCategory(Model model,HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		model.addAttribute("categories",this.catService.getAllCategory());
		return "admin/category/allcat";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addCategory(@RequestParam(value="category") String category, RedirectAttributes redirectAttributes,HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		//System.out.println("categry " + category);
		this.catService.add(category);
		redirectAttributes.addFlashAttribute("status", "Added Successfully");
		return "redirect:all";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteCategory(@RequestParam(value="categoryId") int categoryId, RedirectAttributes redirectAttributes,HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		this.catService.delete(categoryId);
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
