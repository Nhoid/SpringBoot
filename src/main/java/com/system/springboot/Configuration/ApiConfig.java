package com.system.springboot.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//TODO ESSE METODO PERMITE QUE INTERFACES DE FRONT-END DE DIFERENTES DOMINIOS POSSAM ACESSAR OS ENDPOINTS
//FIZ ISSO PRA PODER TESTAR O CODIGO COM O JAVASCRIPT
@Configuration
@EnableWebMvc
public class ApiConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Origin", "Content-Type", "Accept")
                .allowCredentials(false);
    }
}
