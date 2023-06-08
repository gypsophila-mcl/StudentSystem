package com.mcl.controller.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcl.entity.Teacher;
import com.mcl.service.TeacherService;
import com.mcl.service.impl.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
@WebServlet("/findAllTeachers")
public class FindAllTeachersServlet extends HttpServlet {
    private TeacherService teacherService = new TeacherServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取所有教师信息
        List<Teacher> all = teacherService.findAll();

        //将数据返回页面（ajax形式）
        //创建 ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        objectMapper.writeValue(writer,all);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
