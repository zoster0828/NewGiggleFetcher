# Dockerfile

# MySQL 이미지를 기반으로 이미지 생성
FROM mysql:latest

# MySQL 설정
ENV MYSQL_ROOT_PASSWORD=1234
ENV MYSQL_DATABASE=giggle
ENV MYSQL_USER=gigglelabs
ENV MYSQL_PASSWORD=1234

# 포트 설정 (기본 MySQL 포트는 3306)
EXPOSE 3306

COPY ./schema /docker-entrypoint-initdb.d