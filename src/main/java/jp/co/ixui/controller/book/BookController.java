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

import jp.co.ixui.LoginUserDetails;
import jp.co.ixui.domain.Lend;
import jp.co.ixui.domain.MstBook;
import jp.co.ixui.domain.MstBookStock;
import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.service.BookService;

/**
 * <p><b>書籍ページ用コントローラ</b></p>
 *
 * <p>ログイン後の画面遷移を行います。</p><br>
 * <p>セッションスコープに書籍情報を格納しています。</p>
 * @author NAKAJIMA
 *
 */
@Controller
@SessionAttributes(value = "bookDetail")
public class BookController {

	/**
	 * 書籍に関する処理を行うサービスクラス
	 */
	@Autowired
	BookService bookService;

	/**
	 * <p>書籍登録画面</p>
	 *
	 * <p>{@link BookAdminForm}のクラスを初期化します。</p>
	 * @param mav 画面情報
	 * @param form 書籍登録フォームを格納するためのオブジェクト
	 * @return 画面情報を返します。
	 */
	//書籍登録ページ
	@RequestMapping(value = "/admin/book", method=RequestMethod.GET)
	public ModelAndView registerBook	(ModelAndView mav,
			@ModelAttribute("form") BookAdminForm form){
		mav.setViewName("book_admin");
		return mav;
	}

	/**
	 * <p>書籍フォームからPOSTした時の画面遷移</p>
	 * <p>フォームの値をチェックし、<br>
	 * 問題が無ければ入力された{@link BookAdminForm}の値を<br>
	 * 書籍{@link MstBook}、蔵書{@link MstBookStock}にコピーし<br>
	 * サービスクラスに渡して登録処理を行います。</p>
	 * 処理後書籍登録ページ{@link BookController#registerBook(ModelAndView, BookAdminForm)}へとリダイレクトする。
	 * @param mav 画面情報<br>
	 * エラー発生時はエラー情報を格納します。
	 * @param form {@link BookAdminForm}書籍登録用のフォーム 画面から入力された値が格納されます。
	 * @param result バリデーションエラー発生時にhasErrorsメソッドが実行され、<br>
	 * エラー処理を行います。
	 * @param user 書籍登録を行うユーザの情報
	 * @return 正常に処理が行われた後、書籍登録ページへとリダイレクトします。<br>
	 * エラー時はエラーメッセージを保持し、書籍登録ページをセットし返します。
	 */
	//新規書籍登録
	@RequestMapping(value = "/admin/book", method=RequestMethod.POST)
	public ModelAndView registeredBook
		(ModelAndView mav,
				@ModelAttribute("form") @Validated BookAdminForm form,
				BindingResult result,
				@AuthenticationPrincipal LoginUserDetails user){

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
		bookService.registerBook(mstBook, mstBookStock, user);

		//リダイレクト
		mav.setViewName("redirect:/admin/book");
		return mav;
	}

	/**
	 * <p>個別書籍画面</p>
	 *
	 * <p>ISBNから該当書籍を検索し、DB上の値を{@link MstBook}に格納します。<br>
	 * {@link MstBook}に入った値とISBNから貸出可否を判別した結果を<br>
	 * {@link BookAdminForm}にコピーし、インスタンスを渡してあげます。<p>
	 * @param mav 書籍情報と画面情報
	 * @param isbn ISBN-13 書籍用個別URLと書籍検索用
	 * @return 格納された書籍情報と画面を返します。
	 */
	//書籍ページ
	@RequestMapping(value = "/book/{isbn}", method=RequestMethod.GET)
	public ModelAndView bookDetail(ModelAndView mav,
			@PathVariable String isbn){

		MstBook MstBookDetail = bookService.getBook(isbn); //ISBNから書籍の情報を取得

		//取得した情報を格納したオブジェクトを作成
		BookAdminForm bookDetail = new BookAdminForm();
		bookDetail.setIsLendable(bookService.isLendableISBN(isbn));
		BeanUtils.copyProperties(MstBookDetail, bookDetail);

		//書籍情報
		mav.addObject("bookDetail", bookDetail);
		mav.setViewName("book");

		return mav;
	}

	/**
	 * <p>貸出用画面</p>
	 *
	 * <p>ISBNから書籍の情報と蔵書の情報を取得し<br>
	 * それぞれ書籍{@link MstBook}、蔵書{@link MstBookStock}に格納します。<br>
	 * 蔵書の情報を{@link Lend}にコピーし書籍{@link MstBook}と蔵書{@link MstBookStock}のインスタンスを<br>
	 * 渡してあげます。</p>
	 * @param mav 書籍情報/貸出情報/画面情報
	 * @param isbn ISBN-13 貸出用個別URLと書籍検索用
	 * @param lend 貸出フォーム 貸出蔵書IDを格納
	 * @return 格納された書籍情報と貸出情報、画面情報を返します。
	 */
	//貸出ページ
	@RequestMapping(value = "/reserve/{isbn}", method=RequestMethod.GET)
	public ModelAndView lend(ModelAndView mav,
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

	/**
	 * <p>貸出完了画面</p>
	 *
	 * <p>POSTされた内容に問題がなければ貸出処理を行い、<br>
	 * 貸出完了ページへと遷移します。</p>
	 * @param mav 画面情報
	 * @param isbn ISBN-13 個別URLと書籍検索用
	 * @param lend 貸出フォーム<br>
	 *  バリデーションチェックを行います。
	 * @param result バリデーションエラー発生時にhasErrorsメソッドが実行され、<br>
	 * エラー処理を行います。
	 * @param user ログインしているユーザの情報
	 * @return 画面情報を渡します。
	 */
	//貸出完了
	@RequestMapping(value = "/reserve/{isbn}", method=RequestMethod.POST)
	public ModelAndView lendComplete(ModelAndView mav,
			@PathVariable("isbn") String isbn,
			@ModelAttribute("lend") @Validated Lend lend,
			BindingResult result,
			@AuthenticationPrincipal LoginUserDetails user){

		//エラー処理
		if(result.hasErrors()){
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
