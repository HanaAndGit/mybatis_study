package mybatis_study.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import mybatis_study.dto.Course;
import mybatis_study.dto.CourseStat;
import mybatis_study.jdbc.MyBatisSqlSessionFactory;
import mybatis_study.mappers.CourseMapper;

public class CourseMapperImpl implements CourseMapper {
	private String namespace = "mybatis_study.mappers.CourseMapper";
	private static final CourseMapperImpl instance = new CourseMapperImpl();
	private SqlSession sqlSession;

	private CourseMapperImpl() {
	}

	public static CourseMapperImpl getInstance() {
		return instance;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Course> selectCoursesByCondition(Map<String, Object> map) {
		return sqlSession.selectList(namespace + ".selectCoursesByCondition", map);

	}

	@Override
	public List<Course> selectCaseCourses(Map<String, Object> map) {
		return sqlSession.selectList(namespace + ".selectCaseCourses", map);

	}

	@Override
	public List<Course> selectWhereCourses(Map<String, Object> map) {
		return sqlSession.selectList(namespace + ".selectWhereCourses", map);
	}

	@Override
	public List<Course> selectTrimCourses(Map<String, Object> map) {
		return sqlSession.selectList(namespace + ".selectTrimCourses", map);
	}

	@Override
	public List<Course> selectCoursesForeachByTutors(Map<String, Object> map) {
		return sqlSession.selectList(namespace + ".selectCoursesForeachByTutors", map);
	}

	@Override
	public int insertCourses(Map<String, Object> map) {
		int res = sqlSession.insert(namespace + ".insertCourses", map);
		return res;
	}

	@Override
	public int deleteCourses(Map<String, Object> map) {
		int res = sqlSession.delete(namespace + ".deleteCourses", map);
		return res;
	}

	@Override
	public int insertCourse(Course course) {
		int res = sqlSession.insert(namespace + ".insertCourse", course);
		return res;
	}

	@Override
	public int deleteCourse(int course_id) {
		int res = sqlSession.delete(namespace + ".deleteCourse", course_id);
		return res;
	}
	
	  
	  @Override public Map<String, Object> getCourseCountByTutor(Map<String,
		 Object> param) { Map<String, Object> map = new HashMap<>();
		  ResultHandler<CourseStat> resultHandler = new ResultHandler<CourseStat>() {
		 
		  @Override public void handleResult(ResultContext<? extends CourseStat>
		  resultContext) { CourseStat state = resultContext.getResultObject();
		  map.put(state.getTutor(), state.getTotal());
		 
		  }
		  
		  }; try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true)){
		  sqlSession.select(namespace + ".getCourseCountByTutor", param,
		  resultHandler); return map; } }
		

	@Override
	public Map<String, Object> getCourseCountByTutor2(Map<String, Object> param) {
		Map<String, Object> map = new HashMap<>();
		ResultHandler<CourseStat> resultHandler = new ResultHandler<CourseStat>() {

			@Override
			public void handleResult(ResultContext<? extends CourseStat> resultContext) {
				CourseStat state = resultContext.getResultObject();
				map.put(state.getTutor(), state.getTotal());
			}

		};
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true)) {
			sqlSession.select(namespace + ".getCourseCountByTutor2", param, resultHandler);
			return map;
		}
	}

	@Override
	public CourseStat getCourseCountByTutor3(int param) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true)) {
			return sqlSession.selectOne(namespace + ".getCourseCountByTutor3", param);
		}
	}

}
