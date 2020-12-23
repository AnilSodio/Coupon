#!/bin/sh

java -Xmx512M -Xms512M -Xmn200M -XX:+UseParallelOldGC -XX:-UseAdaptiveSizePolicy -XX:SurvivorRatio=3 -jar lib/${project.artifactId}-${project.version}.jar >/dev/null 2>&1 &
echo $! > main.pid