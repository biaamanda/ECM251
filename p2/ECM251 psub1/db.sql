CREATE DATABASE IF NOT EXISTS calculoFrete_db;
USE calculoFrete_db;

CREATE TABLE IF NOT EXISTS cliente (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nome      VARCHAR(100)  NOT NULL UNIQUE,
    taxa_manuseio DECIMAL(100,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS comodo (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT           NOT NULL,
    nome       VARCHAR(50)   NOT NULL,
    peso    DECIMAL(10,2) NOT NULL,
    real_peso    DECIMAL(100,2) NOT NULL,
    custo      DECIMAL(1000,2) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
);
