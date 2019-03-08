package cn.shuaijunlan.design.patterns.mvcdemo.controller;

import com.alibaba.fastjson.JSONObject;
import cn.shuaijunlan.design.patterns.mvcdemo.model.Course;
import cn.shuaijunlan.design.patterns.mvcdemo.service.CourseService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Mr SJL on 2016/11/8.
 */
@Controller
@RequestMapping("/courses")
/**
 * // /courses/**都会被识别
 */
public class CourseController
{
    private static Logger log = LoggerFactory.getLogger(CourseController.class);
    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService)
    {
        this.courseService = courseService;
    }

    //本方法仅仅处理由“/courses/view”传过来的get的请求
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewCourse(@RequestParam("courseId") int id, Model model)
    {
        log.debug("In viewCourse, courseId = {}", id);
        Course course = courseService.getCoursebyId(id);
        model.addAttribute(course);
        return "course_overview";
    }

    //  /courses/view2/{courseId}
    @RequestMapping(value = "/view2/{courseId}", method = RequestMethod.GET)
    public String viewCourse2(@PathVariable("courseId") int id, Map<String, Object> model)
    {
        log.debug("In viewCourse2, courseId = {}", id);
        Course course = courseService.getCoursebyId(id);
        model.put("course", course);
        return "course_overview";

    }

    //  /courses/view3?courseId=456 形式的URL
    @RequestMapping("view3")
    public String viewCourse3(HttpServletRequest request)
    {
        int id = Integer.parseInt(request.getParameter("courseId"));
        log.debug("In viewCourse2, courseId = {}", id);
        Course course = courseService.getCoursebyId(id);
        request.setAttribute("course", course);
        return "course_overview";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET, params = "add")
    public String createCourse()
    {
        return "course_admin/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String doSave(@ModelAttribute Course course)
    {
        System.out.println( "dasdas:"+course.getDescr() );
        log.debug("Info of Course:");
        log.debug(ReflectionToStringBuilder.toString(course));
        //在此进行业务操作，比如数据库持久化
        course.setCourseId(123);
        return "redirect:view2/" + course.getCourseId();
    }


    //  /courses/{courseId}
    @RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
    public @ResponseBody Course getCourseInJson(@PathVariable("courseId") int id)
    {
        return courseService.getCoursebyId(id);
    }

    @RequestMapping(value = "/jsontype/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<Course> getCourseInJson2(@PathVariable Integer courseId)
    {
        Course course = courseService.getCoursebyId(courseId);
        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

    //url形式为：/c1?courseId=111
    @RequestMapping(value = "/c1" , method = RequestMethod.GET)
    public @ResponseBody JSONObject getCourseOnJson(@RequestParam("courseId") int id)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("asdf","asdf");
        //return courseService.getCoursebyId(id);
        return jsonObject;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String  showUploadPage()
    {
        log.debug("Process file: {}");
        System.out.println("heloo");
        return "course_admin/file";
    }
    /**
     * 实现文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String doUpLoadFile(@RequestParam("file") MultipartFile file) throws IOException
    {
        if (!file.isEmpty())
        {
            log.debug("Process file: {}");
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("e:\\temp", System.currentTimeMillis() + file.getOriginalFilename()));
        }
        return "success";
    }

}
