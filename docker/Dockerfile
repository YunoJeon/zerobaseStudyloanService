# Java 기본 이미지 사용
FROM openjdk:22-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일을 컨테이너에 복사
COPY ../build/libs/loan-0.0.1-SNAPSHOT.jar app.jar

# 어플리케이션이 실행될 포트
EXPOSE 8080

# 어플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]

# 환경 변수 설정
ENV ENCRYPTION_KEY=1234567890123456