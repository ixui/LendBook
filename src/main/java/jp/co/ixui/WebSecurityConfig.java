package jp.co.ixui;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//静的コンテンツに対して除外設定
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/gyazo/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
				// indexは全ユーザーアクセス許可
				.antMatchers("/", "/index").permitAll()
				.anyRequest().authenticated();
				
		//フォーム認証
		//ログイン
		http.formLogin()
				.loginPage("/login")
				//認証時にメインページに遷移
				.defaultSuccessUrl("/main", true) 
				//メールアドレスとパスワードで照合
				.usernameParameter("mail_address").passwordParameter("passowrd")
				.permitAll()
				.and();
		
		//ログアウト設定
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
			.logoutSuccessUrl("/index");
	}
	
	
	@Autowired
	private DataSource dataSource; //コンテナが管理している接続先情報を取得
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth
			.jdbcAuthentication()
				.dataSource(dataSource);
	}

	/*
    @Configuration
    protected static class AuthenticationConfiguration
    extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        UserDetailsServiceImpl userDetailsService;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // 認証するユーザーを設定する
            auth.userDetailsService(userDetailsService)
            // 入力値をbcryptでハッシュ化した値でパスワード認証を行う
            .passwordEncoder(new BCryptPasswordEncoder());

        }
    }
    */
}