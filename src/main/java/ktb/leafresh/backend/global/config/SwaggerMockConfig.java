package ktb.leafresh.backend.global.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.storage.Storage;
import ktb.leafresh.backend.domain.auth.application.dto.OAuthUserInfoDto;
import ktb.leafresh.backend.domain.auth.domain.entity.enums.OAuthProvider;
import ktb.leafresh.backend.domain.auth.infrastructure.client.KakaoProfileClient;
import ktb.leafresh.backend.domain.auth.infrastructure.client.KakaoTokenClient;
import ktb.leafresh.backend.domain.chatbot.infrastructure.client.AiChatbotBaseInfoClient;
import ktb.leafresh.backend.domain.chatbot.infrastructure.client.AiChatbotFreeTextClient;
import ktb.leafresh.backend.domain.feedback.infrastructure.client.FeedbackCreationClient;
import ktb.leafresh.backend.domain.feedback.infrastructure.publisher.AiFeedbackPublisher;
import ktb.leafresh.backend.domain.verification.infrastructure.client.AiVerificationClient;
import ktb.leafresh.backend.domain.verification.infrastructure.publisher.AiVerificationPublisher;
import ktb.leafresh.backend.global.security.TokenBlacklistService;
import lombok.extern.slf4j.Slf4j;
import org.mockito.Mockito;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * Swagger 문서 생성을 위한 Mock Bean 설정
 * 외부 의존성(DB, Redis, AWS, GCP 등)을 Mock으로 대체하여 
 * 스프링 컨텍스트가 정상적으로 시작될 수 있도록 함
 */
@Slf4j
@Configuration
@Profile("swagger")
public class SwaggerMockConfig {

    @Bean
    @Primary
    public RedisTemplate<String, Object> mockRedisTemplate() {
        log.info("🔧 Creating Mock RedisTemplate for Swagger documentation");
        return Mockito.mock(RedisTemplate.class);
    }

    @Bean
    @Primary
    public StringRedisTemplate mockStringRedisTemplate() {
        log.info("🔧 Creating Mock StringRedisTemplate for Swagger documentation");
        return Mockito.mock(StringRedisTemplate.class);
    }

    @Bean
    @Primary
    public RedissonClient mockRedissonClient() {
        log.info("🔧 Creating Mock RedissonClient for Swagger documentation");
        return Mockito.mock(RedissonClient.class);
    }

    @Bean
    @Primary
    public AmazonS3 mockAmazonS3() {
        log.info("🔧 Creating Mock AmazonS3 for Swagger documentation");
        return Mockito.mock(AmazonS3.class);
    }

    @Bean
    @Primary
    public AmazonSQS mockAmazonSQS() {
        log.info("🔧 Creating Mock AmazonSQS for Swagger documentation");
        return Mockito.mock(AmazonSQS.class);
    }

    @Bean
    @Primary
    public Storage mockGoogleCloudStorage() {
        log.info("🔧 Creating Mock Google Cloud Storage for Swagger documentation");
        return Mockito.mock(Storage.class);
    }

    @Bean
    @Primary
    public Publisher mockGoogleCloudPublisher() {
        log.info("🔧 Creating Mock Google Cloud Publisher for Swagger documentation");
        return Mockito.mock(Publisher.class);
    }

    @Bean
    @Primary
    public Subscriber mockGoogleCloudSubscriber() {
        log.info("🔧 Creating Mock Google Cloud Subscriber for Swagger documentation");
        return Mockito.mock(Subscriber.class);
    }

    // OAuth 관련 클라이언트 Mock
    @Bean
    @Primary
    public KakaoTokenClient mockKakaoTokenClient() {
        log.info("🔧 Creating Mock KakaoTokenClient for Swagger documentation");
        KakaoTokenClient mock = Mockito.mock(KakaoTokenClient.class);
        when(mock.getAccessToken(anyString(), anyString())).thenReturn("mock-access-token");
        return mock;
    }

    @Bean
    @Primary
    public KakaoProfileClient mockKakaoProfileClient() {
        log.info("🔧 Creating Mock KakaoProfileClient for Swagger documentation");
        KakaoProfileClient mock = Mockito.mock(KakaoProfileClient.class);
        when(mock.getUserProfile(anyString())).thenReturn(
            new OAuthUserInfoDto(
                OAuthProvider.KAKAO,
                "12345",
                "mock@example.com",
                "https://storage.googleapis.com/leafresh-images/init/user_icon.png",
                "MockUser"
            )
        );
        return mock;
    }

    // AI 관련 클라이언트 Mock
    @Bean
    @Primary
    public AiChatbotBaseInfoClient mockAiChatbotBaseInfoClient() {
        log.info("🔧 Creating Mock AiChatbotBaseInfoClient for Swagger documentation");
        return Mockito.mock(AiChatbotBaseInfoClient.class);
    }

    @Bean
    @Primary
    public AiChatbotFreeTextClient mockAiChatbotFreeTextClient() {
        log.info("🔧 Creating Mock AiChatbotFreeTextClient for Swagger documentation");
        return Mockito.mock(AiChatbotFreeTextClient.class);
    }

    @Bean
    @Primary
    public AiVerificationClient mockAiVerificationClient() {
        log.info("🔧 Creating Mock AiVerificationClient for Swagger documentation");
        return Mockito.mock(AiVerificationClient.class);
    }

    @Bean
    @Primary
    public FeedbackCreationClient mockFeedbackCreationClient() {
        log.info("🔧 Creating Mock FeedbackCreationClient for Swagger documentation");
        return Mockito.mock(FeedbackCreationClient.class);
    }

    // Publisher Mock
    @Bean
    @Primary
    public AiVerificationPublisher mockAiVerificationPublisher() {
        log.info("🔧 Creating Mock AiVerificationPublisher for Swagger documentation");
        return Mockito.mock(AiVerificationPublisher.class);
    }

    @Bean
    @Primary
    public AiFeedbackPublisher mockAiFeedbackPublisher() {
        log.info("🔧 Creating Mock AiFeedbackPublisher for Swagger documentation");
        return Mockito.mock(AiFeedbackPublisher.class);
    }

    // Token Blacklist Service Mock
    @Bean
    @Primary
    public TokenBlacklistService mockTokenBlacklistService() {
        log.info("🔧 Creating Mock TokenBlacklistService for Swagger documentation");
        TokenBlacklistService mock = Mockito.mock(TokenBlacklistService.class);
        when(mock.isBlacklisted(anyString())).thenReturn(false);
        return mock;
    }

    // WebClient Mock
    @Bean
    @Primary
    public WebClient mockWebClient() {
        log.info("🔧 Creating Mock WebClient for Swagger documentation");
        return Mockito.mock(WebClient.class);
    }

    @Bean(name = "chatbotWebClient")
    @Primary
    public WebClient mockChatbotWebClient() {
        log.info("🔧 Creating Mock ChatbotWebClient for Swagger documentation");
        return Mockito.mock(WebClient.class);
    }

    @Bean(name = "verificationWebClient")
    @Primary
    public WebClient mockVerificationWebClient() {
        log.info("🔧 Creating Mock VerificationWebClient for Swagger documentation");
        return Mockito.mock(WebClient.class);
    }

    @Bean(name = "feedbackWebClient")
    @Primary
    public WebClient mockFeedbackWebClient() {
        log.info("🔧 Creating Mock FeedbackWebClient for Swagger documentation");
        return Mockito.mock(WebClient.class);
    }
}
