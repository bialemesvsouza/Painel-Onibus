-- Linhas
INSERT INTO linha (id, codigo, nome, origem, destino) VALUES
(1, '100', 'Centro - Bairro A', 'Terminal Central', 'Bairro A'),
(2, '200', 'Centro - Bairro B', 'Terminal Central', 'Bairro B');

-- Ônibus
INSERT INTO onibus (id, codigo, placa, modelo, capacidade, linha_id) VALUES
(1, '1001', 'ABC1234', 'Mercedes', 50, 1),
(2, '1002', 'DEF5678', 'Volvo', 50, 1),
(3, '2001', 'GHI9012', 'Marcopolo', 40, 2);

-- Paradas
INSERT INTO parada (id, codigo, nome, latitude, longitude, linha_id) VALUES
(1, 'P001', 'Terminal Central', -23.5505, -46.6333, 1),
(2, 'P002', 'Praça da Sé', -23.5507, -46.6335, 1),
(3, 'P003', 'Av. Paulista', -23.5614, -46.6559, 1),
(4, 'P004', 'Terminal Central', -23.5505, -46.6333, 2),
(5, 'P005', 'Shopping Center', -23.5550, -46.6400, 2);

-- Painéis
INSERT INTO painel (id, codigo, localizacao, parada_id) VALUES
(1, 'PN001', 'Terminal Central - Plataforma 1', 1),
(2, 'PN002', 'Praça da Sé - Lado Norte', 2),
(3, 'PN003', 'Av. Paulista - Sentido Centro', 3);
