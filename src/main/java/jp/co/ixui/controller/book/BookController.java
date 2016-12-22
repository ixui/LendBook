package jp.co.ixui.controller.book;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ixui.domain.MstBook;
import jp.co.ixui.service.BookAdminService;

@Controller
public class BookController {

	@Autowired
	BookAdminService bookAdminService;

	//書籍登録ページ
	@RequestMapping(value = "/admin/book", method=RequestMethod.GET)
	public ModelAndView bookAdmin(ModelAndView mav){
		mav.setViewName("book_admin");
		return mav;
	}

	//新規書籍登録
	@RequestMapping(value = "/admin/book", method=RequestMethod.POST)
	public ModelAndView postBookAdmin
		(ModelAndView mav,
		BookAdminForm bookAdminForm){
		MstBook mstBook = new MstBook();
		//フォームで取得した値をmstBookへコピー
		BeanUtils.copyProperties(bookAdminForm, mstBook);

		//サービスクラスで処理
		bookAdminService.insertBook(mstBook);

		//リダイレクト
		mav.setViewName("redirect:/admin/book");
		return mav;
	}
}
