select user (), database ();
use mybatis_study;

select * from addresses;
select * from students;
select * from tutors;
select * from course_enrollment;
select * from courses;

select stud_id as studId, name, email, dob, phone 
		from students
		where stud_id=1;

select stud_id as studId, name, email, dob, phone,
		substring(phone, 1, 3) as f,
		substring(phone, 5, 3) as m,
		substring(phone, 9, 4) as l 
		from students
		where stud_id=1;
select STUD_ID as studID, NAME, EMAIL, PHONE, DOB from students;		
	
desc students;

select * from courses where name like "%java%";
delete from courses where course_id in (5,6);