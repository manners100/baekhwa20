package baekhwa.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import baekhwa.domain.dto.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(nullable = false)
	String email;
	@Column(nullable = false)
	String pw;
	
	//기본적으로 int로된 숫자를 저장한다. 문자열로 저장할수있도록 선언
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	Role role;//Role.GUEST , Role.USER
	
	@Column(nullable = false)
	String name;
	
	@Column
	String picture;
	
	@CreatedDate
	@Column(nullable = false)
	LocalDateTime createdate;
	
	@Builder
	public User(String email, String pw, Role role, String name, String picture) {
		this.email = email;
		this.pw = pw;
		this.role = role;
		this.name = name;
		this.picture = picture;
	}
	
	public String getRoleKey() {
		return this.role.getKey();
	}

	public User update(String name, String picture) {
		this.name=name;
		this.picture=picture;
		
		return this;
	}
	
	
}
