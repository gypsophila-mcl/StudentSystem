package com.mcl.controller.teacher;

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
@WebServlet("/addTeacher")
public class AddTeacherServlet extends HttpServlet {
    private TeacherService teacherService = new TeacherServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

//        String teacherName = req.getParameter("teacherName");
//        String teacherNo = req.getParameter("teaNo");
//        int age = Integer.parseInt(req.getParameter("age"));
//        String gender = req.getParameter("teaGender");
//        String tel = req.getParameter("teaTel");
//        Teacher teacher = new Teacher(teacherNo, teacherName, age, gender, tel);
        //优化
        Map<String, String[]> parameterMap = req.getParameterMap();
        Teacher teacher = new Teacher();
        try {
            BeanUtils.populate(teacher,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

//        //测试打印添加的教师信息
//        System.out.println(teacher);
        String addMsg = null;

        try {
            //数据持久化
            boolean isAdd = teacherService.addTeacher(teacher);
            addMsg = isAdd?"添加成功":"添加失败";
        } catch (Exception e) {
            e.printStackTrace();
            addMsg = "教师编号重复，添加失败";
        } finally {
            req.setAttribute("addMsg",addMsg);

            req.getRequestDispatcher("/teacherMain").forward(req,resp);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
