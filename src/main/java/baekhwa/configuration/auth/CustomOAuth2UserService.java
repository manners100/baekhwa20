package baekhwa.configuration.auth;


import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import baekhwa.configuration.auth.dto.OAuthAttributes;
import baekhwa.domain.dto.LogInfo;
import baekhwa.domain.entity.User;
import baekhwa.domain.entity.UserRepository;
import lombok.RequiredArgsConstructor;

//구글로그인 이후 가져온 사용자정보를 기반으로 회원가입,정보수정,세션저장등 기능을 처리하기위한 클래스
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{

	//@Autowired
	private final HttpSession httpSession;
	//@Autowired
	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2UserService delegate=new DefaultOAuth2UserService();
		OAuth2User aAuth2User =delegate.loadUser(userRequest);
		
		//서비스(구글,네이버,카카오) 구분하기위한 코드 
		String registrationId=userRequest.getClientRegistration().getRegistrationId();
		//로그인정보가 저장되어 있는 key: 네이버나 카카오는 지원하지 않는다
		//구글에서는 기본코드: sub
		String userNameAttributeName=userRequest.getClientRegistration()
				.getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		
		OAuthAttributes attributes=OAuthAttributes.of(registrationId, userNameAttributeName, aAuth2User.getAttributes());
		//socalUser DB에저장
		User user=saveOrUpdate(attributes);
		
		//session에 등록
		httpSession.setAttribute("user", new LogInfo(user));
		
		return new DefaultOAuth2User(
				Collections.singleton( new SimpleGrantedAuthority( user.getRoleKey() ) ),
				attributes.getAttributes(),
				attributes.getNameAttributeKey()
				);
	}
	
	
	private User saveOrUpdate(OAuthAttributes attributes) {
		//*
		User user=userRepository.findByEmail(attributes.getEmail())
				.map(e->e.update(attributes.getName(), attributes.getPicture()))
				.orElse(attributes.toEntity());
				
		
		
		return userRepository.save(user);
		//*/
		//return attributes.toEntity();
	}
	
}
