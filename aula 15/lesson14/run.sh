#!/bin/sh
set -e

cd "$(dirname "$0")"

DRIVER_VERSION=8.4.0
DRIVER="lib/mysql-connector-j-$DRIVER_VERSION.jar"

if [ ! -f "$DRIVER" ]; then
    echo "Downloading the MySQL JDBC driver..."
    mkdir -p lib
    curl -fsSL -o "$DRIVER" \
        "https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/$DRIVER_VERSION/mysql-connector-j-$DRIVER_VERSION.jar"
fi

docker compose up -d --wait
javac -cp "$DRIVER" *.java
java -cp "$DRIVER:." Main
