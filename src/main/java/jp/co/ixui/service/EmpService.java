package jp.co.ixui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	public MstEmp getUser(int empNum){
		return mstEmpMapper.getUser(empNum);
	}

	/**
	 * <p>新規でユーザの登録を行います</p>
	 * @param mstEmp 登録するユーザの情報
	 */
	public void registerUser(MstEmp mstEmp){
		String password = passwordEncoder(mstEmp.getPassword());
		mstEmp.setPassword(password);
		mstEmpMapper.registerUser(mstEmp);
	}

	public String passwordEncoder(String password){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodePassword = encoder.encode(password);

		return encodePassword;
	}

	public Boolean isUserRegistered(int empNum){

		//空だった場合はここでの判別はしない
		String stringNum = String.valueOf(empNum);
		if(stringNum == null || stringNum == ""){
			return true;
		}

		MstEmp user = getUser(empNum);
		if(user == null){
			return true;
		}

		return false;
	}
}