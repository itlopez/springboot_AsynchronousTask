package com.example.service;

import com.example.dto.SchoolDto;
import com.example.entity.School;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by lopez on 2017/2/27.
 */
public interface SchoolService {

    List<School> queryListSchoolByIndex(SchoolDto schoolDto);

    List<School> quesryA(SchoolDto schoolDto);

    List<School> quesryB(SchoolDto schoolDto);

    Future<String> sendA(SchoolDto schoolDto);

    Future<String>  sendB(SchoolDto schoolDto);

}
