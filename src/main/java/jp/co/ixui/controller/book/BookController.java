package jp.co.ixui.controller.book;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ixui.LoginUserDetails;
import jp.co.ixui.domain.Lend;
import jp.co.ixui.domain.MstBook;
import jp.co.ixui.domain.MstBookStock;
import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.service.BookService;

/**
 * <b>書籍ページ用コントローラ</b><br><br>
 *
 * ログイン後の基本的な画面遷移を行う。<br>
 * @author NAKAJIMA
 *
 */
@Controller
@SessionAttributes(value = "bookDetail")
public class BookController {

	@Autowired
	BookService bookService;

	/**
	 * 書籍登録画面<br>
	 * 管理者ユーザ以外アクセスは不可<br>
	 * @param mav 画面以外に渡す情報はなし。
	 * @param form 書籍登録フォームを格納するためのオブジェクト
	 * @return book_adminを渡す。
	 */
	//書籍登録ページ
	@RequestMapping(value = "/admin/book", method=RequestMethod.GET)
	public ModelAndView bookAdmin(ModelAndView mav,
			@ModelAttribute("form") BookAdminForm form){
		mav.setViewName("book_admin");
		return mav;
	}

	/**
	 * 書籍フォームからPOSTした時の画面遷移<br>
	 * フォームの値をチェックし、<br>
	 * 問題が無ければ入力されたフォームの値を蔵書、書籍にコピーし<br>
	 * サービスクラスに渡して登録処理を行う。<br>
	 * 処理後書籍登録ページへとリダイレクトする。
	 * @param mav 画面とエラー発生時はエラー情報を渡す
	 * @param form 書籍登録用のフォーム 画面から入力された時に値が格納される。
	 * @param result バリデーションエラー発生時にhasErrorsメソッドが実行され、<br>
	 * エラー処理を行う
	 * @return 正常に処理が行われた後、書籍登録ページへとリダイレクトする。<br>
	 * エラー時はエラーメッセージを保持し、書籍登録ページへと遷移する。
	 */
	//新規書籍登録
	@RequestMapping(value = "/admin/book", method=RequestMethod.POST)
	public ModelAndView postBookAdmin
		(ModelAndView mav,
		@ModelAttribute("form") @Validated BookAdminForm form,
		BindingResult result){

		//エラー処理
		if(result.hasErrors()){
		mav.setViewName("/book_admin");
		return mav;
		}

		//書籍、蔵書オブジェクト作成
		MstBook mstBook = new MstBook();
		MstBookStock mstBookStock = new MstBookStock();

		//フォームの値を書籍、蔵書にコピー
		BeanUtils.copyProperties(form, mstBook);
		BeanUtils.copyProperties(form, mstBookStock);

		//書籍、蔵書に登録
		bookService.registerBook(mstBook, mstBookStock);

		//リダイレクト
		mav.setViewName("redirect:/admin/book");
		return mav;
	}

	/**
	 * 個別書籍画面<br>
	 * ISBNから該当書籍を検索し、DB上の値を{@link MstBook}に格納する。
	 * {@link MstBook}に入った値とISBNから貸出可否を判別した結果を
	 * {@link BookAdminForm}にコピーし、インスタンスを渡してあげる。
	 * @param mav 書籍情報と画面book.htmlを渡す。
	 * @param isbn 書籍用個別URLと書籍検索用
	 * @return 格納された書籍情報と画面を表示する。
	 */
	//書籍ページ
	@RequestMapping(value = "/book/{isbn}", method=RequestMethod.GET)
	public ModelAndView book(ModelAndView mav,
			@PathVariable String isbn){

		MstBook bookDetails = bookService.getBook(isbn); //ISBNから書籍の情報を取得

		//取得した情報を格納したオブジェクトを作成
		BookAdminForm bookDetail = new BookAdminForm();
		bookDetail.setIsLendable(bookService.isLendableISBN(isbn));
		BeanUtils.copyProperties(bookDetails, bookDetail);

		//書籍情報
		mav.addObject("bookDetail", bookDetail);
		mav.setViewName("book");

		return mav;
	}

	/**
	 * 貸出用画面
	 * @param mav
	 * @param isbn
	 * @param lend
	 * @return
	 */
	//貸出ページ
	@RequestMapping(value = "/reserve/{isbn}", method=RequestMethod.GET)
	public ModelAndView reserve(ModelAndView mav,
			@PathVariable String isbn,
			@ModelAttribute("lend") Lend lend){

		//ISBNから書籍の情報を取得
		MstBook bookDetail = bookService.getBook(isbn);

		//ISBNから蔵書の情報を取得
		MstBookStock mstBookStock = bookService.getBookStock(isbn); //蔵書が複数になったときに修正が必要
		BeanUtils.copyProperties(mstBookStock, lend);

		//書籍情報
		mav.addObject("bookDetail", bookDetail);
		mav.addObject("lend", lend);

		mav.setViewName("reserve");

		return mav;
	}

	//貸出完了
	@RequestMapping(value = "/reserve/{isbn}", method=RequestMethod.POST)
	public ModelAndView lendComplete(ModelAndView mav,
			@PathVariable("isbn") String isbn,
			@ModelAttribute("lend") @Validated Lend lend,
			BindingResult result,
			@AuthenticationPrincipal LoginUserDetails user,
			RedirectAttributes redirectAttributes){

		//エラー処理
		if(result.hasErrors()){
		mav.addObject(isbn);
		mav.setViewName("/reserve");
		return mav;
		}

		//ログインユーザ情報
		MstEmp mstEmp = new MstEmp();
		BeanUtils.copyProperties(user.getUser(), mstEmp);

		//貸出処理
		bookService.registerLend(lend, mstEmp,isbn);

		mav.setViewName("complete");
		return mav;
	}
}
