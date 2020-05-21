select user (), database ();
use mybatis_study;

select * from addresses;
select * from students;
select * from tutors;
select * from course_enrollment;
select * from courses;

insert into tutors(tutor_id, name, email) values (7, "kim", "test@test.co.kr");

delete from tutors where tutor_id = 7;
delete from courses where course_id = 8;

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


drop procedure if exists course_total;

DELIMITER $$
$$
create procedure course_total(in tutor_id int)
begin
	select t.name as tutor, ifnull(count(c.name), 0) as total
	from tutors t left join courses c on t.tutor_id = c.tutor_id
	where t.tutor_id = tutor_id;
end $$
Delimiter ;

call course_total(1); 
