package com.mbz.cms.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mbz.cms.model.Category;
import com.mbz.cms.model.Comment;
import com.mbz.cms.model.Post;
import com.mbz.cms.model.User;
@Component
public class PostService {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public List<Post> getAllPost(){
		return this.sessionFactory.getCurrentSession().createQuery("from Post").list();
	}
	
	@Transactional
	public void addPost(String title, String description, int []  categories, String username){
		Post post = new Post();
		post.setTitle(title);
		post.setDescription(description);
		Set<Category> cats = new HashSet<Category>();
		for(int id : categories){
			Category cat = this.sessionFactory.getCurrentSession().load(Category.class, id);
			cats.add(cat);
		}
		User user = this.sessionFactory.getCurrentSession().load(User.class, username);
		post.setUser(user);
		post.setCategories(cats);
		this.sessionFactory.getCurrentSession().save(post);
	}
	
	@Transactional
	public void updatePost(int postId,String title, String description, int []  categories){
		Post post = this.sessionFactory.getCurrentSession().get(Post.class, postId);
		post.setTitle(title);
		post.setDescription(description);
		Set<Category> cats = new HashSet<Category>();
		for(int id : categories){
			Category cat = this.sessionFactory.getCurrentSession().load(Category.class, id);
			cats.add(cat);
		}
		User user = new User();
		user.setUsername("mahesh");
		post.setUser(user);
		post.setCategories(cats);
		this.sessionFactory.getCurrentSession().update(post);
	}
	
	@Transactional
	public void delete(int postId){
		Session session = this.sessionFactory.getCurrentSession();
		Post post = (Post) session.load(Post.class, new Integer(postId));
		session.delete(post);
	}
	
	@Transactional
	public Set<Comment> getComments(int postId){
		Post post = (Post) this.sessionFactory.getCurrentSession().get(Post.class, new Integer(postId));
		return post.getComments();
	}
	
	@Transactional
	public void deleteComment(int commentId){
		Session session = this.sessionFactory.getCurrentSession();
		Comment comment = (Comment) session.load(Comment.class, new Integer(commentId));
		session.delete(comment);
	}
	
	@Transactional
	public Post getPost(int postId){
		return this.sessionFactory.getCurrentSession().get(Post.class, postId);
	}
	
	@Transactional
	public void addComment(int postId, String commentText){
		Post post = this.sessionFactory.getCurrentSession().load(Post.class, postId);
		Comment cmnt = new Comment();
		cmnt.setDescription(commentText);
		cmnt.setPost(post);
		this.sessionFactory.getCurrentSession().save(cmnt);
	}
}
