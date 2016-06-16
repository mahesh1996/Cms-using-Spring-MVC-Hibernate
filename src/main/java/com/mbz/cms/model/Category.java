package com.mbz.cms.model;
import java.util.Set;
public class Category {
	private int categoryId;
	private String text;
	private Set<Post> posts;
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public boolean equals(Object obj){
		
		if((obj instanceof Category) && (this.categoryId == ((Category)obj).getCategoryId())){
			System.out.println("mbz called equals");
				return true;
		}
		else return false;
	}
}