package com.sysmap.learning.course.data;

import com.sysmap.learning.course.domain.Course;
import com.sysmap.learning.course.service.dto.CourseFindByCourseIdResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepository extends MongoRepository<Course, String> {

    List<CourseFindByCourseIdResponse> findByCourseId(String courseId);
}
