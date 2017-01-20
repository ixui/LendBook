package jp.co.ixui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>SpringBootによるアプリケーションを起動するためのクラスです。<br></p>
 * SpringBooTApplicationアノテーションを使うことで<br>
 * 「@Configuration」と「@EnableAutoConfiguration」と「@ComponentScan」を設定しています。
 *
 * @author NAKAJIMA
 *
 */
@SpringBootApplication
public class LendBookApplication {

	/**
	 * SpringBootアプリケーションのメインメソッド<br>
	 * 起動時に読み込まれます。
	 * @param args 配列を渡しています。
	 */
	public static void main(String[] args) {
		SpringApplication.run(LendBookApplication.class, args);
	}
}