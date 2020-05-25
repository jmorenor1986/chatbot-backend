package co.com.santander.chatbot.accesodatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AccesoDatosApplication extends SpringBootServletInitializer {

    private static Class<AccesoDatosApplication> applicationClass = AccesoDatosApplication.class;

    public static void main(String... args) {
        SpringApplication.run(AccesoDatosApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
}
