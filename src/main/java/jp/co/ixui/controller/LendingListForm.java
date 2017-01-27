package jp.co.ixui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.domain.Lend;
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

	public LendingListForm(List<Lend> getList){

		List<LendingListForm> lendList = new ArrayList<LendingListForm>();

		for(int i = 0; i < getList.size(); i++){
			Lend lend = getList.get(i);
			String bookName = lend.getBookStock().get(0).getBook().get(0).getBookName();
			String isbn = lend.getBookStock().get(0).getIsbn();
			String returnDueDate = lend.getReturnDueDate();
			int lendId = lend.getLendId();

			lendList.add(new LendingListForm());
			lendList.get(i).setBookName(bookName);
			lendList.get(i).setIsbn(isbn);
			lendList.get(i).setReturnDueDate(returnDueDate);
			lendList.get(i).setLendId(lendId);
		}

		this.lendList = lendList;
	}

	public List<LendingListForm> getLendList(){
		return this.lendList;
	}
}
