package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstrange;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;
@Mapper
public interface ZstrangeMapper {

    int insertZstrange(Zstrange zstrange);

    List<Zstrange> findAll(String zrecognizeIP, Timestamp timestamp, String zcheck);

    List<Zstrange> findAllByCameraname(String zrecognizeIP, Timestamp timestamp, String zcheck,String cameraname);

    int deleteOriginalPictureUrl(String originalPictureUrl);
}
