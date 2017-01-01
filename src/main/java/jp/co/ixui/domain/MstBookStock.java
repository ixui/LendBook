package jp.co.ixui.domain;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MstBookStock {

	//ISBN
	@NotNull
	private String isbn;

	private String ownerEmpNum;

	//登録者
	private String registEmpNum;

	//更新者
	private String updateEmpNum;

	//登録日時
	private String registTime;

	//更新日時
	private String updateTime;
}
