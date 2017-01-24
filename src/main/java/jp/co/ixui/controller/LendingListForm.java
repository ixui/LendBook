package jp.co.ixui.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LendingListForm {

	private int empNum;

	private int ownerEmpNum;

	private String lendDate;

	private String returnDueDate;

	private String bookName;
}
