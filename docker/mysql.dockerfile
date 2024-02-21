# Dockerfile

FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=1234
ENV MYSQL_DATABASE=giggle
ENV MYSQL_USER=gigglelabs
ENV MYSQL_PASSWORD=1234

COPY my.cnf /etc/mysql/conf.d/

EXPOSE 3306

COPY ./schema /docker-entrypoint-initdb.d
