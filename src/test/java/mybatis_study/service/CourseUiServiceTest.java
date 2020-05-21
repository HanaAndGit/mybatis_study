package mybatis_study.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mybatis_study.dto.Course;
import mybatis_study.dto.Tutor;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseUiServiceTest {
	private static CourseUiService service;
	protected static final Log log = LogFactory.getLog(CourseUiServiceTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new CourseUiService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}
	
	
	//교수 추가만 실패
	@Test(expected=RuntimeException.class)
	public void test01JoinNewTutorAndCourseFailTutor() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Tutor tutor = new Tutor();
		tutor.setTutorId(4);
		tutor.setName("kim");
		tutor.setEmail("test@test.co.kr");
		
		Course course = new Course();
		course.setCourseId(8);
		course.setName("Python");
		course.setDescription("Programming");
		course.setStartDate(new Date());
		course.setEndDate(new Date());
		course.setTutorId(4);
		
		service.joinNewTutorAndCourse(tutor, course);
		
	}
	
	//과목 추가만 실패
	@Test(expected=RuntimeException.class)
	public void test02JoinNewTutorAndCourseFailCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Tutor tutor = new Tutor();
		tutor.setTutorId(7);
		tutor.setName("kim");
		tutor.setEmail("test@test.co.kr");
		
		Course course = new Course();
		course.setCourseId(2);
		course.setName("Python");
		course.setDescription("Programming");
		course.setStartDate(new Date());
		course.setEndDate(new Date());
		course.setTutorId(7);
		
		service.joinNewTutorAndCourse(tutor, course);
		
	}
	
	//교수, 과목 추가 성공
	@Test
	public void test03JoinNewTutorAndCourseSuccess() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Tutor tutor = new Tutor();
		tutor.setTutorId(7);
		tutor.setName("kim");
		tutor.setEmail("test@test.co.kr");
		
		Course course = new Course();
		course.setCourseId(8);
		course.setName("Python");
		course.setDescription("Programming");
		course.setStartDate(new Date());
		course.setEndDate(new Date());
		course.setTutorId(7);
		
		
		service.joinNewTutorAndCourse(tutor, course);
	}
	
	//교수 삭제 실패 
	@Test(expected=RuntimeException.class)
	public void test04RemoveTutorAndCourseFailTutor() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		service.removeTutorAndCourse(10, 8);
	}
	
	
	//과목 삭제 실패
	@Test(expected=RuntimeException.class)
	public void test05RemoveTutorAndCourseFailCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		service.removeTutorAndCourse(7, 10);
	}
	
	
	//교수, 과목 삭제 성공
	@Test
	public void test06RemoveTutorAndCourseSuccess() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		service.removeTutorAndCourse(7, 8);
	}
	
	

}
