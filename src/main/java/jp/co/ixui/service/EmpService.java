package jp.co.ixui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.mapper.MstEmpMapper;

/**
 * <p>社員用サービスクラス</p>
 *
 * 社員情報の登録や変更等の処理を行います。
 * @author NAKAJIMA
 *
 */
@Service
public class EmpService {

	/**
	 * 社員マッパー
	 */
	@Autowired
	MstEmpMapper mstEmpMapper;

	/**
	 * メールアドレスから社員情報を取得します。
	 * @param mailAddress 登録済みメールアドレス
	 * @return 該当社員を取得し返します。
	 */
	public MstEmp getUser(String mailAddress){
		return mstEmpMapper.getUser(mailAddress);
	}
}