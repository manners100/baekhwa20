package baekhwa.domain.dto;

import java.time.LocalDateTime;

import baekhwa.domain.entity.Reply;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReplyResponseDto {
	private long rno;
	private String content;
	private String writer;
	private LocalDateTime regDate;
	private long bno;
	//*
	public ReplyResponseDto(Reply reply){
		rno=reply.getRno();
		content=reply.getContent();
		writer=reply.getWriter();
		regDate=reply.getRegDate();
		bno=reply.getBno();
	}
	//*/
	
}
