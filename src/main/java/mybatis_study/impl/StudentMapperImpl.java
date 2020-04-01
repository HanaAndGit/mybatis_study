package mybatis_study.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis_study.dto.Student;
import mybatis_study.jdbc.MyBatisSqlSessionFactory;
import mybatis_study.mappers.StudentMapper;

public class StudentMapperImpl implements StudentMapper {
	private static final StudentMapperImpl instance = new StudentMapperImpl();
	private String namesapce = "mybatis_study.mappers.StudentMapper";
	private SqlSession sqlSession;
	
	public StudentMapperImpl() {
		this.sqlSession = MyBatisSqlSessionFactory.openSession();
		
	}
	
	public static StudentMapperImpl getInstance() {
		return instance;
	}
	
	
	@Override
	public Student selectStudentByNo(Student student) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();){
			return sqlSession.selectOne(namesapce+ ".selectStudentByNo", student);
		}
		
	}

	@Override
	public Student selectStudentByNoWithResultMap(Student student) {
		//매개변수로 student 객체를 꼭 넘겨줘야함
		return sqlSession.selectOne(namesapce + ".selectStudentByNoWithResultMap", student);
	}

	@Override
	public List<Student> selectStudentByAll() {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession()){
			return sqlSession.selectList(namesapce + ".selectStudentByAll"); 
		}
	}

}
