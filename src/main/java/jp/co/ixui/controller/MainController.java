package jp.co.ixui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ixui.domain.MstBook;
import jp.co.ixui.service.BookService;

/**
 * メインコントローラ
 * @author NAKAJIMA
 *
 */
@Controller
public class MainController {

	@Autowired
	BookService bookService;

	//トップページ
	@RequestMapping(value = {"/", "/index"}, method=RequestMethod.GET)
	public ModelAndView top(
			ModelAndView mav){
		mav.setViewName("index");
		return mav;
	}

	//ログインページ
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public ModelAndView login(
			ModelAndView mav){
		mav.setViewName("login");
		return mav;
	}

	//ログイン後メイン
	@RequestMapping(value = "/main", method=RequestMethod.GET)
	public ModelAndView main(
			ModelAndView mav){
		//新着書籍表示数
		int limit = 4;

		//新着書籍を取得する
		List<MstBook> newbook = bookService.selectNewBook(limit);

		mav.addObject("newbook", newbook);
		mav.setViewName("main");
		return mav;
	}
}