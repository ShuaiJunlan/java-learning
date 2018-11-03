package cn.shuaijunlan.testmarkdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestmarkdownApplication {
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new HessianServiceImpl(), "/test");
    }

    public static void main(String[] args) {
        SpringApplication.run(TestmarkdownApplication.class, args);
    }
}
