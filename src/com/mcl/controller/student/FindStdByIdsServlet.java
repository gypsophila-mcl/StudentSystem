package com.mcl.controller.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcl.entity.Student;
import com.mcl.service.StudentService;
import com.mcl.service.impl.StudentServiceImpl;

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
 * @description: 处理修改请求
 */
@WebServlet("/findStdById")
public class FindStdByIdsServlet extends HttpServlet {
    private StudentService studentService = new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取页面请求
        String stdid = req.getParameter("id");
//        System.out.println("stdid=" + stdid);
        //根据 ID 查询学生信息
        Student student = studentService.findStdById(Integer.parseInt(stdid));
        //返回数据给页面
        PrintWriter writer = resp.getWriter();
        //使用 Jackson 把学生对象信息转成 json 数据传过去
        ObjectMapper objectMapper = new ObjectMapper();
//        //方法一：先转数据再返回
//        //转 json 数据
//        String jsonData = objectMapper.writeValueAsString(student);
//        //返回
//        writer.println(jsonData);
        //方法二：直接返回
        objectMapper.writeValue(writer,student);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
