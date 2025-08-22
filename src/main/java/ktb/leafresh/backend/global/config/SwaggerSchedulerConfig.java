package ktb.leafresh.backend.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Swagger 프로파일에서는 스케줄링을 비활성화
 */
@Slf4j
@Configuration
@Profile("!swagger")
@EnableScheduling
public class SwaggerSchedulerConfig {
    
    // swagger 프로파일이 아닐 때만 스케줄링 활성화
    public SwaggerSchedulerConfig() {
        log.info("📅 Scheduling enabled (not swagger profile)");
    }
}
