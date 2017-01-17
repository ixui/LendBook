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
	 * <p>社員の情報を取得します</p>
	 * @param mail_address 各社員毎に設定したメールアドレス
	 * @return 該当社員の情報を返します。
	 */
	//社員1名の情報取得
	 MstEmp selectUser(String mail_address);

}
