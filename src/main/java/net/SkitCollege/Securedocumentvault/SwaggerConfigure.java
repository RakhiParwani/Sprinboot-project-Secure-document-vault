package net.SkitCollege.Securedocumentvault;

//import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfigure {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Document Vault API")
                        .version("1.0")
                        .description("API Documentation for Security Document Vault"))
                        .servers(Arrays.asList(new Server().url("http://localhost:8081").description("local"),
                                new Server().url("http://localhost:8082").description("live")
                        ))
                     .tags(Arrays.asList(new Tag().name("Auth API's"),
                             new Tag().name("User API's"),
                             new Tag().name("Admin API's"),
                             new Tag().name("Document API's"),
                             new Tag().name("Token API's"),
                            new Tag().name("Email API's")

                     ));

    }
}
