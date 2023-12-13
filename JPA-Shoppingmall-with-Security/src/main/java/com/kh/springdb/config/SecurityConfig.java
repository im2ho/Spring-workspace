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
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	/*
		spring-boot-starter-security 의존성 추가 = 현재 프로젝트는 모든 페이지에 대해서 로그인을 통해 접근이 가능하다
			> 원하는 페이지에 따라 비회원 접근도 허용을 원할시 추가 설정 필요
			
		람다식 : ((변수값)-> 변수값에 어떤 처리를 진행할지 작성)
	*/

	@Bean //객체의 생성, 관리, 주입을 담당
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
					// "/**" : 모든 리소스에 대해 권한 설정 & .permitAll() : 인증없이 허용
					.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
			
			//로그인 처리를 따로 하지 않을시 403 오류 가능
			
			//.formLogin() : 로그인 페이지와 기타 로그인 처리 및 성공 실패 처리
			//로그인 후 로그인 세션을 유지하기 위한 설정
			.formLogin((formLogin) -> formLogin
					.loginPage("/user/login") //스프링에서 자체 제공하는 login페이지가 아닌 사용자 커스터 페이지로 사용하겠음
					.defaultSuccessUrl("/")) //인증 성공시 이동할 페이지
			
			//로그아웃 후 로그인 세션을 종료하기 위한 설정
			.logout((logout) -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true))
			
			//CSRF(Cross-Site Request Forgery) 공격 방지
			
	        .csrf((csrf) -> csrf
	        		.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
	        
	        //headers
	        .headers((headers) -> headers
	        		.addHeaderWriter(new XFrameOptionsHeaderWriter(
	        				XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)));
		
		return http.build();
	}
	
	
	//사용자의 신원 확인을 처리하는 인터페이스 > 인증 전 신원확인 필요 선언
	@Bean
	AuthenticationManager authenticationManage(AuthenticationConfiguration a) throws Exception {
		return a.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
