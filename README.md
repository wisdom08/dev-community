# dev-community

## 실행 방법
- [MySQL 설치](https://dev.mysql.com/downloads/installer/)
- 데이터베이스에 'community' 스키마 생성
```
// application.yaml 참고
datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/community
```
- 스프링부트 프로젝트 실행
```
gradlew bootRun
```

## Retrospective
- [Iteration](https://github.com/wisdom08/dev-community/blob/develop/doc/retropective/retrospective.md)

## WIKI
[해당 프로젝트에 대한 상세 내용은 위키를 참고해주세요.](https://github.com/wisdom08/dev-community/wiki)

## 개발 환경
- Intellij IDEA Ultimate
- Spring Boot 2.7.2
- Java 17
- Gradle 7.5
