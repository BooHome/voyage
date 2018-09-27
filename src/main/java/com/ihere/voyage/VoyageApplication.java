package com.ihere.voyage;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author fengshibo
 */
@SpringBootApplication
@EnableScheduling
@EnableSwagger2Doc
@MapperScan("com.ihere.voyage.mapper")
public class VoyageApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoyageApplication.class, args);
    }
}
