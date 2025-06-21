package org.example.accident;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.accident.Mapper")
public class AccidentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccidentApplication.class, args);
    }

}
