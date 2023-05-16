package com.springboot.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Springboot Blog Rest API Hands On",
                version = "1.0",
                description = "Springboot Blog Rest API Documentation",
                contact = @Contact(
                        name = "Cam",
                        email = "camilo.barros.c@gmail.com",
                        url = "https://github.com/cbarrosc"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Springboot Blog Rest API Hands On",
                url = "https://github.com/cbarroscc/springboot-blog-rest-api-hands-on")
)
public class SpringbootBlogRestApiHandsOnApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogRestApiHandsOnApplication.class, args);
    }

}
