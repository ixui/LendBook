package jp.co.ixui;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DB接続用クラス<br>
 * 環境変数を利用してDBへとアクセス
 * @author NAKAJIMA
 *
 */
@Configuration
public class DataBaseConfig {

	/**
	 * DBのURL,ID,PASSWORDを環境変数を利用してアクセスする。<br>
	 * 変数に、環境変数に入力されているURL,ID,PASSWORDを入れるので、<br>
	 * 事前に環境変数を設定しておく必要があります。<br>
	 * 取得した環境変数をDataSoruceBuilderを用いて、
	 * DBにアクセスする設定をする。
	 * 「@Configuration」を利用しているのでアクセス時にこの設定ファイルが読み込まれる。
	 *
	 * @return DBにアクセスする設定を作成
	 */
	//プロパティの設定を各フィールドにインジェクション
	@Bean
	@ConfigurationProperties("spring.datarsource")
	public DataSource datasource(){
		//環境変数からDB情報取得
		String databaseUrl  = System.getenv("DATABASE_URL");
		String databaseId = System.getenv("DATABASE_ID");
		String databasePassword = System.getenv("DATABASE_PASS");

		//DataSourceBuilder作成
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder
				.create()
				.url(databaseUrl)
				.username(databaseId)
				.password(databasePassword);
		return dataSourceBuilder.build();
	}
}