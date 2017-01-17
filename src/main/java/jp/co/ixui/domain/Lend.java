package jp.co.ixui.domain;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import jp.co.ixui.controller.book.validator.annotation.LendAvailable;
import jp.co.ixui.controller.book.validator.annotation.ReturnDueDateOver;
import lombok.Getter;
import lombok.Setter;

/**
 * 貸出用オブジェクト
 * @author NAKAJIMA
 *
 */
@Getter
@Setter
@ReturnDueDateOver
@LendAvailable
public class Lend {

	/**
	 * <p>貸出ID</p>
	 * DB側でオートインクリメント設定しているので数値の変更をしません。
	 */
	//貸出id
	private int lendId;

	/**
	 * <p>蔵書ID</p>
	 * 蔵書判別用のものでDB側でオートインクリメント設定しているので数値の変更をしません。
	 */
	//蔵書id
	private int bookStockId;

	/**
	 * <p>社員番号</p>
	 * 登録時に設定し、変更は可能です。
	 */
	//社員番号
	private int empNum;

	/**
	 * <p>所有者番号</p>
	 * <p>本の所有者の社員番号
	 * 変更は原則しません。</p>
	 */
	//所有者番号
	private int ownerEmpNum;

	/**
	 * <p>貸出日</p>
	 * 貸出をする日を指定
	 * 日付を自動取得するので変更はしません。
	 */
	//貸出日
	private String lendDate;

	/**
	 * <p>返却予定日</p>
	 * yyyy/MM/dd形式以外での入力はエラーがでるようになっています。
	 */
	//返却予定日
	@NotEmpty(message = "返却予定日を入力してください。")
	@Pattern(regexp="[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}", message="出版日はyyyy/mm/ddの形式で入力してください。 (例):2016/7/25")
	private String returnDueDate;

	/**
	 * <p>返却日</p>
	 * デフォルトはnull<br>
	 * DB検索やバリデーションもnullで行っています。<br>
	 * 返却日入力後にnullに変更しないでください。<br>
	 */
	//返却日
	private String returnDate;

	/**
	 * <p>登録者番号</p>
	 */
	//登録者番号
	private int registEmpNum;

	/**
	 * <p>更新者番号</p>
	 * DBの更新したアカウントの社員番号が入ります。
	 */
	//更新者番号
	private int updateEmpNum;

	/**
	 * <p>登録日時</p>
	 * DBに最初に入力された日の日付が入ります。<br>
	 * 一度入力された後は変更しないでください。
	 */
	//登録日時
	private String registTime;

	/**
	 * <p>更新日時</p>
	 * 最初に値が入るのは登録された日付です。<br>
	 * それ以降は返却処理が行われるなどのDB上の処理が行われた時に更新されます。
	 */
	//更新日時
	private String updateTime;
}
