#!/bin/bash
mkdir -p /opt/newrelic
cp /tmp/newrelic-agent-6.4.1.jar /opt/newrelic/newrelic.jar
cp /tmp/newrelic.yml /tmp/newrelic.yml.orig
cat /tmp/newrelic.yml.orig  | sed "s/app_name:.*/app_name: integer-to-roman/"  | sed "s/license_key:.*/license_key: 166b2c322e55d46d40b15bb6e029225417faNRAL/" > /opt/newrelic/newrelic.yml
#cat /opt/newrelic/newrelic.yml
java -Xms512m -Xmx1024m -javaagent:/opt/newrelic/newrelic.jar -jar app.jar