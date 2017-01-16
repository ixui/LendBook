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

	/**
	 * index画面を表示する
	 * /indexと/は同じ画面を表示
	 * @param mav 画面以外に渡す情報なし。
	 * @return index.htmlを渡す。
	 */
	//トップページ
	@RequestMapping(value = {"/", "/index"}, method=RequestMethod.GET)
	public ModelAndView top(
			ModelAndView mav){
		mav.setViewName("index");
		return mav;
	}

	/**
	 * ログイン画面を表示する。
	 * POSTはSpringSecurityを実装したクラスにて行う。
	 * @param mav 画面以外に渡す情報なし。
	 * @return login.htmlを渡す。
	 */
	//ログインページ
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public ModelAndView login(
			ModelAndView mav){
		mav.setViewName("login");
		return mav;
	}

	/**
	 * メイン画面を表示する。
	 * SpringSecurity側のチェックを通過した場合にこの画面に自動的に遷移する。
	 *
	 * 新着書籍/おすすめ書籍を表示させる。
	 * 表示させる書籍数は変数limitを使い設定する。
	 * @param mav 書籍情報を格納
	 * @return main.htmlと格納した情報を渡す。
	 */
	//ログイン後メイン
	@RequestMapping(value = "/main", method=RequestMethod.GET)
	public ModelAndView main(
			ModelAndView mav){
		//新着書籍表示数
		int limit = 4;

		//新着書籍を取得する
		List<MstBook> newbook = bookService.getNewlyBook(limit);

		mav.addObject("newbook", newbook);
		mav.setViewName("main");
		return mav;
	}
}