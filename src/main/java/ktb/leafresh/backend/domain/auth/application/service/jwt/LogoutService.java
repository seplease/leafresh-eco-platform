package ktb.leafresh.backend.domain.auth.application.service.jwt;

public interface LogoutService {
    void logout(String accessToken, String refreshToken);
}
