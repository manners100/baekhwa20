package baekhwa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import baekhwa.domain.entity.JpaBoard;
import baekhwa.domain.entity.JpaBoardRepository;

@SpringBootTest
class BaekhwaApplicationTests {
	@Autowired
	JpaBoardRepository repository;
	//@Test
	void contextLoads() {
		for(int i=1 ; i<=1000 ; i++) {
			JpaBoard entity=JpaBoard.builder()
					.subject("제목"+i).writer("test")
					.content("내용"+i).user_ip("127.0.0.1").build();
			repository.save(entity);
		}
	}

}
