package jp.co.ixui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * メインコントローラ
 * @author NAKAJIMA
 *
 */
@Controller
public class MainController {

	//トップページ
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView top(
			ModelAndView mav){
		mav.setViewName("index");
		return mav;
	}

	//トップページ
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView index(
			ModelAndView mav){
		mav.setViewName("index");
		return mav;
	}

	//ログイン後メイン
	@RequestMapping(value = "/main", method=RequestMethod.GET)
	public ModelAndView main(
			ModelAndView mav){
		mav.setViewName("main");
		return mav;
	}
}