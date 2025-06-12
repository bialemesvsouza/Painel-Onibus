-- Tabela LINHA
CREATE TABLE linha (
    id BIGINT PRIMARY KEY,
    codigo VARCHAR(20),
    nome VARCHAR(100),
    origem VARCHAR(100),
    destino VARCHAR(100)
);

-- Tabela ONIBUS
CREATE TABLE onibus (
    id BIGINT PRIMARY KEY,
    codigo VARCHAR(20),
    placa VARCHAR(10),
    modelo VARCHAR(50),
    capacidade INT,
    linha_id BIGINT,
    FOREIGN KEY (linha_id) REFERENCES linha(id)
);

-- Tabela PARADA
CREATE TABLE parada (
    id BIGINT PRIMARY KEY,
    codigo VARCHAR(20),
    nome VARCHAR(100),
    latitude DOUBLE,
    longitude DOUBLE,
    linha_id BIGINT,
    FOREIGN KEY (linha_id) REFERENCES linha(id)
);

-- Tabela PAINEL
CREATE TABLE painel (
    id BIGINT PRIMARY KEY,
    codigo VARCHAR(20),
    localizacao VARCHAR(150),
    parada_id BIGINT,
    FOREIGN KEY (parada_id) REFERENCES parada(id)
);
