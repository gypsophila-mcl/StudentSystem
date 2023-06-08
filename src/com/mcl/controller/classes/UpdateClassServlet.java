package com.mcl.controller.classes;

import com.mcl.entity.Classes;
import com.mcl.service.ClassesService;
import com.mcl.service.impl.ClassesServiceImpl;
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
@WebServlet("/updateClass")
public class UpdateClassServlet extends HttpServlet {
    private ClassesService classesService = new ClassesServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取页面数据
        Map<String, String[]> parameterMap = req.getParameterMap();
        Classes classes = new Classes();
        try {
            BeanUtils.populate(classes,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

//        //测试代码
//        System.out.println("要修改的数据：" + classes);

        //数据持久化
        String updateMsg = null;
        try {
            boolean isUpdate = classesService.updateClass(classes);

            updateMsg = isUpdate?"修改成功":"修改失败";
        } catch (Exception e) {
            e.printStackTrace();
            updateMsg = "班级姓名重复，修改失败";
        }finally {
            req.setAttribute("updateMsg",updateMsg);

            //转发
            req.getRequestDispatcher("/clsMain").forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
