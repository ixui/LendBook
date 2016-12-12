package jp.co.ixui.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.domain.MstEmp;

@Mapper
public interface MstEmpMapper {

/*	@Select("select * from m_emp where mail_address = #{mail_address}")
	MstEmp selectUser(String mail_address);*/

	//社員1名の情報取得
	 MstEmp selectUser(String mail_address);

}
