package com.itboyst.facedemo.service;


import com.itboyst.facedemo.dto.Zsafe_testingDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface QbankService {

    public Map<String,Object> findallQuestion(HttpServletResponse response);

    public List<Zsafe_testingDto> findrand10(String zstudentID, String zsafetestingType);

    public List<Zsafe_testingDto> findrandbynumber(int number,String ztype);

    public List<String> findallsafetype();

    public int findnumberbytype(String ztype);

    public List<Zsafe_testingDto> findalltestbytype(String ztype);
}
