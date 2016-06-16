package com.mbz.cms.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mbz.cms.model.Category;

@Component
public class CategoryService {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void add(String category){
		Category cat = new Category();
		cat.setText(category);
		this.sessionFactory.getCurrentSession().save(cat);
	}
	
	@Transactional
	public void delete(int categoryId){
		Session session = this.sessionFactory.getCurrentSession();
		Category cat = (Category) session.load(Category.class, new Integer(categoryId));
		session.delete(cat);
	}
	
	@Transactional
	public List<Category> getAllCategory(){
		return this.sessionFactory.getCurrentSession().createQuery("from Category").list();
	}
}
