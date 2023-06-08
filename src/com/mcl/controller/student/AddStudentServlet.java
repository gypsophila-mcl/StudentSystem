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
@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    private StudentService studentService = new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取学生信息:
        /*
        //传统方式，比较复杂
        String xh = req.getParameter("xh");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String birthdayStr = req.getParameter("birthday");
        String idnum = req.getParameter("idnum");
        String tel = req.getParameter("tel");
        String classnoStr = req.getParameter("classno");
        //封装到学生对象中
        //字符串转成 Date 格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = sdf.parse(birthdayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer classno = Integer.valueOf(classnoStr);
        Student student = new Student(xh, name, gender, birthday, idnum, tel, classno);
        System.out.println("获取学生信息：" + student);

         */
        //通过工具类
        //获取所有页面数据
        Map<String, String[]> parameterMap = req.getParameterMap();
        //实体对象
        Student student = new Student();
        //使用工具类:吧页面数据封装到实体类对象中
        /*populate(Object bean, Map properties)
            bean:实体类对象
            properties:代表数据
         */
        try {
            //使用自定义转换器
            /* register(Converter converter, Class clazz):
                    converter:自定义转换器
                    clazz:转换后类型
             */
            ConvertUtils.register(new MyDateConverter(), Date.class);
            BeanUtils.populate(student,parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
//        System.out.println("获取学生信息：" + student);
        //保存到数据库
        String addMsg = null;
        try {
            boolean isAdd = studentService.addStudent(student);
            //保存数据
            addMsg = isAdd?"添加成功":"添加失败";
        } catch (Exception e) {
            e.printStackTrace();
            addMsg = "学号重复，无法添加";
        }finally {
            req.setAttribute("addMsg",addMsg);
            //回到学生首页
            req.getRequestDispatcher("/stdMain").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
