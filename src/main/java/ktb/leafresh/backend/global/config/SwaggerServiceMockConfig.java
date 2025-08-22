package ktb.leafresh.backend.global.config;

import ktb.leafresh.backend.domain.verification.infrastructure.cache.VerificationStatCacheService;
import ktb.leafresh.backend.domain.store.product.infrastructure.cache.ProductCacheService;
import ktb.leafresh.backend.global.util.redis.StockRedisLuaService;
import ktb.leafresh.backend.global.util.redis.VerificationStatRedisLuaService;
import lombok.extern.slf4j.Slf4j;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Swagger 문서 생성을 위한 서비스 레벨 Mock Bean 설정
 * Redis, 캐시 관련 서비스들을 Mock으로 대체
 */
@Slf4j
@Configuration
@Profile("swagger")
public class SwaggerServiceMockConfig {

    @Bean
    @Primary
    public VerificationStatCacheService mockVerificationStatCacheService() {
        log.info("🔧 Creating Mock VerificationStatCacheService for Swagger documentation");
        return Mockito.mock(VerificationStatCacheService.class);
    }

    @Bean
    @Primary
    public ProductCacheService mockProductCacheService() {
        log.info("🔧 Creating Mock ProductCacheService for Swagger documentation");
        return Mockito.mock(ProductCacheService.class);
    }

    @Bean
    @Primary
    public StockRedisLuaService mockStockRedisLuaService() {
        log.info("🔧 Creating Mock StockRedisLuaService for Swagger documentation");
        return Mockito.mock(StockRedisLuaService.class);
    }

    @Bean
    @Primary
    public VerificationStatRedisLuaService mockVerificationStatRedisLuaService() {
        log.info("🔧 Creating Mock VerificationStatRedisLuaService for Swagger documentation");
        return Mockito.mock(VerificationStatRedisLuaService.class);
    }
}
