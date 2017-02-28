package com.example.service;

import com.example.dao.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/2/24.
 *
 * 多个服务之间逻辑上不存在相互依赖关系，
 * 执行先后顺序没有严格的要求，逻辑上可以被并行执行。
 * 对于单发服务只有请求，没有应答，很容易设计成异步的。发起服务调用后。立即返回，不需要同步阻塞等待应答。
 */
@Service
public class MsgServer {

    @Async
    public void sendA() throws Exception {
        System.out.println("send A");
        Long startTime = System.currentTimeMillis();
        System.out.println("a开始进入等待");
        Thread.sleep(2000);
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime));
    }

    @Async
    public void sendB() throws Exception {
        System.out.println("send B");
        Long startTime = System.currentTimeMillis();
        System.out.println("b开始进入等待");;
        Thread.sleep(2000);
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime));
    }

}
