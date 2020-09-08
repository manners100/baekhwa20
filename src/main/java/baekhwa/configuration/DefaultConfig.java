package baekhwa.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class DefaultConfig {
	
	@Autowired
	private ApplicationContext applicationContext;
	//마이바티스 사용시 설정
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean factoryBean= new SqlSessionFactoryBean();
		log.debug(dataSource);
		//datasource
		factoryBean.setDataSource(dataSource);
		//mapper
		Resource[] mapperLocations=
				applicationContext.getResources("classpath:/mapper/**/mapper-*.xml");
		factoryBean.setMapperLocations(mapperLocations);
		return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		log.debug(sqlSessionFactory);
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	

}
