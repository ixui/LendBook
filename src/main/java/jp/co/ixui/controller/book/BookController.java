package jp.co.ixui.controller.book;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ixui.domain.MstBook;
import jp.co.ixui.service.BookAdminService;
import jp.co.ixui.service.BookDisplayService;

@Controller
public class BookController {

	@Autowired
	BookAdminService bookAdminService;

	@Autowired
	BookDisplayService bookDisplayService;

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

		MstBook mstBook = new MstBook();
		//フォームで取得した値をmstBookへコピー
		BeanUtils.copyProperties(form, mstBook);

		//サービスクラスで処理
		bookAdminService.insertBook(mstBook);

		//リダイレクト
		mav.setViewName("redirect:/admin/book");
		return mav;
	}

	//書籍ページ
	@RequestMapping(value = "/book/{isbn}", method=RequestMethod.GET)
	public ModelAndView book(ModelAndView mav,
			@PathVariable String isbn){

		//ISBNから書籍の情報を取得
		MstBook bookDetail = bookDisplayService.selectBook(isbn);

		//書籍情報
		mav.addObject("bookname", bookDetail.getBookName());
		mav.addObject("author", bookDetail.getAuthor());
		mav.addObject("publisher", bookDetail.getPublisher());
		mav.addObject("publishdate", bookDetail.getPublishDate());
		mav.addObject("isbn", bookDetail.getIsbn());
		mav.addObject("content", bookDetail.getContent());

		mav.setViewName("book");
		return mav;
	}
}
