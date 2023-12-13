package com.kh.springdb.config;

/*
	데이터베이스나 외부에서 로그인하여 인증을 하기 위해서는 인증처리 필요
	
	UserDetailsService : 사용자 정보를 인증
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.SecurityUser;
import com.kh.springdb.model.UserRole;
import com.kh.springdb.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {
	
	//멤버(필드)변수
	//유저에 대한 정보를 로그인 할 때, userDetails를 사용해서 로그인 할 수 있는 유저가 있는지 확인
	//사용자명을 기준으로 사용자 정보를 가져온다
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		// _siteUser : 작명 형식 중 하나일 뿐 입맛대로 쓰셔
		Optional<SecurityUser> _siteUser = this.userRepository.findByusername(username);
		if(_siteUser.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}
		SecurityUser siteUser = _siteUser.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		//만약 로그인이 된다면, 로그인 분류를 role에 따라 추가로 작성
		//if(UserRole.ADMIN.equals(siteUser.getIsRole()))
		if("ADMIN".equals(siteUser.getIsRole())) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));	
		} else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		
		//User : import org.springframework.security.core.userdetails.User;
		return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
	}
}

/*

	UserDetails() : 스프링 시큐리티가 사용자의 인증과 권한 부여를 처리하는데 필요한 정보를 제공
	
 		getAuthorities() : 사용자가 가지고 있는 권한 목록을 반환
 					권한은 GrantedAuthority 갖고옴
  					권한은 정의된 권한에 따라 달라짐 권한은 개발자가 설정
 		getPassword() : 사용자의 비밀번호를 반환 
 						데이터베이스에서 암호화 처리된 형태로 저장돼있음
 		getUsername() : 사용자명을 반환
 	  	isEnables() : 계정이 활성화 돼있는지 여부를 Boolean 값으로 나타냄

*/