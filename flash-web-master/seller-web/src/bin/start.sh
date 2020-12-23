#!/bin/sh

nohup java -Xmx512M -Xms512M -Xmn100M -XX:+DisableExplicitGC -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -XX:+UseFastAccessorMethods -jar lib/${project.artifactId}-${project.version}.jar >/dev/null 2>&1 &
echo $! > main.pid