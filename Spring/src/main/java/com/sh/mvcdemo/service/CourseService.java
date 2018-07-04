package com.sh.mvcdemo.service;

import com.sh.mvcdemo.model.Course;
import org.springframework.stereotype.Service;

/**
 * Created by Mr SJL on 2016/11/8.
 */
@Service
public interface CourseService
{
    Course getCoursebyId(Integer courseId);
}
