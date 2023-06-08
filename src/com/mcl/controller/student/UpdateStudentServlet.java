package com.mcl.controller.student;

import com.mcl.converter.MyDateConverter;
import com.mcl.entity.Student;
import com.mcl.service.StudentService;
import com.mcl.service.impl.StudentServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
    private StudentService studentService = new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取页面修改后的数据，进行持久化操作
        Map<String, String[]> parameterMap = req.getParameterMap();
        Student student = new Student();
        try {
            ConvertUtils.register(new MyDateConverter(), Date.class);
            BeanUtils.populate(student,parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        String updateMsg = null;
        try {
            boolean isUpdate = studentService.updateStudent(student);
            //保存数据是否添加成功
            updateMsg = isUpdate?"修改成功":"修改失败";
        } catch (Exception e) {
            e.printStackTrace();
            updateMsg = "学号重复，修改失败";
        }finally {
            req.setAttribute("updateMsg",updateMsg);
//        System.out.println(student);
            req.getRequestDispatcher("/stdMain").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
