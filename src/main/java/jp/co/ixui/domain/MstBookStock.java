package jp.co.ixui.domain;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * 蔵書マスタードメインオブジェクト
 * @author NAKAJIMA
 *
 */
@Getter
@Setter
public class MstBookStock {

	private List<MstBook> book;

	/**
	 * <p>蔵書ID</p>
	 * 蔵書判別用のものでDB側でオートインクリメント設定しているので数値の変更をしません。
	 */
	private int bookStockId;

	/**
	 * <p>String型 ISBN-13を使用します。</p><br>
	 * 例: 123-1234567890<br>
	 */
	@NotNull
	@NotEmpty
	private String isbn;

	/**
	 * <p>所有者番号</p>
	 * <p>本の所有者の社員番号
	 * 変更は原則しません。</p>
	 */
	@NotNull
	private int ownerEmpNum;

	/**
	 * <p>登録者番号</p>
	 */
	@NotNull
	private int registEmpNum;

	/**
	 * <p>更新者番号</p>
	 * DBの更新したアカウントの社員番号が入ります。
	 */
	private int updateEmpNum;

	/**
	 * <p>登録日時</p>
	 * DBに最初に入力された日の日付が入ります。<br>
	 * 一度入力された後は変更しないでください。
	 */
	private String registTime;

	/**
	 * <p>更新日時</p>
	 * 最初に値が入るのは登録された日付です。<br>
	 * それ以降は返却処理が行われるなどのDB上の処理が行われた時に更新されます。
	 */
	private String updateTime;
}
