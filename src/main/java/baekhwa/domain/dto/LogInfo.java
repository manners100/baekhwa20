package baekhwa.domain.dto;

import baekhwa.domain.entity.User;
import lombok.Data;


@Data
public class LogInfo{
	
	private String email;
	private String name;
	
	public LogInfo(User user) {
		this.email = user.getEmail();
		this.name = user.getName();
	}
	
}
