package baekhwa.domain.dto;

import baekhwa.domain.entity.Reply;
import lombok.Data;

@Data
public class ReplyDto {
	private String content;
	private String writer;
	private long bno;
	
	public Reply toEntity() {
		//return new Reply(content, writer, b_no);
		return Reply.builder()
				.content(content).writer(writer).bno(bno)
				.build();
	}
}
