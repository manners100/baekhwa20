package baekhwa.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
	
	GUEST("ROLE_GUEST","GUEST"), USER("ROLE_USER","USER");
	
	private final String key;
	private final String title;
	//스프링 시큐리티에서는 권한 코드에 항상 'ROLE_' 항상 있어야한다.
}
