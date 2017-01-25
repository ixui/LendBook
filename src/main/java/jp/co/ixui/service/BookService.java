package jp.co.ixui.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ixui.LoginUserDetails;
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
 * 書籍の登録や貸出の処理を行います。
 * @author NAKAJIMA
 */
@Service
public class BookService {

	/**
	 * 書籍マッパー
	 */
	@Autowired
	MstBookMapper mstBookMapper;

	/**
	 * 蔵書マッパー
	 */
	@Autowired
	MstBookStockMapper mstBookStockMapper;

	/**
	 * 貸出マッパー
	 */
	@Autowired
	LendMapper lendMapper;

	/**
	 * <p>新規で書籍を登録します。</p>
	 * <b>蔵書登録ページがないので、書籍の登録時に同時に蔵書を登録しています。</b>
	 * @param mstBook 登録したい書籍の情報
	 * @param mstBookStock 登録したい蔵書の情報
	 * @param user 登録するユーザの情報
	 */
	@Transactional
	public void registerBook(MstBook mstBook, MstBookStock mstBookStock, LoginUserDetails user){

		//社員番号を格納
		mstBook.setRegistEmpNum(user.getUser().getEmpNum());
		mstBookStock.setRegistEmpNum(user.getUser().getEmpNum());

		//オブジェクトに入れたものをmapperを使ってINSERT
		mstBookMapper.registerBook(mstBook);
		mstBookStockMapper.registerBookStock(mstBookStock);
	}

	/**
	 * 新規で蔵書を登録します。
	 * @param mstBookStock 登録したい蔵書の情報
	 */
	public void registerBookStock(MstBookStock mstBookStock){
		mstBookStockMapper.registerBookStock(mstBookStock);
	}

	/**
	 * <p>ISBN-13から指定された蔵書の情報を1冊取得します。</p>
	 * <p>半角数字、半角ハイフンで記述してください。<br>
	 * <b>例: "123-1234567890"</b></p>
	 * <p>現在の蔵書は書籍の数とイコールになっているので、
	 * 同ISBNは一つだけになります。</p>
	 * @param isbn ISBN-13を使います<br>
	 * ISBN-10と混同しないよう注意してください。
	 * @return ISBNから取得した蔵書情報をMstBookStockに返します。
	 */
	public MstBookStock getBookStock(String isbn){
		return mstBookStockMapper.getBookStock(isbn);
	}

	/**
	 * <p>書籍が登録された日付(yyyy/MM/dd)から新しいもの順に取得します。</p>
	 * <p>取得する冊数は変数limitによって変更します。</p>
	 * <p>日付で取得しているので、登録日時が同じものがlimit以上に存在した時、<br>
	 * ISBN順で表示されます。</p>
	 * @param limit 取得する新着書籍の冊数
	 * @return limitで指定した冊数の新着書籍を返します。
	 */
	public List<MstBook> getNewlyBook(int limit){
		return mstBookMapper.getNewlyBook(limit);
	}

	/**
	 * <p>ISBN-13から指定された書籍の情報を1冊取得する。</p>
	 * <p>半角数字、半角ハイフンで記述してください。<br>
	 * <b>例: "123-1234567890"</b><br>
	 * 書籍の同ISBNは存在しません。</p>
	 * @param isbn ISBN-13を使います。<br>
	 * ISBN-10と混同しないよう注意してください。
	 * @return ISBNから取得した書籍情報をMstBookに返します。
	 */
	public MstBook getBook(String isbn){
		return mstBookMapper.getBook(isbn);
	}

	/**
	 * <p>書籍の貸出処理を行います。<br>
	 * 実際の貸出処理はLendで行います。</p>
	 * Lendに貸出に必要なプロパティを渡すためにMstEmp、ISBNを引数として使っています。
	 * @param lend Lendに情報を格納し最終的な貸出処理を行います。
	 * @param mstEmp 誰が借りたのかを明らかにするために社員番号をlendに渡します。
	 * @param isbn MstBookStockに借りる蔵書の情報を取得する引数として使用しています。
	 */
	@Transactional
	public void registerLend(Lend lend, MstEmp mstEmp,String isbn){

		//Lendに貸出処理に必要な値を渡す
		lend.setEmpNum(mstEmp.getEmpNum());
		MstBookStock mstBookStcok = getBookStock(isbn);
		lend.setBookStockId(mstBookStcok.getBookStockId());
		lend.setOwnerEmpNum(mstBookStcok.getOwnerEmpNum());

		lendMapper.registerLend(lend);
	}

	/**
	 * <p>貸出が可能かどうかを蔵書ID(bookStockId)を使い判別します。</p>
	 * <p>貸出の履歴と返却がされているかをチェックします。<br>
	 * 返却の確認は返却日がNullのものがあれば先に表示し、なければ返却日が最新のものを取得しています。</p>
	 * @param bookStockId DBの蔵書マスターと貸出の外部キーである蔵書IDを使用します。
	 * @return getLendingHistoryを使い、貸出DBに該当する蔵書IDがあるかどうかを確認します。<br>
	 * 一度でも貸し出されていなければ(nullであれば)貸出が可能でありtrueを返します。<br>
	 * getReturnDateを使い、貸出DBに該当する蔵書IDの返却日を取得します。<br>
	 * 返却日の値が入力されていれば、蔵書は貸出が可能でありtrueを返します。<br>
	 * 両者に該当しない場合は貸出ができずfalseを返します。
	 */
	@Transactional
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
	 * <p>貸出が可能かどうかをISBNを使い判別します。</p>
	 * <p>ISBNから蔵書IDを取得しisLendableメソッド利用しています。<br>
	 * 実際の処理は{@link BookService#isLendable(int)}を参照してください。</p>
	 * @param isbn ISBN-13を使。 ISBN-10と混同しないよう注意
	 * @return 取得した処理がtrueなら貸出可能、falseなら貸出不能。
	 */
	@Transactional
	public Boolean isLendableISBN(String isbn){
		MstBookStock mstBookStock = getBookStock(isbn);
		return	isLendable(mstBookStock.getBookStockId());
	}

	/**
	 * 新規に書籍登録を行う時に登録ができるかどうか判別を行う。<br>
	 * ISBN-13から指定されたISBNが既にDB上に登録されているか確認する。
	 * @param isbn ISBN-13を使います。<br>
	 * ISBN-10と混同しないよう注意してください。
	 * @return もし既に登録されていて値が取得できた時、<br>
	 * 新規で登録できないようfalseを返します。<br>
	 * DB上にデータが存在せず、nullの時は登録が可能でありtrueを返します。
	 */
	@Transactional
	public Boolean isBookRegistered(String isbn){

		MstBook mstBook = this.mstBookMapper.getBook(isbn);

		if(mstBook != null){
			return false;
		}

		return true;
	}

	/**
	 * <p>返却予定日を判別します。</p>
	 * <p>String型で取得した返却予定日(returnDueDate)をDate型(yyyy/MM/dd)に置き換え<br>
	 * 現在の日付との差分を算出します。<br>
	 * 60日以上だった場合は貸出はできません。</p>
	 * @param returnDueDate String型で(yyyy/MM/dd)の形で渡されます。
	 * それ以外の値の場合は、Date型に置き換える時に値が異常になります。
	 * @return 空だった場合は、別の処理をするためtrueを返します。<br>
	 * 受け取った値が異常だった場合は例外をキャッチしtrueとして別に処理をします。<br>
	 * 本日の日付を貸出日とし、返却予定日との日付の差分を算出し、<br>
	 * 60日以上だった場合にはfalseを返します。<br>
	 * 上記に該当しない場合trueを返し貸出します。
	 */
	@Transactional
	public Boolean isReturnDueDateOver(String returnDueDate){

		//空だった場合はここでの判別はしない
		if(returnDueDate == "" || returnDueDate == null){
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

	public List<Lend> getLendingList(int empNum){
		List<Lend> lendingList = lendMapper.getLendingList(empNum);
		return lendingList;
	}

	public List<MstBook> getLendingBookList(int empNum){
		List<MstBook> lendingBookList = mstBookMapper.getLendingBookList(empNum);
		return lendingBookList;
	}

	public void returnBook(int lendId){
		lendMapper.returnBook(lendId);
	}

}