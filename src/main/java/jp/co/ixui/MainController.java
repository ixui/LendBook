package jp.co.ixui;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView top(
			ModelAndView mav){
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView index(
			ModelAndView mav){
		mav.setViewName("index");
		return mav;
	}
	
	
	@RequestMapping(value = "/login", method= RequestMethod.GET)
	public ModelAndView login(
			HttpServletRequest request,
			ModelAndView mav){
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping("/main")
	public String main(){
		return "main";
	}
	
	@RequestMapping("/user")
	public String user(){
		return "user";
	}
	
	@RequestMapping("/lend-history-two")
	public String lend_history_two(){
		return "lend-history-two";
	}
	
	@RequestMapping("/lend-history")
	public String lend_history(){
		return "lend-history";
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
	
	@RequestMapping("/javase8silver")
	public String javase8silver(){
		return "javase8silver";
	}
	
	@RequestMapping("/sukkirijissen")
	public String sukkirijissen(){
		return "sukkirijissen";
	}
	
	@RequestMapping("/sukkirinyumon")
	public String sukkirinyumon(){
		return "sukkirinyumon";
	}
	
	@RequestMapping("/reserve")
	public String reserve(){
		return "reserve";
	}
	
	@RequestMapping("/complete")
	public String complete(){
		return "complete";
	}
}