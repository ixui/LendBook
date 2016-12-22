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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	//静的コンテンツに対して除外設定 css/js/imagesを読み込めるようにする
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	}

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

	@Configuration
    static class AuthenticationConfiguration
    extends GlobalAuthenticationConfigurerAdapter {

		@Autowired
		UserDetailsServiceImpl userDetailsService;

        @Bean //パスワードの暗号化方式を宣言
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

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