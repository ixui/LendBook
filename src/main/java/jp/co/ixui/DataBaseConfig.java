package jp.co.ixui;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DB接続用クラスです。<br>
 * 環境変数を利用してDBへとアクセスします。
 * @author NAKAJIMA
 *
 */
@Configuration
public class DataBaseConfig {

	/**
	 * <p>DBのURL,ID,PASSWORDを環境変数を利用してアクセスします。<br>
	 * 変数に、環境変数に入力されているURL,ID,PASSWORDを入れるので、<br>
	 * 事前に環境変数を設定しておく必要があります。<br>
	 * 取得する変数は<br>
	 * 1.DATABASE_URL<br>
	 * 2.DATABASE_ID<br>
	 * 3.DATABASE_PASS<br>
	 * になります。<br></p>
	 * <p>取得した環境変数をDataSoruceBuilderを用いて、<br>
	 * DBにアクセスする設定をします。<br></p>
	 * 「@Configuration」を利用しているのでアクセス時にこの設定ファイルが読み込まれます。
	 *
	 * @return DBにアクセスする設定を返します。
	 */
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