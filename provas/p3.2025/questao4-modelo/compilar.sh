#!/bin/sh
# Compila as classes e gera o .jar executavel (rode dentro desta pasta).
set -e
rm -rf out && mkdir out
javac -encoding UTF-8 -d out Main.java TelaPrincipal.java Checksum.java CryptoRSA.java Impressora.java
jar cfe App.jar Main -C out .
echo "Gerado: App.jar  (execute com: java -jar App.jar -- de DENTRO desta pasta)"
