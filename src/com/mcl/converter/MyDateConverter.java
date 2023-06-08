package com.mcl.converter;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: 将字符串转换成Date的转换器
 */
public class MyDateConverter implements Converter {

    @Override
    public Object convert(Class aClass, Object o) {
        //o：代表要转换的数据(String)
        String s = (String) o;
        //写转换过程:把字符串转为Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
