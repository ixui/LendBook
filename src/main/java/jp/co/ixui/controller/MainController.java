package jp.co.ixui.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ixui.LoginUserDetails;
import jp.co.ixui.controller.book.BookController;
import jp.co.ixui.domain.Lend;
import jp.co.ixui.domain.MstBook;
import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.security.WebSecurityConfig;
import jp.co.ixui.service.BookService;
import jp.co.ixui.service.EmpService;

/**
 * <p><b>メインコントローラ</b></p>
 *
 * <p>インデックス画面、ログイン画面、メイン画面、ユーザに関する画面を表示します。<br>
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
	 * ユーザに関する処理を行うサービスクラス
	 */
	@Autowired
	EmpService empService;

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
	 * ログイン認証は{@link WebSecurityConfig}によって行われます。
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

	/**
	 * <p>ユーザ情報の登録/管理画面を表示します。</p>
	 * @param mav 画面情報
	 * @param userForm 新規ユーザ登録フォームを格納するためのオブジェクト
	 * @return 画面情報を返します。
	 */
	@RequestMapping(value = "/admin/user", method=RequestMethod.GET)
	public ModelAndView user(ModelAndView mav,
			@ModelAttribute("userForm") NewUserRegistrationForm userForm){

		mav.setViewName("user_admin");

		return mav;
	}

	/**
	 * <p>新規ユーザ登録フォームからPOSTした時の画面遷移</p>
	 * フォームの値に問題がなければ、フォームの値を{@link MstEmp}にコピーし、<br>
	 * {@link EmpService#registerUser(MstEmp)}で登録処理を行います。<br>
	 * 処理が行われたらユーザ情報の登録/管理画面へとリダイレクトします。
	 * @param mav 画面情報<br>
	 * エラー発生時はエラー情報を格納します。
	 * @param userForm {@link NewUserRegistrationForm}新規ユーザ登録用のフォーム画面から入力された値が格納されます。
	 * @param result バリデーションエラー発生時にhasErrorsメソッドが実行され、<br>
	 * エラー処理を行います。
	 * @return 正常に処理が行われた後、ユーザ登録/管理ページへとリダイレクトします。<br>
	 * エラー時はエラーメッセージを保持し、ユーザ登録/管理ページをセットし返します。
	 */
	@RequestMapping(value = "/admin/user", method=RequestMethod.POST)
	public ModelAndView registerdBook(
			ModelAndView mav,
			@ModelAttribute("userForm") @Validated NewUserRegistrationForm userForm,
			BindingResult result){

		//エラー処理
		if(result.hasErrors()){
		mav.setViewName("/user_admin");
		return mav;
		}

		MstEmp mstEmp = new MstEmp();
		BeanUtils.copyProperties(userForm, mstEmp);

		empService.registerUser(mstEmp);

		mav.setViewName("redirect:/admin/user");
		return mav;
	}

	@RequestMapping(value = "/user/lend", method=RequestMethod.GET)
	public ModelAndView userLendingInformation(
			ModelAndView mav,
			@AuthenticationPrincipal LoginUserDetails user){

		List<Lend> getList = bookService.getLendingList(user.getUser().getEmpNum());
		List<LendingListForm> lendList = new LendingListForm(getList).getLendList();

		mav.addObject("lendList", lendList);
		mav.setViewName("user");
		return mav;
	}

	@RequestMapping(value = "/user/lend", method=RequestMethod.POST)
	public ModelAndView userReturnBook(
			ModelAndView mav,
			@RequestParam("lendId") int lendId){

		bookService.returnBook(lendId);

		mav.setViewName("redirect:/user/lend");
		return mav;
	}
}