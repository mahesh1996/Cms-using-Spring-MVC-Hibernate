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

import com.mbz.cms.model.Post;
import com.mbz.cms.services.CategoryService;
import com.mbz.cms.services.PostService;

@Controller
@RequestMapping(value="/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CategoryService catService;
	
	@ModelAttribute("isLoggedIn")
	public boolean isloggedIn(HttpSession session){
		//System.out.println("islogged in called");
		if(session.getAttribute("isLoggedIn")!=null)
			return true;
		else return false;
	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public String listAllPost(Model model,HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		model.addAttribute("posts", this.postService.getAllPost());
		return "admin/post/allpost";
	}
	
	@RequestMapping(value="/single", method = RequestMethod.GET)
	public String singlePost(@RequestParam("postId") int postId, Model model){
		model.addAttribute("post",this.postService.getPost(postId));
		model.addAttribute("comments", this.postService.getComments(postId));
		return "client/post";
	}
	
	@RequestMapping(value="/comments/add", method = RequestMethod.POST)
	public String addComment(@RequestParam("postId") int postId, @RequestParam("commentText") String commentText){
		this.postService.addComment(postId, commentText);
		return "redirect:http://localhost:8080/cms/post/single?postId=" + postId;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addPost(Model model,HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		model.addAttribute("categories",this.catService.getAllCategory());
		return "admin/post/add";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String savePost(Model model,@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("categories") int [] categories,HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		this.postService.addPost(title, description, categories, (String)session.getAttribute("loggedinuser"));
		return "redirect:all";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String editGet(@RequestParam(value="postId") int postId, Model model,HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		Post post = this.postService.getPost(postId);
		model.addAttribute("post",post);
		model.addAttribute("postId",postId);
		model.addAttribute("categories",this.catService.getAllCategory());
		return "admin/post/edit";
	}
	
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String editPost(Model model,@RequestParam("title") String title,@RequestParam("postId") int postId,@RequestParam("description") String description,@RequestParam("categories") int [] categories,HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		this.postService.updatePost(postId,title, description, categories);
		return "redirect:all";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@RequestParam(value="postId") int postId, RedirectAttributes redirectAttributes,HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		this.postService.delete(postId);
		redirectAttributes.addFlashAttribute("status", "Deleted Successfully");
		return "redirect:all";
	}
	
	@RequestMapping(value="/comments", method=RequestMethod.GET)
	//Auto mapping of request param postId based on matching of request parameter name and method argument name
	public String seeComments(@RequestParam int postId, Model model,HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		model.addAttribute("comments", this.postService.getComments(postId));
		model.addAttribute("postId",postId);
		//System.out.println(postId);
		return "admin/post/comments";
	}
	
	@RequestMapping(value="/comments/delete", method=RequestMethod.POST)
	public String deleteComments(@RequestParam(value="commentId") int commentId,@RequestParam(value="postId") int postId, RedirectAttributes redirectAttributes,HttpSession session){
		if(!checkLogin(session))
			return "redirect:http://localhost:8080/cms/login";
		this.postService.deleteComment(commentId);
		redirectAttributes.addAttribute("postId", postId);
		return "redirect:http://localhost:8080/cms/post/comments";
	}
	
	public static boolean checkLogin(HttpSession session){
		if(session.getAttribute("loggedinuser") != null)
			return true;
		else return false;
	}
}
