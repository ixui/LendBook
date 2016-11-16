package jp.co.ixui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
