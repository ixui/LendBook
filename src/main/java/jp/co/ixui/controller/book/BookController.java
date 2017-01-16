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

	//書籍登録ページ
	@RequestMapping(value = "/admin/book", method=RequestMethod.GET)
	public ModelAndView bookAdmin(ModelAndView mav,
			@ModelAttribute("form") BookAdminForm form){
		mav.setViewName("book_admin");
		return mav;
	}

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
