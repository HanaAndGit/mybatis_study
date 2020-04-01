package mybatis_study.mappers;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mybatis_study.dto.Student;
import mybatis_study.impl.StudentMapperImpl;
import mybatis_study.jdbc.MyBatisSqlSessionFactoryTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentMapperTest {
	private static StudentMapper dao;
	private static final Log log = LogFactory.getLog(MyBatisSqlSessionFactoryTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = StudentMapperImpl.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
	}

	

	@Test
	public void test01SelectStudentByNo() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+ "()");
		Student student = new Student();
		student.setStudId(1); //1번 학생
		Student selectStudent = dao.selectStudentByNo(student);
		log.debug(selectStudent.toString());
		Assert.assertEquals(student.getStudId(), selectStudent.getStudId());
	}
	
	@Test
	public void test02SelectStudentByNoWithResultMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+ "()");
		Student student = new Student();
		student.setStudId(1); //1번 학생
		Student selectStudent = dao.selectStudentByNoWithResultMap(student);
		log.debug(selectStudent.toString());
		Assert.assertEquals(student.getStudId(), selectStudent.getStudId());
	}
	
	@Test
	public void test03selectStudentByAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+ "()");
		
		List<Student> lists = dao.selectStudentByAll();
		Assert.assertNotNull(lists);
		for(Student std : lists) {
			log.debug(std.toString());
		}
	}

}
