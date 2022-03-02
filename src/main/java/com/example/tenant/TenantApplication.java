package com.example.tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@EnableScheduling
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)

public class TenantApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenantApplication.class, args);
    }

}
