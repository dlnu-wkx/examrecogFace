package com.itboyst.facedemo.service.impl;


import com.itboyst.facedemo.dto.Zsafe_testingDto;
import com.itboyst.facedemo.mapper.Zsafe_testingMapper;
import com.itboyst.facedemo.service.QbankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QbankServiceImpl implements QbankService {
    @Autowired
    private Zsafe_testingMapper cmapper;


    //用map类型装下所有题库
    Map<String,Object> allquestion=new HashMap<>();

    /**
     * 查找随机的五道题，两道判断，三道选择
     * @return
     */
    @Override   
    public Map<String, Object> findallQuestion(HttpServletResponse response) {

        //三道选择题
    List<Zsafe_testingDto> cquestion=cmapper.findallCbank();
        //两道判断题
    List<Zsafe_testingDto> jquestion=cmapper.findallJbank();

    //将选择题和判断题全部放入题库
    allquestion.put("jbank",jquestion);
    allquestion.put("cbank",cquestion);

    //将正确答案全部写入cookie中(可优化字符拼接循环载入)
        Cookie answer0 =new Cookie("answer0",cquestion.get(0).getZresult());
        Cookie answer1 =new Cookie("answer1",cquestion.get(1).getZresult());
        Cookie answer2 =new Cookie("answer2",cquestion.get(2).getZresult());
        Cookie answer3 =new Cookie("answer3",jquestion.get(0).getZresult());
        Cookie answer4 =new Cookie("answer4",jquestion.get(1).getZresult());

        answer1.setMaxAge(86400);
        answer2.setMaxAge(86400);
        answer3.setMaxAge(86400);
        answer4.setMaxAge(86400);
        answer0.setMaxAge(86400);

        response.addCookie(answer0);
        response.addCookie(answer1);
        response.addCookie(answer2);
        response.addCookie(answer3);
        response.addCookie(answer4);



        return allquestion;
    }

    @Override
    public List<Zsafe_testingDto> findrand10(String zstudentID,String ztestingtype){

        return   cmapper.findrand10(zstudentID,ztestingtype);

    }

    @Override
    public List<Zsafe_testingDto> findrandbynumber(int number,String ztype){return cmapper.findrandbynumber(number,ztype);}

    @Override
    public List<String> findallsafetype(){return cmapper.findallsafetype();}

    @Override
    public int findnumberbytype(String ztype){return cmapper.findnumberbytype(ztype);};

}
