package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.TestScreen;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestscreenMapper {

    public int insertscreen(TestScreen testScreen);

    public List<TestScreen> findleftscreen2();

    public int findleftscreencount();
}
