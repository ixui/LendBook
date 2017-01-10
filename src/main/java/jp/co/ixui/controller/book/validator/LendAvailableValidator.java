package jp.co.ixui.controller.book.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.controller.book.validator.annotation.LendAvailable;
import jp.co.ixui.domain.Lend;
import jp.co.ixui.service.BookService;

public class LendAvailableValidator implements ConstraintValidator<LendAvailable, Lend> {

	@Autowired
	BookService service;

	@Override
	public void initialize(LendAvailable constraintAnnotation) {
	}

	@Override
	public boolean isValid(Lend value, ConstraintValidatorContext context) {

		int bookStockId = value.getBookStockId();

		//貸出履歴に該当する蔵書が存在しない(Null)ならtrue
		try{
			Lend lend = service.selectLendHistory(bookStockId);
			String lendHistory = lend.getLendDate();
		}catch(NullPointerException e){
			return true;
		}

		//貸出履歴に返却されていない蔵書が存在しないなら(Null)ならtrue
		try{
			Lend lend = service.selectReturnDateBook(bookStockId);
			String lendReturn = lend.getReturnDate();
		}catch(NullPointerException e){
			return true;
		}

		//それ以外は貸出不可
		return false;
	}

}
