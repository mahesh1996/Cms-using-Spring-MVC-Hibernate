package com.mbz.cms.model;
import com.mbz.cms.model.Post;
import java.util.Set;
public class User {
	private String username;
	private String password;
	private Set<Post> posts;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
}
