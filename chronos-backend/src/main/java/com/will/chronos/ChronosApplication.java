package com.will.chronos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: chronos
 * @description: 启动类
 * @author: Mr.Zhang
 * @create: 2025-04-29 19:46
 **/

@SpringBootApplication
@MapperScan("com.will.chronos.mapper")
public class ChronosApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(ChronosApplication.class, args);
    }
}
