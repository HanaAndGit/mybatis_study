package mybatis_study.mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mybatis_study.AbstractTest;
import mybatis_study.dto.Course;
import mybatis_study.dto.PhoneNumber;
import mybatis_study.dto.Student;
import mybatis_study.impl.CourseMapperImpl;
import mybatis_study.jdbc.MyBatisSqlSessionFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseMapperTest extends AbstractTest {
	private static CourseMapperImpl dao;
	private static SqlSession sqlSession;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = CourseMapperImpl.getInstance();
		sqlSession = MyBatisSqlSessionFactory.openSession();
		dao.setSqlSession(sqlSession);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
		sqlSession.close();
	}

	@Test
	public void test01SelectCoursesByCondition() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		GregorianCalendar cal = new GregorianCalendar(2013, 1, 1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutorId", 1);
		
		List<Course> courses = dao.selectCoursesByCondition(map);
		Assert.assertNotNull(courses);
		for(Course c : courses) {
			log.trace(c.toString());
		}
	}
	
	@Test
	public void test02SelectCoursesByCondition() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		GregorianCalendar cal = new GregorianCalendar(2013, 1, 1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseName", "%java%");
		
		List<Course> courses = dao.selectCoursesByCondition(map);
		Assert.assertNotNull(courses);
		for(Course c : courses) {
			log.trace(c.toString());
		}
	}
	
	@Test
	public void test03SelectCoursesByCondition() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		GregorianCalendar cal = new GregorianCalendar(2013, 1, 1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", cal.getTime());
		
		List<Course> courses = dao.selectCoursesByCondition(map);
		Assert.assertNotNull(courses);
		for(Course c : courses) {
			log.trace(c.toString());
		}
	}
	
	@Test
	public void test04SelectCaseCourses() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchBy", "Tutor");
		map.put("tutorId", 1);
		List<Course> courses = dao.selectCaseCourses(map);
		Assert.assertNotNull(courses);
		for(Course c : courses) {
			log.trace(c.toString());
		}
		
		map.replace("searchBy", "CourseName");
		map.remove("tutorId");
		map.put("courseName", "%java%");
		courses = dao.selectCaseCourses(map);
		Assert.assertNotNull(courses);
		for(Course c : courses) {
			log.trace(c.toString());
		}
		
	}
	
	@Test
	public void test05selectWhereCourses() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() +"()");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Course> courses = dao.selectWhereCourses(map);
		Assert.assertNotNull(courses);
		for(Course c : courses) {
			log.trace(c.toString());
		}
		map.put("tutorId", 1);
		courses = dao.selectWhereCourses(map);
		Assert.assertNotNull(courses);
		for(Course c : courses) {
			log.trace(c.toString());
		}
		map.put("courseName", "%java%");
		courses = dao.selectWhereCourses(map);
		for(Course c : courses) {
			log.trace(c.toString());
		}
		
		map.clear();
		map.put("endDate", new Date());
		courses = dao.selectWhereCourses(map);
		
		for(Course c : courses) {
			log.trace(c.toString());
		}
		
	}
	
	
	@Test
	public void test06selectTrimCourses() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() +"()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutorId", 1);
		List<Course> courses = dao.selectTrimCourses(map);
		Assert.assertNotNull(courses);
		for(Course c: courses) {
			log.trace(c.toString());
		}
		
		map.clear();
		map.put("courseName", "%java%");
		courses = dao.selectTrimCourses(map);
		Assert.assertNotNull(courses);
		for(Course c: courses) {
			log.trace(c.toString());
		}
		
		map.clear();
		map.put("tutorId", 1);
		courses = dao.selectTrimCourses(map);
		Assert.assertNotNull(courses);
		for(Course c: courses) {
			log.trace(c.toString());
		}
		
	}
	
	
	@Test
	public void test07selectCoursesForeachByTutors() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		List<Integer> tutorIds = new ArrayList<Integer>();
		tutorIds.add(1);
		tutorIds.add(2);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutorIds", tutorIds);
		
		List<Course> courses = dao.selectCoursesForeachByTutors(map);
		Assert.assertNotNull(courses);
		
		for(Course c : courses) {
			log.trace(c.toString());
		}
	}
	
	
	@Test
	public void test08insertCourses() {
		List<Course> tutors = new ArrayList<Course>();
		Course course1 = new Course();
		course1.setCourseId(4);
		course1.setName("mysql");
		course1.setDescription("database");
		course1.setStartDate(new Date());
		course1.setEndDate(new Date());
		course1.setTutorId(3);
		tutors.add(course1);
		
		Course course2 = new Course();
		course2.setCourseId(5);
		course2.setName("oracle");
		course2.setDescription("database");
		course2.setStartDate(new Date());
		course2.setEndDate(new Date());
		course2.setTutorId(3);
		tutors.add(course2);
		
		Course course3 = new Course();
		course3.setCourseId(6);
		course3.setName("mariaDB");
		course3.setDescription("database");
		course3.setStartDate(new Date());
		course3.setEndDate(new Date());
		course3.setTutorId(4);
		tutors.add(course3);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutors", tutors);
		
		int res = dao.insertCourses(map);
		Assert.assertEquals(3, res);
	}
	
	
	
	@Test
	public void test09deleteCourses() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		List<Integer> courseIds = new ArrayList<Integer>();
		courseIds.add(4);
		courseIds.add(5);
		courseIds.add(6);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseIds", courseIds);
		
		int res = dao.deleteCourses(map);
		Assert.assertEquals(3, res);
	}
	
	
	
	
	

}
