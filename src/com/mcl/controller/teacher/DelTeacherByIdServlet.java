package com.mcl.controller.teacher;

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
@WebServlet("/delTeacherById")
public class DelTeacherByIdServlet extends HttpServlet {
    private TeacherService teacherService = new TeacherServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        ///获取id
        int teacherId = Integer.parseInt(req.getParameter("teacherId"));
        String delMsg = null;

        try {
            //数据持久化
            boolean isDel = teacherService.delTeacherById(teacherId);
            delMsg = isDel?"删除成功":"删除失败";
        } catch (Exception e) {
            e.printStackTrace();
            delMsg = "此教师仍在任教，无法删除";
        }finally {
            PrintWriter writer = resp.getWriter();
            writer.println(delMsg);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
