package jp.co.ixui.controller.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

	@RequestMapping(value = "/book_admin", method=RequestMethod.GET)
	public ModelAndView book_admin(ModelAndView mav){
		mav.setViewName("book_admin");
		return mav;
	}
}
