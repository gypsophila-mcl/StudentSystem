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

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
@WebServlet("/findTeacherById")
public class FindTeacherByIdServlet extends HttpServlet {
    private TeacherService teacherService = new TeacherServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //从页面获取数据 （data:{id:teacherId}）
        int id = Integer.parseInt(req.getParameter("id"));

        //查找
        Teacher teacher = teacherService.findById(id);

        //传回页面
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(teacher);
        PrintWriter writer = resp.getWriter();
        writer.println(jsonData);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
