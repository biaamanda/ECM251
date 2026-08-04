-- Script de criação do Banco de Dados usado pela aplicação CEM
-- Executar no MySQL antes de rodar a aplicação (ex.: mysql -u root -p < schema.sql)

CREATE DATABASE IF NOT EXISTS cem_db;
USE cem_db;

CREATE TABLE IF NOT EXISTS cliente (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nome      VARCHAR(100)  NOT NULL UNIQUE,
    valor_kwh DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS comodo (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT           NOT NULL,
    nome       VARCHAR(50)   NOT NULL,
    consumo    DECIMAL(10,2) NOT NULL,
    custo      DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
);
