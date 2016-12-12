package jp.co.ixui.mapper;

import org.apache.ibatis.annotations.Mapper;
import jp.co.ixui.domain.M_EMP;

@Mapper
public interface UserDateMapper {
	
    M_EMP login(String mail_address);

	
}
