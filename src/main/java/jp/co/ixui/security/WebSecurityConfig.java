package jp.co.ixui.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SpringSecurity実装クラス
 * @author NAKAJIMA
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	/**
	 * css/js/imagesフォルダをログインしていない状態でも読み込めるように設定。
	 */
	//静的コンテンツに対して除外設定 css/js/imagesを読み込めるようにする
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	}

	/**
	 * アクセス管理メソッド<br>
	 * /index、loginページは全ユーザーアクセス許可<br>
	 * その他のページはログイン後にアクセスを許可する。<br>
	 * また、管理者用のページは管理者権限を持たないアカウントはアクセスはできない。<br><br>
	 *
	 * ログインフォームに入力が必要な情報は<br>
	 * メールアドレスとパスワード<br>
	 */
	//アクセス管理
	@Override
	protected void configure(HttpSecurity http) throws Exception{

		http.authorizeRequests()
				//indexは全ユーザーアクセス許可
				.antMatchers("/", "/index").permitAll()
				//管理者は管理者用ページに遷移できる
				.antMatchers("/admin/**").hasAuthority("1")
				.anyRequest().authenticated();

		//フォーム認証
		//ログイン
		http.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/main" ,true)
				.usernameParameter("mailAddress")
				.passwordParameter("password")
				.permitAll()
				.and();

		//ログアウト設定
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
			.logoutSuccessUrl("/index");
	}

	/**
	 * ログイン処理
	 * GlobalAuthenticationConfigurerAdapterを継承してログイン処理のカスタマイズを行っている。
	 * @author NAKAJIMA
	 *
	 */
	@Configuration
    static class AuthenticationConfiguration
    extends GlobalAuthenticationConfigurerAdapter {

		@Autowired
		UserDetailsServiceImpl userDetailsService;

		/**
		 * パスワードの暗号化
		 * BCryptPasswordEncoderを使用
		 * @return コンストラクタ内にパスワードハッシュ化の強度を指定する。
		 * 指定しない場合はデフォルト値の10が入っている。
		 */
        @Bean //パスワードの暗号化方式を宣言
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        /**
         * ログインユーザの認証処理を行う。
         * UserDetailsServiceを使いユーザ情報を取得する。
         * passwordEncoderを使い入力されたパスワードをハッシュ化し認証を行う。
         */
		@Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // 認証するユーザーを設定する
            auth
            	.userDetailsService(userDetailsService)

           // 入力値をbcryptでハッシュ化した値でパスワード認証を行う
            	.passwordEncoder(passwordEncoder());
        }
    }
}