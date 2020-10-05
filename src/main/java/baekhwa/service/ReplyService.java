package baekhwa.service;

import java.util.List;

import baekhwa.domain.dto.ReplyDto;
import baekhwa.domain.dto.ReplyResponseDto;

public interface ReplyService {

	void reg(ReplyDto dto);

	List<ReplyResponseDto> findAllByB_no(long b_no);

	void delete(long rno);

	void update(long rno, String content);

}
