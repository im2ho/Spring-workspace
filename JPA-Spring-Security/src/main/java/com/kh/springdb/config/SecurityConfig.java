package com.kh.springdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration //스프링 시큐리티 설정을 의미하는 어노테이션 (스프링에서 환경설정 파일임을 나타냄)
@EnableWebSecurity //모든 URL이 스프링 시큐리티의 제어를 받도록 만든 어노테이션
public class SecurityConfig {

	@Bean //객체의 생성, 관리, 주입을 담당
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
					.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
			
			//로그인 후 로그인 세션을 유지하기 위한 설정
			.formLogin((formLogin) -> formLogin
					.loginPage("/user/login")
					.defaultSuccessUrl("/"))
			
			//로그아웃 후 로그인 세션을 종료하기 위한 설정
			.logout((logout) -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true));
		
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManage(AuthenticationConfiguration a) throws Exception{
		return a.getAuthenticationManager();
	}
	
	//비밀번호 변경시, 어떤 식으로 암호 처리를 할 지 설정
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(); //BCryptPasswordEncoder 형식으로 비밀번호 저장
	}
	
}
