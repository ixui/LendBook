package jp.co.ixui.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ixui.service.BookAdminService;

@Controller
public class BookController {

	@Autowired
	BookAdminService bookAdminService;

	//書籍登録ページ
	@RequestMapping(value = "/book_admin", method=RequestMethod.GET)
	public ModelAndView bookAdmin(ModelAndView mav){
		mav.setViewName("book_admin");
		return mav;
	}

	//新規書籍登録
	@RequestMapping(value = "/book_admin", method=RequestMethod.POST)
	public ModelAndView postBookAdmin
		(ModelAndView mav,
		BookAdminForm bookAdminForm){

		//フォームから受け取った値をサービスクラスで処理
		bookAdminService.insertBook(bookAdminForm);

		//リダイレクト
		mav.setViewName("redirect:/book_admin");
		return mav;
	}
}
