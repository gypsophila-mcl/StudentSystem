package com.mcl.controller.student;

import com.mcl.service.StudentService;
import com.mcl.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
@WebServlet("/batchDelStd")
public class BatchDelStdServlet extends HttpServlet {
    private StudentService studentService = new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取所有的 id （一个 key 多个 value）
        String[] ids = req.getParameterValues("ids");
        int[] allId = new int[ids.length];
        for (int i = 0; i < ids.length; i++) {
            allId[i] = Integer.valueOf(ids[i]);
        }
        boolean b = studentService.batchDelStd(allId);
        String batchDelMsg = b?"删除成功":"删除失败";



        req.setAttribute("batchDelMsg",batchDelMsg);
        req.getRequestDispatcher("/stdMain").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
