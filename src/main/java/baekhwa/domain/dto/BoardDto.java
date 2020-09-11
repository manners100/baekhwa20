package baekhwa.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BoardDto {
	private int no;
	private String subject;
	private int count;
	private String writer;
	private LocalDateTime reg_date;
	private String user_ip;
	private String content;
	
	public BoardDto(String subject, String writer, String user_ip, String content) {
		this.subject = subject;
		this.writer = writer;
		this.user_ip = user_ip;
		this.content = content;
	}
	
	
	
}
