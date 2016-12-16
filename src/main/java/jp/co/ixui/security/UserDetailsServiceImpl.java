package jp.co.ixui.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.ixui.LoginUserDetails;
import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.mapper.MstEmpMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	MstEmpMapper mstEmpMapper;


	@Override
	public UserDetails loadUserByUsername(String mail_address) throws UsernameNotFoundException {

		//ログインフォームの値を
		if(mail_address == null || "".equals(mail_address)){
			throw new UsernameNotFoundException("MailAddress is empty");
		}

		//社員1名の情報をmstEmpに格納
		MstEmp mstEmp = mstEmpMapper.selectUser(mail_address);
		if(mstEmp == null){
			throw new UsernameNotFoundException("User not found for name: " + mail_address);
		}

		return new LoginUserDetails(mstEmp);

	}
}
