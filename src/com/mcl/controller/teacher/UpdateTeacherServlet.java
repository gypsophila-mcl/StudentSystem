package com.mcl.controller.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcl.entity.Teacher;
import com.mcl.service.TeacherService;
import com.mcl.service.impl.TeacherServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
@WebServlet("/updateTeacher")
public class UpdateTeacherServlet extends HttpServlet {
    private TeacherService teacherService = new TeacherServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取页面数据
        Map<String, String[]> parameterMap = req.getParameterMap();
        Teacher teacher = new Teacher();

        try {
            BeanUtils.populate(teacher,parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //测试
//        System.out.println(teacher);
        
        String updateMsg = null;

        try {
            //数据持久化
            boolean isUpdate = teacherService.updateTeacher(teacher);
            updateMsg = isUpdate?"修改成功":"修改失败";
        } catch (Exception e) {
            e.printStackTrace();
            updateMsg = "教师编号重复，修改失败";
        }finally {
            req.setAttribute("updateMsg",updateMsg);
            req.getRequestDispatcher("/teacherMain").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
