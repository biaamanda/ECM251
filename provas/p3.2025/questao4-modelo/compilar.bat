@echo off
rem Compila as classes e gera o .jar executavel (rode dentro desta pasta).
rem Precisa do JDK no PATH (javac e jar). Se "jar" nao for reconhecido, use o
rem caminho completo, ex.: "C:\Program Files\Java\jdk-25.0.2\bin\jar.exe"

if exist out rmdir /s /q out
mkdir out

javac -encoding UTF-8 -d out Main.java TelaPrincipal.java Checksum.java CryptoRSA.java Impressora.java
if errorlevel 1 goto erro

rem c = criar, f = nome do arquivo, e = ponto de entrada (Main-Class)
jar cfe App.jar Main -C out .
if errorlevel 1 goto erro

echo.
echo Gerado: App.jar   ^(execute com: java -jar App.jar  ^-- de DENTRO desta pasta^)
goto fim

:erro
echo.
echo *** Falhou. Veja as mensagens acima. ***

:fim
