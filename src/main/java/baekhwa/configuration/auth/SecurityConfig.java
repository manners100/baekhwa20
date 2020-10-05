package baekhwa.configuration.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import baekhwa.domain.dto.Role;


@EnableWebSecurity//spring Security설정들을 활성화..
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomOAuth2UserService oAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()
				.authorizeRequests()//url별로 권한 관리를 설정하는 옵션
				.antMatchers("/", "/css/**","/images/**", "/js/**","/upload/**","/user/**").permitAll()
				.antMatchers("/admin/**").hasRole(Role.USER.name())
				.anyRequest().authenticated()//설정된 이외 나머지 url들 인증된 사용자만 허용(로그인한 사용자)
			.and()
				.logout()
					.logoutSuccessUrl("/")//로그아웃시 "/" 주소로 이동하세요
			.and()
				.oauth2Login()//oauth2로그인 기능 설정
					.userInfoEndpoint()//oauth2로그인  성공이후 사용자 정보를 가져올때의 설정
						.userService(oAuth2UserService);//로그인 성공시 진행할 구현체 등록
	}
	

}
