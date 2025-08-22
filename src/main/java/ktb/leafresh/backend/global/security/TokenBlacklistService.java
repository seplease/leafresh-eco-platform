package ktb.leafresh.backend.global.security;

public interface TokenBlacklistService {
    void blacklistAccessToken(String accessToken, long expirationTimeMillis);
    boolean isBlacklisted(String accessToken);
}
