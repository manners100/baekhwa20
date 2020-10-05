package baekhwa.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)//날짜 자동으로 입력처리하기위해..
@Entity
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rno;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private String writer;
	
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime regDate;
	
	private long bno;

	@Builder
	public Reply(String content, String writer, long bno) {
		super();
		this.content = content;
		this.writer = writer;
		this.bno = bno;
	}
	
	
	public Reply update(String content) {
		this.content=content;
		return this;
	}
	
	
}
