-- ���̹�Ƽ������
DROP SCHEMA IF EXISTS mybatis_study;

-- ���̹�Ƽ������
CREATE SCHEMA mybatis_study;

-- �ּ�
CREATE TABLE mybatis_study.addresses (
	addr_id INT(11)     NOT NULL COMMENT '�ּ��ڵ�', -- �ּ��ڵ�
	street  VARCHAR(50) NOT NULL COMMENT '����', -- ����
	city    VARCHAR(50) NOT NULL COMMENT '��', -- ��
	state   VARCHAR(50) NOT NULL COMMENT '��', -- ��
	zip     VARCHAR(10) NULL     COMMENT '�����ȣ', -- �����ȣ
	country VARCHAR(50) NOT NULL COMMENT '��' -- ��
)
COMMENT '�ּ�';

-- �ּ�
ALTER TABLE mybatis_study.addresses
	ADD CONSTRAINT
		PRIMARY KEY (
			addr_id -- �ּ��ڵ�
		);

ALTER TABLE mybatis_study.addresses
	MODIFY COLUMN addr_id INT(11) NOT NULL AUTO_INCREMENT COMMENT '�ּ��ڵ�';

ALTER TABLE mybatis_study.addresses
	AUTO_INCREMENT = 5;

-- ����
CREATE TABLE mybatis_study.courses (
	course_id   INT(11)      NOT NULL COMMENT '�����ڵ�', -- �����ڵ�
	name        VARCHAR(100) NOT NULL COMMENT '�����', -- �����
	description VARCHAR(512) NULL     COMMENT '����', -- ����
	start_date  DATE         NULL     COMMENT '������', -- ������
	end_date    DATE         NULL     COMMENT '������', -- ������
	tutor_id    INT(11)      NOT NULL COMMENT '������ȣ' -- ������ȣ
)
COMMENT '����';

-- ����
ALTER TABLE mybatis_study.courses
	ADD CONSTRAINT
		PRIMARY KEY (
			course_id -- �����ڵ�
		);

ALTER TABLE mybatis_study.courses
	MODIFY COLUMN course_id INT(11) NOT NULL AUTO_INCREMENT COMMENT '�����ڵ�';

ALTER TABLE mybatis_study.courses
	AUTO_INCREMENT = 4;

-- �������
CREATE TABLE mybatis_study.course_enrollment (
	course_id INT(11) NOT NULL COMMENT '�����ڵ�', -- �����ڵ�
	stud_id   INT(11) NOT NULL COMMENT '�л��ڵ�' -- �л��ڵ�
)
COMMENT '�������';

-- �������
ALTER TABLE mybatis_study.course_enrollment
	ADD CONSTRAINT
		PRIMARY KEY (
			course_id, -- �����ڵ�
			stud_id    -- �л��ڵ�
		);

-- �л�
CREATE TABLE mybatis_study.students (
	stud_id INT(11)     NOT NULL COMMENT '�л��ڵ�', -- �л��ڵ�
	name    VARCHAR(50) NOT NULL COMMENT '�̸�', -- �̸�
	email   VARCHAR(50) NOT NULL COMMENT '�̸���', -- �̸���
	phone   VARCHAR(15) NULL     COMMENT '����ó', -- ����ó
	dob     DATE        NULL     COMMENT '����', -- ����
	bio     LONGTEXT    NULL     COMMENT '�ڱ�Ұ�', -- �ڱ�Ұ�
	pic     BLOB        NULL     COMMENT '����', -- ����
	addr_id INT(11)     NULL     COMMENT '�ּ��ڵ�' -- �ּ��ڵ�
)
COMMENT '�л�';

-- �л�
ALTER TABLE mybatis_study.students
	ADD CONSTRAINT
		PRIMARY KEY (
			stud_id -- �л��ڵ�
		);

ALTER TABLE mybatis_study.students
	MODIFY COLUMN stud_id INT(11) NOT NULL AUTO_INCREMENT COMMENT '�л��ڵ�';


ALTER TABLE mybatis_study.students
	AUTO_INCREMENT = 4;

/* 열거형 ENUM 컬럼 gender 추가 */

alter table mybatis_study.students add gender tinyint(1) unsigned;
desc students;

-- ����
CREATE TABLE mybatis_study.tutors (
	tutor_id INT(11)     NOT NULL COMMENT '������ȣ', -- ������ȣ
	name     VARCHAR(50) NOT NULL COMMENT '�̸�', -- �̸�
	email    VARCHAR(50) NOT NULL COMMENT '�̸���', -- �̸���
	phone    VARCHAR(15) NULL     COMMENT '����ó', -- ����ó
	dob      DATE        NULL     COMMENT '����', -- ����
	bio      LONGTEXT    NULL     COMMENT '�ڱ�Ұ�', -- �ڱ�Ұ�
	pic      BLOB        NULL     COMMENT '����', -- ����
	addr_id  INT(11)     NULL     COMMENT '�ּ��ڵ�' -- �ּ��ڵ�
)
COMMENT '����';

-- ����
ALTER TABLE mybatis_study.tutors
	ADD CONSTRAINT
		PRIMARY KEY (
			tutor_id -- ������ȣ
		);

ALTER TABLE mybatis_study.tutors
	MODIFY COLUMN tutor_id INT(11) NOT NULL AUTO_INCREMENT COMMENT '������ȣ';

ALTER TABLE mybatis_study.tutors
	AUTO_INCREMENT = 5;

-- user_pics
CREATE TABLE mybatis_study.user_pics (
	id   INT(11)     NOT NULL COMMENT 'id', -- id
	name VARCHAR(50) NULL     COMMENT 'name', -- name
	pic  LONGBLOB        NULL     COMMENT 'pic', -- pic
	bio  LONGTEXT    NULL     COMMENT 'bio' -- bio
)
COMMENT 'user_pics';

-- user_pics
ALTER TABLE mybatis_study.user_pics
	ADD CONSTRAINT
		PRIMARY KEY (
			id -- id
		);

ALTER TABLE mybatis_study.user_pics
	MODIFY COLUMN id INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id';

ALTER TABLE mybatis_study.user_pics
	AUTO_INCREMENT = 37;

-- ����
ALTER TABLE mybatis_study.courses
	ADD CONSTRAINT FK_COURSE_TUTOR -- FK_COURSE_TUTOR
		FOREIGN KEY (
			tutor_id -- ������ȣ
		)
		REFERENCES mybatis_study.tutors ( -- ����
			tutor_id -- ������ȣ
		),
	ADD INDEX FK_COURSE_TUTOR (
		tutor_id -- ������ȣ
	);

-- �������
ALTER TABLE mybatis_study.course_enrollment
	ADD CONSTRAINT FK_ENROLLMENT_STUD -- FK_ENROLLMENT_STUD
		FOREIGN KEY (
			stud_id -- �л��ڵ�
		)
		REFERENCES mybatis_study.students ( -- �л�
			stud_id -- �л��ڵ�
		),
	ADD INDEX FK_ENROLLMENT_STUD (
		stud_id -- �л��ڵ�
	);

-- �л�
ALTER TABLE mybatis_study.students
	ADD CONSTRAINT FK_STUDENTS_ADDR -- FK_STUDENTS_ADDR
		FOREIGN KEY (
			addr_id -- �ּ��ڵ�
		)
		REFERENCES mybatis_study.addresses ( -- �ּ�
			addr_id -- �ּ��ڵ�
		),
	ADD INDEX FK_STUDENTS_ADDR (
		addr_id -- �ּ��ڵ�
	);

-- ����
ALTER TABLE mybatis_study.tutors
	ADD CONSTRAINT FK_TUTORS_ADDR -- FK_TUTORS_ADDR
		FOREIGN KEY (
			addr_id -- �ּ��ڵ�
		)
		REFERENCES mybatis_study.addresses ( -- �ּ�
			addr_id -- �ּ��ڵ�
		),
	ADD INDEX FK_TUTORS_ADDR (
		addr_id -- �ּ��ڵ�
	);

-- �������
ALTER TABLE mybatis_study.course_enrollment
	ADD CONSTRAINT FK_ENROLLMENT_COURSE -- FK_ENROLLMENT_COURSE
		FOREIGN KEY (
			course_id -- �����ڵ�
		)
		REFERENCES mybatis_study.courses ( -- ����
			course_id -- �����ڵ�
		);
	
	
-- user_mybatis_study localhost, % ���� �߰�
drop user if exists 'user_mybatis_study'@'localhost';
drop user if exists 'user_mybatis_study'@'%';

grant all privileges on mybatis_study.* to 'user_mybatis_study'@'localhost' identified by 'rootroot';
grant all privileges on mybatis_study.* to 'user_mybatis_study'@'%' identified by 'rootroot';
	
	
	
	
	
	