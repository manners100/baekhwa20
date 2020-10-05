package baekhwa.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>{
	//JPQL
	@Query("select r from Reply r where bno=:b_no order by rno desc")
	List<Reply> findAllByBNo(@Param("b_no") Long bno);
	
	List<Reply> findAllByBnoOrderByRnoDesc(Long bno);
	//Spring-Data-JPA에서 언더스코어(_)가 프로퍼티을 찾기 위한 탐색 경로를 지정하는 예약어
	
	//(쿼리 메서드: 메서드이름으로 쿼리를 생성하는 기능) 전략 
	//조건컬럼 검색컬럼이나 정렬컬럼으로 사용하는 컬럼은 그냥 소문자로 만드는게 메서드쿼리사용에 편리하다
	//1. By 뒤에는 컬럼명.. 첫글자 대문자로 표기해서 column명이 bno 이면 Bno
	//2. 컬럼명을 카멜표기법(낙타법) 이나 스네이크표기법('_' 언더스코어 처리된) 사용하지 말자

}
