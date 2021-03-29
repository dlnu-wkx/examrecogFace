package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Ztask_input;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Ztask_inputMapper {
    public int isnerttaskinput(Ztask_input ztask_input);

    public int findistaskinput(Ztask_input ztask_input);

    public int updatetaskselfcheck(Ztask_input ztask_input);
}
