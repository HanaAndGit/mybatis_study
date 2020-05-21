package mybatis_study.impl;

import org.apache.ibatis.session.SqlSession;

import mybatis_study.dto.UserPic;
import mybatis_study.jdbc.MyBatisSqlSessionFactory;
import mybatis_study.mappers.UserPicMapper;

public class UserPicMapperImpl implements UserPicMapper {
	private String namespace = "mybatis_study.mappers.UserPicMapper";
	private static final UserPicMapperImpl instance = new UserPicMapperImpl();
	private SqlSession sqlSession;
	
	private UserPicMapperImpl () {}
	
	public static UserPicMapperImpl getInstance() {
		return instance;
	}
	
	
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertUserPic(UserPic userPic) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);){
			int res = sqlSession.insert(namespace + ".insertUserPic", userPic);
			return res;
		}
	}

	@Override
	public UserPic getUserPic(int id) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true)){
			return sqlSession.selectOne(namespace + ".getUserPic", id);
		}
	}

}
