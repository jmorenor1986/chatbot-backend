package co.com.santander.chatbot.acceso.recursos.config;


import feign.Logger;
import lombok.extern.slf4j.XSlf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@XSlf4j
@Configuration
@EnableFeignClients(basePackages = "co.com.santander.chatbot.acceso.recursos.clients")
public class FeignConfig {
    @Bean
    public ModelMapper getMapper(){
        return new ModelMapper();
    }

    public FeignConfig() {
        log.entry(FeignConfig.class.getName());
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}