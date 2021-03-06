package com.example.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by Administrator on 2017/2/24.
 *
 * 对于，请求的内容，需要应答，例如我们需要在多个方法调用都完成后，
 * 才进行接下来的操作，此时我们可以利用 Java 的 Future-Listener 机制来实现异步服务调用。
 */
@Service
public class MsgFutureServer {

    @Async
    public Future<String> sendA() throws Exception {
        System.out.println("send A");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(2000);
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime));
        return new AsyncResult<String>("success");
    }

    @Async
    public Future<String> sendB() throws Exception {
        System.out.println("send B");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(2000);
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime));
        return new AsyncResult<String>("success");
    }
}
