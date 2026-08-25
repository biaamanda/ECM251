# Lesson 14 - Login com MySQL

Tela de login que valida usuario e senha na tabela `users` do MySQL. Depois de
autenticado, o usuario ve sua nota e suas faltas em uma tabela.

## Requisitos

- Docker
- JDK 17 ou superior

## Como executar

```
./lesson14/run.sh
```

O script baixa o driver JDBC em `lib/` na primeira vez, sobe o MySQL, compila e abre a tela.
