#built java images
FROM registry.cn-shenzhen.aliyuncs.com/thinker-open/openjre:8

ARG JAR_FILE
ARG PROPERTIES

COPY ${JAR_FILE} /JAVA/app.jar

COPY ${PROPERTIES} /JAVA/application.yml

EXPOSE 8082

ENTRYPOINT exec java $JAVA_OPTS -jar /JAVA/app.jar