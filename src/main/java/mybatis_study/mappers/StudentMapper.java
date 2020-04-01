package mybatis_study.mappers;
import java.util.List;

import mybatis_study.dto.Student;

public interface StudentMapper {
	//TypeHandler 
	Student selectStudentByNo (Student student);
	//TypeHandler »ç¿ë x 
	Student selectStudentByNoWithResultMap(Student student);
	List<Student> selectStudentByAll();
}
