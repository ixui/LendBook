package jp.co.ixui.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.domain.MstEmp;

/**
 * 社員用マッパー
 * @author NAKAJIMA
 *
 */
@Mapper
public interface MstEmpMapper {

	/**
	 * <p>ユーザの情報を取得します</p>
	 * @param mail_address 各社員毎に設定したメールアドレス
	 * @return 該当社員の情報を返します。
	 */
	 MstEmp getUser(String mail_address);

	 /**
	  * <p>ユーザを新規で登録します。</p>
	  * @param mstEmp 登録するユーザの情報です。
	  */
	 void registerUser(MstEmp mstEmp);
}
