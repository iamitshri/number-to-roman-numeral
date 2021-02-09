FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN echo "I am in $PWD"
RUN curl -SL https://download.newrelic.com/newrelic/java-agent/newrelic-agent/6.4.1/newrelic-agent-6.4.1.jar -o /tmp/newrelic-agent-6.4.1.jar && \
    curl -SL https://download.newrelic.com/newrelic/java-agent/newrelic-agent/6.4.1/newrelic.yml -o /tmp/newrelic.yml
COPY run.sh /
RUN chmod +x /run.sh
CMD ["/run.sh"]
