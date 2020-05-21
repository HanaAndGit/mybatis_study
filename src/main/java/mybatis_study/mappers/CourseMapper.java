package mybatis_study.mappers;

import java.util.List;
import java.util.Map;

import mybatis_study.dto.Course;
import mybatis_study.dto.CourseStat;

public interface CourseMapper {
	//동적 SQL
	List<Course> selectCoursesByCondition(Map<String, Object> map);
	List<Course> selectCaseCourses(Map<String, Object> map);
	List<Course> selectWhereCourses(Map<String, Object> map);
	
	//trim
	List<Course> selectTrimCourses(Map<String, Object> map);
	//forEach (select)
	List<Course> selectCoursesForeachByTutors(Map<String, Object> map);
	//forEach (insert)
	int insertCourses(Map<String, Object> map);
	//forEach(delete)
	int deleteCourses(Map<String, Object> map);
	//CourseUiService (Transaction)
	int insertCourse(Course course);
	int deleteCourse(int course_id);
	//Procedure
	Map<String, Object> getCourseCountByTutor(Map<String, Object> param);
	Map<String, Object> getCourseCountByTutor2(Map<String, Object> param);
	CourseStat getCourseCountByTutor3(int param);

	
	
}
