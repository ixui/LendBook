package jp.co.ixui.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.domain.MstEmp;

@Mapper
public interface MstEmpMapper {

	//社員1名の情報取得
	 MstEmp selectUser(String mail_address);

}
