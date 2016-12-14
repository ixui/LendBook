package jp.co.ixui;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseConfig {

	@Autowired
	DataSourceProperties properties;

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
				.create(this.properties.getClassLoader())
				.url(databaseUrl)
				.username(databaseId)
				.password(databasePassword);
		return dataSourceBuilder.build();
	}
}