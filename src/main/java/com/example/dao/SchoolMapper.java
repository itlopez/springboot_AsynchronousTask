package com.example.dao;

import com.example.dto.SchoolDto;
import com.example.entity.School;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by lopez on 2017/2/27.
 */
@Mapper
public interface SchoolMapper {

    List<School> queryListSchoolByIndex(SchoolDto schoolDto);
}
