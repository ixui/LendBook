package com.kohei.nakajima;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String index(){
	return "index";
	}
	
	@RequestMapping("/index")
	public String index_two(){
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/main")
	public String main(){
		return "main";
	}
	
	@RequestMapping("/user")
	public String user(){
		return "user";
	}
	
	@RequestMapping("/user_change")
	public String user_change(){
		return "user_change";
	}
	
	@RequestMapping("/book_admin")
	public String book_admin(){
		return "book_admin";
	}
	
	@RequestMapping("/user_admin")
	public String user_admin(){
		return "user_admin";
	}
	
	@RequestMapping("/search")
	public String search(){
		return "search";
	}
	
	@RequestMapping("/search-two")
	public String search_two(){
		return "search-two";
	}
	
	@RequestMapping("/effective_java")
	public String effective_java(){
		return "effective_java";
	}
}
