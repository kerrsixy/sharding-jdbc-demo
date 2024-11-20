package com.zjp.shardingjdbcdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: DatabaseHintAlgorithm
 * Package: com.zjp.shadingjdbcdemo.strategy.database
 * Description:
 * 启动类
 * @author zjp
 * @version 1.0
 * @data: 2024/11/18 14:50
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zjp.shardingjdbcdemo.mapper")
public class ShardingJdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcDemoApplication.class, args);
    }

}
