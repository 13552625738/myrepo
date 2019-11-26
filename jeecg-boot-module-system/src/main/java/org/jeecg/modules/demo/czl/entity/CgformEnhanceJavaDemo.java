package org.jeecg.modules.demo.czl.entity;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component("cgformEnhanceJavaDemo")
public class CgformEnhanceJavaDemo implements CgformEnhanceJavaInter {
    @Override
    public void execute(String s, Map<String, Object> map) throws BusinessException {
        System.out.println("我是给编辑按钮实现的增强代码");
    }

    @Override
    public void execute(String s, JSONObject jsonObject) throws BusinessException {

        System.out.println("我是给编辑按钮实现的增强代码");
        System.out.println("表名字"+s);
        System.out.println("json对象"+jsonObject.toString());
        //实现自己增强业务代码比如可以处理邮件发送等等
    }
}
