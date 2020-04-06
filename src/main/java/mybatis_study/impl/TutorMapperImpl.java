package mybatis_study.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis_study.dto.Tutor;
import mybatis_study.jdbc.MyBatisSqlSessionFactory;
import mybatis_study.mappers.TutorMapper;

public class TutorMapperImpl implements TutorMapper {
	private String namespace = "mybatis_study.mappers.TutorMapper";
	private static final TutorMapperImpl instance = new TutorMapperImpl();
	private SqlSession sqlSession;
	
	private TutorMapperImpl() {
	}
	
	public static TutorMapperImpl getInstance() {
		return instance;
	}
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	
	@Override
	public Tutor selectTutorByTutorId(Tutor tutor) {
		return sqlSession.selectOne(namespace + ".selectTutorByTutorId", tutor);
	}

}
