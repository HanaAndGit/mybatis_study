package mybatis_study.mappers;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mybatis_study.dto.Student;

public interface StudentMapper {
	//TypeHandler 
	Student selectStudentByNo (Student student);
	//TypeHandler 사용 x 
	Student selectStudentByNoWithResultMap(Student student);
	List<Student> selectStudentByAll();
	int insertStudent(Student student);
	int deleteStudent(int id);
	int updateStudent(SqlSession sqlSession, Student student);
	List<Student> selectStudentByAllForResultMap();
	List<Map<String,Object>> selectStudentByAllForHashMap();
}
