package jp.co.ixui.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ixui.domain.Lend;
import jp.co.ixui.domain.MstBook;
import jp.co.ixui.domain.MstBookStock;
import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.mapper.LendMapper;
import jp.co.ixui.mapper.MstBookMapper;
import jp.co.ixui.mapper.MstBookStockMapper;

/**
 * 書籍用サービスクラス<br><br>
 *
 * コントローラやバリデーションなどでの処理は基本的にこちらで行う。
 * @author NAKAJIMA
 *
 */
@Service
public class BookService {

	@Autowired
	MstBookMapper mstBookMapper;

	@Autowired
	MstBookStockMapper mstBookStockMapper;

	@Autowired
	LendMapper lendMapper;

	/**
	 * 新規で書籍を登録する。
	 * @param mstBook 登録したい書籍の情報
	 * @param mstBookStock 登録したい蔵書の情報
	 */
	//書籍登録
	@Transactional
	public void registerBook(MstBook mstBook, MstBookStock mstBookStock){

		//オブジェクトに入れたものをmapperを使ってINSERT
		mstBookMapper.registerBook(mstBook);
		mstBookStockMapper.registerBookStock(mstBookStock);
	}

	/**
	 * 新規で蔵書を登録する。
	 * @param mstBookStock 登録したい蔵書の情報
	 */
	//蔵書マスターに登録
	public void registerBookStock(MstBookStock mstBookStock){
		mstBookStockMapper.registerBookStock(mstBookStock);
	}

	/**
	 * ISBN-13から指定された蔵書の情報を1冊取得する。<br>
	 * 半角数字、半角ハイフンで記述すること。<br>
	 * <b>例: "123-1234567890"</b><br>
	 * 現在の蔵書は書籍の数とイコールになっているので、
	 * 同ISBNは一つのみとなる。
	 * @param isbn ISBN-13を使う。 ISBN-10と混同しないよう注意
	 * @return ISBNから取得した蔵書情報をMstBookStockに格納する。
	 */
	//蔵書検索
	public MstBookStock getBookStock(String isbn){
		return mstBookStockMapper.getBookStock(isbn);
	}

	/**
	 * 書籍が登録された日付(yyyy/MM/dd)から新しいもの順に取得する。<br>
	 * 取得する冊数は変数limitによって変更する。<br>
	 * 日付で取得しているため、登録日時が同じものがlimit以上存在した場合、<br>
	 * ISBN順で表示される。
	 * @param limit 取得する新着書籍の冊数
	 * @return limitで指定した冊数の新着書籍を取得する
	 */
	//新着書籍取得
	public List<MstBook> getNewlyBook(int limit){
		return mstBookMapper.getNewlyBook(limit);
	}

	/**
	 * ISBN-13から指定された書籍の情報を1冊取得する。<br>
	 * 半角数字、半角ハイフンで記述すること。<br>
	 * <b>例: "123-1234567890"</b><br>
	 * 書籍の同ISBNは存在しないものとする。
	 * @param isbn ISBN-13を使う。 ISBN-10と混同しないよう注意
	 * @return ISBNから取得した書籍情報をMstBookに格納する。
	 */
	//書籍情報
	public MstBook getBook(String isbn){
		return mstBookMapper.getBook(isbn);
	}

	/**
	 * 書籍の貸出処理を行う。<br>
	 * 実際の貸出処理はLendで行う。<br>
	 * Lendに貸出に必要なプロパティを渡すためにMstEmp、ISBNを引数として使っている。
	 * @param lend Lendに情報を格納し最終的な貸出処理を行う。
	 * @param mstEmp 誰が借りたのかを明らかにするために社員番号をlendに渡す。
	 * @param isbn MstBookStockに借りる蔵書の情報を取得する引数として使用
	 */
	//貸出処理
	public void registerLend(Lend lend, MstEmp mstEmp,String isbn){
		lend.setEmpNum(mstEmp.getEmpNum());
		MstBookStock mstBookStcok = getBookStock(isbn);
		lend.setBookStockId(mstBookStcok.getBookStockId());
		lend.setOwnerEmpNum(mstBookStcok.getOwnerEmpNum());
		lendMapper.registerLend(lend);
	}

	/**
	 * 貸出が可能かどうかを蔵書ID(bookStockId)を使い判別する。<br>
	 * 貸出の履歴と返却がされているかをチェックする。<br>
	 * 返却の確認は返却日がNullのものがあれば先に表示し、なければ返却日が最新のものを取得している。<br>
	 * @param bookStockId DBの蔵書マスターと貸出の外部キーである蔵書IDを使用
	 * @return getLendingHistoryを使い、貸出DBに該当する蔵書IDがあるかどうかを判別する。<br>
	 * 一度でも貸し出されていなければ(null)貸し出しが可能となりtrueを返す。<br>
	 * getReturnDateを使い、貸出DBに該当する蔵書IDの返却日を取得する。<br>
	 * 返却日の値が入力されていれば、蔵書は貸出可能な状態であるのでtrueを返す。<br>
	 * 両者に該当しない場合は貸出ができずfalseとなる。
	 */
	//貸出の可否を判別
	public Boolean isLendable(int bookStockId){
		if(lendMapper.getLendingHistory(bookStockId) == null){
			return true;
		}

		if(lendMapper.getReturnDate(bookStockId).getReturnDate() != null){
			return true;
		}

		return false;
	}

	/**
	 * 貸出が可能かどうかをISBNを使い判別する。<br>
	 * ISBNから蔵書IDを取得しisLendableメソッド利用している。<br>
	 * 実際の処理は{@link BookService#isLendable(int)}を参照
	 * @param isbn ISBN-13を使う。 ISBN-10と混同しないよう注意
	 * @return 取得した処理がtrueなら貸出可能、falseなら貸出不能。
	 */
	//ISBNから貸出の可否を判別
	public Boolean isLendableISBN(String isbn){
		MstBookStock mstBookStock = getBookStock(isbn);
		return	isLendable(mstBookStock.getBookStockId());
	}

	/**
	 * 新規に書籍登録を行う時に登録ができるかどうか判別を行う。<br>
	 * ISBN-13から指定されたISBNが既にDB上に登録されているか確認する。
	 * @param isbn ISBN-13を使う。 ISBN-10と混同しないよう注意
	 * @return もし既に登録されていて値が取得できた場合、新規で登録できないようfalseを返す。<br>
	 * DB上にデータが存在せず、nullの場合、登録が可能でありtrueを返す。
	 */
	//ISBNから既に本が登録されているか判別
	public Boolean isBookResgitered(String isbn){
		MstBook mstBook = this.mstBookMapper.getBook(isbn);
		if(mstBook != null){
			return false;
		}
		return true;
	}

	/**
	 * 返却予定日を判別する。<br>
	 * String型で取得した返却予定日(returnDueDate)をDate型(yyyy/MM/dd)に置き換え
	 * 現在の日付との差分を算出する。
	 * 60日以上だった場合は貸出はできない。
	 * @param returnDueDate String型で(yyyy/MM/dd)の形で渡される。
	 * それ以外の値の場合は、Date型に置き換える時に値が異常になる。
	 * @return 空だった場合は、別に処理するためtrueを返す。
	 * 受け取った値が異常だった場合は例外をキャッチしtrueとして別に処理する。
	 * 本日の日付を貸出日とし、返却予定日との日付の差分を算出し
	 * 60日以上だった場合にはfalseを返す。
	 * 上記に該当しない場合trueを返し貸出す。
	 */
	//返却予定日の判別
	public Boolean isReturnDueDateOver(String returnDueDate){

		//空だった場合はここでの判別はしない
		if(returnDueDate == ""){
			return true;
		}

		//現在の日付
		Date today = new Date();

		//フォーマット作成
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		//returnDueDateをDate型に置き換える
		Date retunDueDateTo = null;
			try {
				retunDueDateTo = sdf.parse(returnDueDate);
			} catch (ParseException e) {
				return true; //値が異常な場合はPatternアノテーションで表示する。
			}

		//日付をlong値に変換
		long dateTimeTo = retunDueDateTo.getTime();
		long dateTimeFrom = today.getTime();

		//差分の日付を算出
		long dayDiff = ( dateTimeTo - dateTimeFrom ) / (1000 * 60 * 60 * 24);

		//60日以上の貸出はエラー
		if(dayDiff > 60){
			return false;
		}

		return true;
	}
}