package jp.co.ixui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>SpringBootによるアプリケーションを起動するためのクラスです。<br></p>
 * SpringBooTApplicationアノテーションを使うことで<br>
 * 「@Configuration」と「@EnableAutoConfiguration」と「@ComponentScan」を設定しています。
 *
 * @author NAKAJIMA
 *
 */
@SpringBootApplication
public class LendBookApplication extends WebMvcConfigurerAdapter{

	@Autowired
	private MessageSource messageSource;

	/**
	 * SpringBootアプリケーションのメインメソッド<br>
	 * 起動時に読み込まれます。
	 * @param args 配列を渡しています。
	 */
	public static void main(String[] args) {
		SpringApplication.run(LendBookApplication.class, args);
	}

	/**
	 * <p>バリデーションの設定メソッド</p>
	 * setValidationMessageSourceを用いてカスタムメッセージソースを取得
	 * @return
	 */
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        return localValidatorFactoryBean;
    }

    /**
     * WebMvcConfigurerAdapterのオーバーライド<br>
     * デフォルトで作成されたバリデータの代わりに設定したバリデータを取得
     * @return バリデーションの設定を返す
     *
     */
    @Override
    public org.springframework.validation.Validator getValidator() {
        return validator();
    }
}