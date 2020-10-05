package baekhwa.domain.dto;

import baekhwa.domain.entity.User;
import lombok.Data;

@Data
public class UserRequestDto {
	private String email;
	private String pw;
	
	public User toEntity() {
		return User.builder()
				.email(email).pw(pw)
				.role(Role.USER)
				.name("NoName")
				.build();
	}
	
}
