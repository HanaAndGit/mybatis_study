package mybatis_study.mappers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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

import mybatis_study.dto.Gender;
import mybatis_study.dto.PhoneNumber;
import mybatis_study.dto.Student;
import mybatis_study.impl.StudentMapperImpl;
import mybatis_study.jdbc.MyBatisSqlSessionFactory;
import mybatis_study.jdbc.MyBatisSqlSessionFactoryTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentMapperTest {
	private static StudentMapperImpl dao;
	private static final Log log = LogFactory.getLog(MyBatisSqlSessionFactoryTest.class);
	private static SqlSession sqlSession;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = StudentMapperImpl.getInstance();
		sqlSession = MyBatisSqlSessionFactory.openSession(true);
		dao.setSqlSession(sqlSession);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
		sqlSession.close();
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
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);
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
	
	@Test
	public void test10selectStudentByNoAssociation() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Student student = new Student();
		student.setStudId(1);
		Student selectedStd = dao.selectStudentByNoAssociation(student);
		Assert.assertNotNull(selectedStd);
		log.debug(selectedStd.toString());
	}
	
	
	@Test
	public void test12InsertEnumstudent() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		Calendar newDate = GregorianCalendar.getInstance();
		newDate.set(1990,2,28);
		Student student = new Student();
		student.setStudId(3);
		student.setName("test");
		student.setEmail("test@test.co.kr");
		student.setDob(newDate.getTime());
		student.setPhone(new PhoneNumber("010-1234-1234"));
		student.setGender(Gender.FEMALE);
		int res = dao.insertEnumStudent(student);
		Assert.assertEquals(1, res);
		
		student.setStudId(4);
		student.setName("test4");
		student.setEmail("test4@test.co.kr");
		student.setDob(newDate.getTime());
		student.setPhone(new PhoneNumber("010-1234-1234"));
		student.setGender(Gender.MALE);
		int res1 = dao.insertEnumStudent(student);
		Assert.assertEquals(1, res1);
		
	}
	
	
	
	
	  @Test public void test11selectAllStudentByMap() {
	  log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+ "()");
	  Map<String, String> maps = new HashMap<>();
	   maps.put("name", "Timothy");
	  maps.put("email", "timothy@gmail.com"); 
	  Student student = dao.selectAllStudentByMap(maps); 
	  Assert.assertNotNull(student);
	  log.debug(student.toString());
	  
	  maps.remove("email"); 
	  student = dao.selectAllStudentByMap(maps);
	  log.debug(student.toString());
	  
	  maps.clear(); maps.put("email", "timothy@gmail.com"); 
	  student = dao.selectAllStudentByMap(maps); 
	  log.debug(student.toString()); 
	 }
	 
	  @Test
	  public void test13selectStudentForMap() {
		  log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		  Map<Integer, Student> map = dao.selectStudentForMap(1);
		  Assert.assertNotNull(map);
		  for(Entry<Integer,Student> entry : map.entrySet()) {
			  System.out.printf("key(%s) - value(%s)%n", entry.getKey(), entry.getValue());
		  }
	  }
	

		@Test
		public void test14updateSetStudent() {
			log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
			
			Student student = new Student();
			student.setStudId(1);
			student.setPhone(new PhoneNumber("987-654-3211"));
			student.setDob(new Date());
			
			int result = dao.updateSetStudent(student);
			Assert.assertSame(1, result);
			
			student.setPhone(new PhoneNumber("123-123-1234"));
			student.setDob(new GregorianCalendar(1988,04,25).getTime());
			
			result = dao.updateSetStudent(student);
			Assert.assertSame(1, result);
		}
		
		
	
	

}
