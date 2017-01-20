package jp.co.ixui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ixui.controller.book.BookController;
import jp.co.ixui.domain.MstBook;
import jp.co.ixui.security.WebSecurityConfig;
import jp.co.ixui.service.BookService;

/**
 * <p><b>メインコントローラ</b></p>
 *
 * <p>インデックス画面、ログイン画面、メイン画面を表示します。<br>
 * 書籍の処理を行うような画面は{@link BookController}を使用します。</p>
 * @author NAKAJIMA
 */
@Controller
public class MainController {

	/**
	 * 書籍に関する処理を行うサービスクラス
	 */
	@Autowired
	BookService bookService;

	/**
	 * index画面を表示します。<br>
	 * /indexと/は同じ画面を表示します。
	 * @param mav 画面情報
	 * @return 画面情報を渡します。
	 */
	@RequestMapping(value = {"/", "/index"}, method=RequestMethod.GET)
	public ModelAndView top(
			ModelAndView mav){
		mav.setViewName("index");
		return mav;
	}

	/**
	 * ログイン画面を表示します。<br>
	 * POSTはSpringSecurityを実装したクラス{@link WebSecurityConfig}によって行われます。
	 * @param mav 画面情報
	 * @return 画面情報を渡します。
	 */
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public ModelAndView login(
			ModelAndView mav){
		mav.setViewName("login");
		return mav;
	}

	/**
	 * <p>メイン画面を表示します。<br>
	 * SpringSecurity側のチェックを通過した場合にこの画面に自動的に遷移します。</p>
	 *
	 * <p>新着書籍/おすすめ書籍を表示します。<br>
	 * 表示させる書籍数は変数limitを使い設定します。</p>
	 * @param mav 画面情報と書籍情報
	 * @return 画面情報と格納した書籍情報を返します。
	 */
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