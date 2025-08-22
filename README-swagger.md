# 📚 Leafresh API Documentation

환경보호를 실천하는 사람들을 위한 챌린지 플랫폼 **Leafresh**의 백엔드 API 문서입니다.

## 🌐 배포된 문서 보기

- **[📖 Swagger UI](https://100-hours-a-week.github.io/15-Leafresh-BE/)** - 대화형 API 문서
- **[📄 OpenAPI YAML](https://100-hours-a-week.github.io/15-Leafresh-BE/swagger.yaml)** - 원본 스키마 파일

## 🚀 자동 배포 시스템

이 프로젝트는 두 가지 GitHub Actions 워크플로우로 자동 배포됩니다:

### 1. API 문서 업데이트 (`swagger-deploy.yml`)
- **트리거**: `main`, `develop` 브랜치에 push할 때
- **동작**:
  1. Spring Boot 애플리케이션을 빌드하고 실행
  2. `/v3/api-docs` 엔드포인트에서 OpenAPI 스펙 다운로드
  3. JSON을 YAML 형식으로 변환하여 `swagger.yaml` 업데이트
  4. GitHub Pages에 배포

### 2. Swagger UI 자동 업데이트 (`update-swagger.yml`)
- **트리거**: 매일 오전 10시 (UTC) 또는 수동 실행
- **동작**:
  1. Swagger UI 최신 릴리스 확인
  2. 버전이 다르면 새 버전 다운로드
  3. 설정 파일들을 프로젝트에 맞게 수정
  4. Pull Request 생성

## 📁 파일 구조

```
├── swagger.yaml           # OpenAPI 스펙 (자동 생성/업데이트)
├── index.html            # Swagger UI 메인 페이지
├── swagger-ui.version    # 현재 Swagger UI 버전
├── dist/                 # Swagger UI 정적 파일들 (자동 다운로드)
│   ├── swagger-ui.css
│   ├── swagger-ui-bundle.js
│   └── ...
└── .github/workflows/
    ├── swagger-deploy.yml    # API 문서 배포
    └── update-swagger.yml    # Swagger UI 업데이트
```

## 🛠️ 로컬 개발

### 1. API 문서 로컬에서 보기

```bash
# 애플리케이션 실행 (swagger 프로파일)
./gradlew bootRun --args='--spring.profiles.active=swagger'

# 브라우저에서 접속
open http://localhost:8080/swagger-ui.html
```

### 2. 수동으로 swagger.yaml 업데이트

```bash
# 애플리케이션 실행 후
curl -o swagger.json http://localhost:8080/v3/api-docs

# JSON을 YAML로 변환 (yq 설치 필요)
yq eval -P swagger.json > swagger.yaml
```

### 3. Swagger UI 수동 업데이트

```bash
# GitHub Actions 워크플로우 수동 실행
gh workflow run update-swagger.yml
```

## 🎨 커스터마이징

### 브랜딩
- `index.html`에서 색상 테마 수정 가능
- 현재 Leafresh 브랜드 컬러 (`#2d5a27`) 적용됨

### 설정 옵션
- `tryItOutEnabled`: API 테스트 기능 활성화
- `filter`: API 검색 기능 활성화
- `supportedSubmitMethods`: 지원하는 HTTP 메서드

## 🔧 문제 해결

### GitHub Pages 설정
1. Repository Settings > Pages
2. Source: Deploy from a branch
3. Branch: `gh-pages`
4. Folder: `/ (root)`

### 빌드 실패 시
- Actions 탭에서 빌드 로그 확인
- Spring Boot 애플리케이션이 정상 시작되는지 확인
- `/v3/api-docs` 엔드포인트가 응답하는지 확인

## 🌱 About Leafresh

Leafresh는 개인과 그룹이 함께 지속가능한 생활습관을 만들어가며, AI 기술로 활동을 검증하고 보상을 제공하는 환경보호 챌린지 플랫폼입니다.

### 주요 기능
- 🔐 **인증 시스템**: JWT 기반 사용자 인증
- 👤 **멤버 관리**: 프로필 및 계정 관리
- 🏆 **챌린지**: 개인/그룹 챌린지 생성 및 참여
- ✅ **AI 검증**: 이미지 기반 활동 검증
- 🛒 **포인트 상점**: 보상 시스템
- 🤖 **챗봇**: AI 기반 개인화 추천

---

📧 문의사항이 있으시면 [Leafresh 팀](https://leafresh.app)에 연락해주세요.
