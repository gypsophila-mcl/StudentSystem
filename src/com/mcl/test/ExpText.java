package com.mcl.test;

import com.mcl.dao.impl.StudentDaoImpl;
import com.mcl.entity.Student;
import com.mcl.exception.MyException;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
public class ExpText {
    public static void main(String[] args) {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        Student stdById = studentDao.findStdById(17);
        if (stdById!=null){
            throw new MyException("自定义");
        }else {
            System.out.println(stdById);
        }
    }
}
