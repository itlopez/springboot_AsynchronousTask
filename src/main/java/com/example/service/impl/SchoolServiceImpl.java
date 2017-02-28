package com.example.service.impl;

import com.example.dao.SchoolMapper;
import com.example.dto.SchoolDto;
import com.example.entity.School;
import com.example.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2017/2/27.
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolMapper schoolDAO;

    @Override
    public List<School> queryListSchoolByIndex(SchoolDto schoolDto) {
        return schoolDAO.queryListSchoolByIndex(schoolDto);
    }

    @Override
    @Async
    public List<School> quesryA(SchoolDto schoolDto){
        List<School> schoolList = schoolDAO.queryListSchoolByIndex(schoolDto);
        return schoolList;
    }

    @Override
    @Async
    public List<School> quesryB(SchoolDto schoolDto){
        return schoolDAO.queryListSchoolByIndex(schoolDto);
    }

    @Override
    @Async
    public Future<String> sendA(SchoolDto schoolDto) {
        List<School> schoolList = schoolDAO.queryListSchoolByIndex(schoolDto);
        return new AsyncResult<String>("success");
    }

    @Override
    @Async
    public Future<String> sendB(SchoolDto schoolDto) {
        List<School> schoolList = schoolDAO.queryListSchoolByIndex(schoolDto);
        return new AsyncResult<String>("success");
    }

}
