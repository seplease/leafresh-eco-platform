package ktb.leafresh.backend.global.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Swagger 문서 생성용 Mock TokenBlacklistService
 * Redis 없이 작동하며, 실제 토큰 블랙리스트 기능은 제공하지 않음
 */
@Slf4j
@Service
@Profile("swagger")
public class SwaggerMockTokenBlacklistService implements TokenBlacklistService {

    @Override
    public void blacklistAccessToken(String accessToken, long expirationTimeMillis) {
        log.info("[Mock] 토큰 블랙리스트 등록: token={}, TTL={}ms", accessToken, expirationTimeMillis);
        // Mock 구현: 실제로는 아무것도 하지 않음
    }

    @Override
    public boolean isBlacklisted(String accessToken) {
        log.info("[Mock] 토큰 블랙리스트 확인: token={}", accessToken);
        // Mock 구현: 항상 false 반환
        return false;
    }
}
