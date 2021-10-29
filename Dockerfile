# -- Dockerfile --
# 这个文件负责构建包含你的程序的 Docker 容器

# 使用 Java 8
FROM openjdk:15
# 向容器内复制文件
WORKDIR /app/
COPY ./ /app/
# 编译程序
RUN javac -cp antlr-4.9.2-complete.jar *.java