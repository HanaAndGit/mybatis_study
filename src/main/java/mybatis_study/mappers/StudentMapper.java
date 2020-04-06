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
	Student selectStudentByNoAssociation(Student student);
	//enum 타입 다루기
	int insertEnumStudent(Student student);
	//여러 개의 입력 파라미터 전달
	Student selectAllStudentByMap(Map<String, String> map);
	//ResultSet 처리 방식의 재정의
	Map<Integer, Student> selectStudentForMap(int studId);
}
