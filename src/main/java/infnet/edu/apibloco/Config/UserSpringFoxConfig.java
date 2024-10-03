package infnet.edu.apibloco.Config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class UserSpringFoxConfig {
@Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("user")
                .packagesToScan("infnet.edu.apibloco.Controllers")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("user API")
                        .description("user API")
                        .version("1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
