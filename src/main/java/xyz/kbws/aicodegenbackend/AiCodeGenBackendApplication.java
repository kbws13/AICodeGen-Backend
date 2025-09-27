package xyz.kbws.aicodegenbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan("xyz.kbws.aicodegenbackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class AiCodeGenBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCodeGenBackendApplication.class, args);
    }

}
