package xyz.kbws.aicodegenbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class AiCodeGenBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCodeGenBackendApplication.class, args);
    }

}
