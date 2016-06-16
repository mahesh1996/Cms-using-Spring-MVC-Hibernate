package com.mbz.cms.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mbz.cms.model.Category;
import com.mbz.cms.model.User;
@Component
public class UserService {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public List<Category> getAllUser(){
		return this.sessionFactory.getCurrentSession().createQuery("from User").list();
	}
	
	@Transactional
	public void add(String username,String password){
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		this.sessionFactory.getCurrentSession().save(user);
	}
	
	@Transactional
	public void delete(String username){
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, username);
		session.delete(user);
	}
	
	@Transactional
	public boolean isValid(String username,String password){
		User user = (User) this.sessionFactory.getCurrentSession().get(User.class, username);
		//System.out.println("user pass" + user.getPassword());
		if( user != null)
		{
			if(password.equals(user.getPassword()))
				return true;
			else return false;
		}
		else return false;
	}
}
