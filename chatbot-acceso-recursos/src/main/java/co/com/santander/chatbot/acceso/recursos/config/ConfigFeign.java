package co.com.santander.chatbot.acceso.recursos.config;


import feign.Logger;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@XSlf4j
@Configuration
@EnableFeignClients(basePackages = "co.com.santander.chatbot.acceso.recursos.clients")
public class ConfigFeign {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}