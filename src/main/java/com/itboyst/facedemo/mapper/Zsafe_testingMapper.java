package com.itboyst.facedemo.mapper;



import com.itboyst.facedemo.dto.Zsafe_testingDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface Zsafe_testingMapper {
    public List<Zsafe_testingDto> findallCbank();

    public List<Zsafe_testingDto> findallJbank();

    public List<Zsafe_testingDto> findrand10(String zstudentID,String ztestingtype);

    public List<Zsafe_testingDto> findrandbynumber(int number,String ztype);

    public List<String> findallsafetype();

    public int findnumberbytype(String ztype);

    public List<Zsafe_testingDto> findalltestbytype(String ztype);
}
