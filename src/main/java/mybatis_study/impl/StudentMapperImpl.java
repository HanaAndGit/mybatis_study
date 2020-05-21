package mybatis_study.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import mybatis_study.dto.Student;
import mybatis_study.jdbc.MyBatisSqlSessionFactory;
import mybatis_study.mappers.StudentMapper;

public class StudentMapperImpl implements StudentMapper {
	private static final StudentMapperImpl instance = new StudentMapperImpl();
	private String namesapce = "mybatis_study.mappers.StudentMapper";
	private SqlSession sqlSession;
	
	private StudentMapperImpl() {
	}
	
	public static StudentMapperImpl getInstance() {
		return instance;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Student selectStudentByNo(Student student) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);){
			return sqlSession.selectOne(namesapce+ ".selectStudentByNo", student);
		}
		
	}

	@Override
	public Student selectStudentByNoWithResultMap(Student student) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true)){
			return sqlSession.selectOne(namesapce + ".selectStudentByNoWithResultMap", student);
		}
	}

	@Override
	public List<Student> selectStudentByAll() {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true)){
			return sqlSession.selectList(namesapce + ".selectStudentByAll"); 
		}
	}

	@Override
	public int insertStudent(Student student) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);){
			int res = sqlSession.insert(namesapce + ".insertStudent", student);
			return res;
		}	
	}

	@Override
	public int deleteStudent(int id) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true)){
			int res = sqlSession.delete(namesapce + ".deleteStudent", id);
			return res;
		}
		
	}

	@Override
	public int updateStudent(SqlSession sqlSession, Student student) {
		int res = sqlSession.update(namesapce + ".updateStudent", student);
		return res;
		
	}

	@Override
	public List<Student> selectStudentByAllForResultMap() {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true)){
			return sqlSession.selectList(namesapce + ".selectStudentByAllForResultMap");
		}
	}

	@Override
	public List<Map<String, Object>> selectStudentByAllForHashMap() {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true)){
			return sqlSession.selectList(namesapce + ".selectStudentByAllForHashMap");
		}
	}

	@Override
	public Student selectStudentByNoAssociation(Student student) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true)){
			return sqlSession.selectOne(namesapce + ".selectStudentByNoAssociation" , student); 
		}
	}

	@Override
	public int insertEnumStudent(Student student) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true)){
			int res = sqlSession.insert(namesapce + ".insertEnumStudent", student);
			return res;
		}
	}

	@Override
	public Student selectAllStudentByMap(Map<String, String> map) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true)){
			return sqlSession.selectOne(namesapce + ".selectAllStudentByMap", map);
		}
	}

	@Override
	public Map<Integer, Student> selectStudentForMap(int studId) {
		Map<Integer, Student> map = new HashMap<Integer, Student>();
		ResultHandler<Student> resultHandler = new ResultHandler<Student>() {

			@Override
			public void handleResult(ResultContext<? extends Student> resultContext) {
				Student student = resultContext.getResultObject();
				map.put(student.getStudId(), student);
				
			}
		};
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true)){
			sqlSession.select(namesapce + ".selectStudentForMap", studId, resultHandler);
		}
		return map;
	}
	
	@Override
	public int updateSetStudent(Student student) {
		int res = sqlSession.update(namesapce + ".updateSetStudent", student);
		return res;
	}

}
