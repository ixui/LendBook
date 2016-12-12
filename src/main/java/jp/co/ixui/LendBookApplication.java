package jp.co.ixui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("jp.co.ixui/mapper")
@SpringBootApplication
public class LendBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(LendBookApplication.class, args);
	}
}
