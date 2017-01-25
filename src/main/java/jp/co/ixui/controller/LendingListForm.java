package jp.co.ixui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.domain.Lend;
import jp.co.ixui.domain.MstBook;
import jp.co.ixui.mapper.LendMapper;
import jp.co.ixui.mapper.MstBookMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LendingListForm {

	@Autowired
	LendMapper lendMapper;

	@Autowired
	MstBookMapper mstBookMapper;

	private List<LendingListForm> lendList;

	private int empNum;

	private int ownerEmpNum;

	private String lendDate;

	private String returnDueDate;

	private String bookName;

	private String isbn;

	private int lendId;

	public LendingListForm(){}

	public LendingListForm(List<MstBook> lendBookList, List<Lend> lendReturnDueDateList){

		List<LendingListForm> lendList = new ArrayList();
		for(int i = 0; i < lendBookList.size(); i++){
		lendList.add(new LendingListForm());
		lendList.get(i).setBookName(lendBookList.get(i).getBookName());
		lendList.get(i).setIsbn(lendBookList.get(i).getIsbn());
		lendList.get(i).setReturnDueDate(lendReturnDueDateList.get(i).getReturnDueDate());
		lendList.get(i).setLendId(lendReturnDueDateList.get(i).getLendId());

		this.lendList = lendList;
		}
	}

	public List<LendingListForm> getLendingListForm(){
		return this.lendList;
	}
}
