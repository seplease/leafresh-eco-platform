#!/bin/bash

# Leafresh API Swagger 문서 생성 및 실행 스크립트
echo "🌱 Leafresh API Swagger 문서를 생성합니다..."

# 빌드
echo "📦 애플리케이션 빌드 중..."
./gradlew clean bootJar -x test --quiet

if [ $? -eq 0 ]; then
    echo "✅ 빌드 완료!"
    echo ""
    echo "🚀 Swagger 문서 서버 시작 중..."
    echo "📖 Swagger UI: http://localhost:8080/swagger-ui.html"
    echo "📄 API Docs JSON: http://localhost:8080/v3/api-docs"
    echo ""
    echo "서버를 중지하려면 Ctrl+C를 누르세요."
    echo "================================================="
    
    # swagger 프로필로 실행
    java -Dspring.profiles.active=swagger \
         -Dserver.port=8080 \
         -Dspring.main.banner-mode=off \
         -jar build/libs/backend-0.0.1-SNAPSHOT.jar
else
    echo "❌ 빌드 실패!"
    exit 1
fi
