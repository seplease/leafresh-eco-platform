#!/bin/bash

# Leafresh API Documentation Generator
# 로컬에서 OpenAPI 스펙을 생성하고 Swagger UI를 실행하는 스크립트

set -e

echo "🌱 Leafresh API Documentation Generator"
echo "======================================"

# 프로젝트 루트로 이동
cd "$(dirname "$0")/.."

echo "🔧 Building application..."
./gradlew clean build -x test --no-daemon

echo "🚀 Starting application with Swagger profile..."

# JAR 파일로 애플리케이션 실행 (더 안정적)
java -Dspring.profiles.active=swagger \
     -Dserver.port=8080 \
     -Dspring.datasource.url=jdbc:h2:mem:testdb \
     -Dspring.datasource.driver-class-name=org.h2.Driver \
     -Dspring.jpa.hibernate.ddl-auto=create-drop \
     -Djwt.secret=dGVzdC1zZWNyZXQta2V5LWZvci1zd2FnZ2VyLWRvY3VtZW50YXRpb24tZ2VuZXJhdGlvbg== \
     -Dkakao.client-id=dummy \
     -Dkakao.client-secret=dummy \
     -jar build/libs/*.jar > swagger-app.log 2>&1 &
APP_PID=$!

echo "⏳ Waiting for application to start..."

# 애플리케이션 시작 대기 (더 긴 타임아웃)
STARTED=false
for i in {1..40}; do
    if curl -s http://localhost:8080/actuator/health >/dev/null 2>&1; then
        echo "✅ Application started successfully after $((i*3)) seconds!"
        STARTED=true
        break
    fi
    echo "Waiting... ($i/40)"
    sleep 3
done

# 시작 확인
if [ "$STARTED" = false ]; then
    echo "❌ Application failed to start!"
    echo "📋 Application logs:"
    cat swagger-app.log
    kill $APP_PID 2>/dev/null || true
    exit 1
fi

# 추가 대기 (완전한 초기화)
echo "⌛ Waiting for full initialization..."
sleep 5

# OpenAPI 스펙 다운로드 (선택사항)
echo "📥 Downloading OpenAPI specification to docs/openapi.yaml..."
if curl -f http://localhost:8080/v3/api-docs -o openapi-temp.json; then
    # JSON을 YAML로 변환
    python3 << 'EOF'
import json
import yaml

# JSON 파일 읽기
with open('openapi-temp.json', 'r', encoding='utf-8') as f:
    data = json.load(f)

# YAML 파일로 저장
with open('docs/openapi.yaml', 'w', encoding='utf-8') as f:
    yaml.dump(data, f, default_flow_style=False, allow_unicode=True, sort_keys=False)

print("✅ OpenAPI YAML updated successfully!")
EOF
    rm -f openapi-temp.json
    echo "📊 API endpoints found: $(python3 -c "import yaml; data=yaml.safe_load(open('docs/openapi.yaml')); print(len(data.get('paths', {})))")"
fi

echo ""
echo "🎉 Swagger UI is now available!"
echo "📖 Swagger UI: http://localhost:8080/swagger-ui.html"
echo "📄 API Docs JSON: http://localhost:8080/v3/api-docs"
echo "📁 Static docs: file://$(pwd)/docs/index.html"
echo ""
echo "💡 Press Ctrl+C to stop the application"

# 사용자가 Ctrl+C를 누를 때까지 대기
trap "echo ''; echo '🛑 Stopping application...'; kill $APP_PID 2>/dev/null || true; rm -f swagger-app.log; echo '✅ Done!'; exit 0" INT

# 백그라운드 프로세스 대기
wait $APP_PID
