package baekhwa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import baekhwa.domain.dto.BoardDto;
import baekhwa.domain.entity.JpaBoard;
import baekhwa.domain.entity.JpaBoardRepository;
import baekhwa.mapper.BoardMapper;

@SpringBootTest
class BaekhwaApplicationTests {
	@Autowired
	JpaBoardRepository repository;
	@Autowired
	BoardMapper boardMapper;
	//@Test
	void contextLoads() {
		for(int i=1 ; i<=1000 ; i++) {
			JpaBoard entity=JpaBoard.builder()
					.subject("제목"+i).writer("test")
					.content("내용"+i).user_ip("127.0.0.1").build();
			repository.save(entity);
		}
	}
	
	//@Test
	public void mysqlData() {
		for(int i=10101 ; i<=100000 ; i++) {
			BoardDto dto=new BoardDto("제목"+i, "test", "127.0.0.1", "내용"+i);
			boardMapper.save(dto);
		}
	}
	

}
