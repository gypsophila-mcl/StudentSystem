package com.mcl.controller.classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcl.entity.Classes;
import com.mcl.service.ClassesService;
import com.mcl.service.impl.ClassesServiceImpl;

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
@WebServlet("/findClsById")
public class FindClsByIdServlet extends HttpServlet {
    private ClassesService classesService = new ClassesServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取页面数据
        int clsId = Integer.parseInt(req.getParameter("clsid"));

        //查询班级信息
        Classes cls = classesService.findClsById(clsId);

//        //测试输出查询到的班级信息
//        System.out.println(cls);
//        System.out.println(clsId);

        //以json形式返回
        PrintWriter writer = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        //直接返回
//        objectMapper.writeValue(writer,cls);
        //转换形式在返回
        String jsonData = objectMapper.writeValueAsString(cls);
        writer.println(jsonData);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
