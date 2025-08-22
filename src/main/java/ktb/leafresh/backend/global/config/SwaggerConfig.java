package ktb.leafresh.backend.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Leafresh API Documentation")
                .description("환경보호를 실천하는 사람들을 위한 웹진자 플랫폼 Leafresh의 백엔드 API 문서입니다.")
                .version("1.0.0"))
        .servers(List.of(
            new Server().url("https://springboot.dev-leafresh.app").description("프로덕션 서버"),
            new Server().url("https://api.leafresh.app").description("프로덕션 서버")
        ));
  }

  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
        .group("leafresh-api")
        .pathsToMatch("/**")
        .packagesToScan("ktb.leafresh.backend.domain")
        .build();
  }
}
