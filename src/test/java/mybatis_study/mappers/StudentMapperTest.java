package mybatis_study.mappers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mybatis_study.dto.PhoneNumber;
import mybatis_study.dto.Student;
import mybatis_study.impl.StudentMapperImpl;
import mybatis_study.jdbc.MyBatisSqlSessionFactory;
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
		student.setStudId(1); //1�� �л�
		Student selectStudent = dao.selectStudentByNo(student);
		log.debug(selectStudent.toString());
		Assert.assertEquals(student.getStudId(), selectStudent.getStudId());
	}
	
	@Test
	public void test02SelectStudentByNoWithResultMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+ "()");
		Student student = new Student();
		student.setStudId(1); //1�� �л�
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
	
	@Test
	public void test04insertStudent() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+ "()");
		Calendar newDate = GregorianCalendar.getInstance();
		newDate.set(1990,2,28);
		Student student = new Student();
		student.setStudId(3);
		student.setName("홍길동");
		student.setEmail("test@test.co.kr");
		student.setPhone(new PhoneNumber("010-1234-1234"));
		student.setDob(newDate.getTime());
		int res = dao.insertStudent(student);
		Assert.assertEquals(1, res);
	}
	
	
	@Test
	public void test05deleteStudent() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+ "()");
		int deleteStudent = dao.deleteStudent(3);
		Assert.assertEquals(1, deleteStudent);
	}
	
	@Test
	public void test06updateStudent() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+ "()");
		Student student = new Student();
		student.setStudId(2);
		student.setName("Douglas");
		student.setEmail("test@test.co.kr");
		student.setPhone(new PhoneNumber("987-654-3211"));
		student.setDob(new Date());
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		int result = dao.updateStudent(sqlSession, student);
		
		
		student.setEmail("douglas@gmail.com");
		student.setPhone(new PhoneNumber("789-456-1234"));
		student.setDob(new GregorianCalendar(1990,8,15).getTime());
		result = dao.updateStudent(sqlSession, student);
		Assert.assertSame(1, result);
		
		
	}
	
	@Test
	public void test08selectStudentByAllForResultMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Student> lists = dao.selectStudentByAllForResultMap();
		Assert.assertNotNull(lists);
		for(Student std : lists) {
			log.debug(std.toString());
		}
	}
	
	@Test
	public void test09selectStudentByAllForHashMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Map<String,Object>> lists = dao.selectStudentByAllForHashMap();
		Assert.assertNotNull(lists);
		for(Map<String, Object> map : lists) {
			for(Entry<String, Object> e : map.entrySet()) {
				log.debug(String.format("%s -> %s", e.getKey(), e.getValue()));
			}
		}
	}

}
