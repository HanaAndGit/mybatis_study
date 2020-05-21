package mybatis_study.mappers;

import java.util.Map;

import mybatis_study.dto.Tutor;

public interface TutorMapper {
	Tutor selectTutorByTutorId(Tutor tutor);
	int insertTutors(Map<String, Object> map);
	int insertTutor(Tutor tutor);
	int deleteTutor(int tutor_id);
}
