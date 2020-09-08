package baekhwa.domain.dto;

import baekhwa.domain.entity.JpaBoard;
import lombok.Data;

@Data
public class JpaBoardRequestDto {
	private String subject;
	private String content;
	private String writer;
	
	private String user_ip;
	
	public JpaBoard toEntity() {
		//return new JpaBoard(subject, writer, user_ip, content);
		return JpaBoard.builder()
				.subject(subject).writer(writer).user_ip(user_ip).content(content)
				.build();
	}
}
