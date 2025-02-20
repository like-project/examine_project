# 使用官方的OpenJDK镜像作为基础镜像
FROM openjdk:8-jre-slim

# 设置工作目录
WORKDIR /Users/like/Desktop/develop/workspace

# 复制应用程序的Jar文件到容器中
COPY target/docker-project.jar /Users/like/Desktop/develop/workspace/docker-project.jar

# 暴露端口
EXPOSE 8080

# 运行应用程序
ENTRYPOINT ["java", "-jar", "/app/your-app.jar"]