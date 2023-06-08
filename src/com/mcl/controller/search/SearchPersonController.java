package com.mcl.controller.search;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
public class SearchPersonController extends HttpServlet {
//    private SearchService searchService = new SearchServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String manageType = req.getParameter("manageType");
        int managetype = Integer.parseInt((manageType == null || manageType.equals("")) ? "0" : manageType);

        req.setAttribute("isSearch","true");
        switch (managetype){
            case 0:
                req.getRequestDispatcher("/stdMain").forward(req,resp);
            case 1:
                req.getRequestDispatcher("/clsMain").forward(req,resp);
            case 2:
                req.getRequestDispatcher("/teacherMain").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
