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

@Service
public class BookService {

	@Autowired
	MstBookMapper mstBookMapper;

	@Autowired
	MstBookStockMapper mstBookStockMapper;

	@Autowired
	LendMapper lendMapper;

	//書籍登録
	@Transactional
	public void registerBook(MstBook mstBook, MstBookStock mstBookStock){

		//オブジェクトに入れたものをmapperを使ってINSERT
		mstBookMapper.registerBook(mstBook);
		mstBookStockMapper.registerBookStock(mstBookStock);
	}

	//蔵書マスターに登録
	public void registerBookStock(MstBookStock mstBookStock){
		mstBookStockMapper.registerBookStock(mstBookStock);
	}

	//蔵書検索
	public MstBookStock getBookStock(String isbn){
		return mstBookStockMapper.getBookStock(isbn);
	}

	//新着書籍取得
	public List<MstBook> getNewlyBook(int limit){
		return mstBookMapper.getNewlyBook(limit);
	}

	//書籍情報
	public MstBook getBook(String isbn){
		return mstBookMapper.getBook(isbn);
	}

	//貸出処理
	public void registerLend(Lend lend, MstEmp mstEmp,String isbn){
		lend.setEmpNum(mstEmp.getEmpNum());
		MstBookStock mstBookStcok = getBookStock(isbn);
		lend.setBookStockId(mstBookStcok.getBookStockId());
		lend.setOwnerEmpNum(mstBookStcok.getOwnerEmpNum());
		lendMapper.registerLend(lend);
	}

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

	//ISBNから貸出の可否を判別
	public Boolean isLendableISBN(String isbn){
		MstBookStock mstBookStock = getBookStock(isbn);
		return	isLendable(mstBookStock.getBookStockId());
	}

	//ISBNから既に本が登録されているか判別
	public Boolean isBookResgitered(String isbn){
		MstBook mstBook = this.mstBookMapper.getBook(isbn);
		if(mstBook != null){
			return false;
		}
		return true;
	}

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