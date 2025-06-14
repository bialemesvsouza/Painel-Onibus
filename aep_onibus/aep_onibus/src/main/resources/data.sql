-- Linhas
INSERT INTO linha (id, codigo, nome, origem, destino) VALUES
(1, '100', 'Centro - Bairro A', 'Terminal Central', 'Bairro A'),
(2, '200', 'Centro - Bairro B', 'Terminal Central', 'Bairro B'),
(3, '300', 'Centro - Bairro C', 'Terminal Central', 'Bairro C');

-- Ônibus
INSERT INTO onibus (id, codigo, placa, modelo, capacidade, linha_id) VALUES
(1, '1001', 'ABC1234', 'Mercedes', 50, 1),
(2, '1002', 'DEF5678', 'Volvo', 50, 1),
(3, '2001', 'GHI9012', 'Marcopolo', 40, 2),
(4, '3001', 'JKL3456', 'Scania', 45, 3),
(5, '3002', 'MNO7890', 'Volvo', 45, 3);

-- Paradas
INSERT INTO parada (id, codigo, nome, latitude, longitude, linha_id) VALUES
(1, 'P001', 'Terminal Central', -23.5505, -46.6333, 1),
(2, 'P002', 'Praça da Sé', -23.5507, -46.6335, 1),
(3, 'P003', 'Av. Paulista', -23.5614, -46.6559, 1),
(4, 'P004', 'Terminal Central', -23.5505, -46.6333, 2),
(5, 'P005', 'Shopping Center', -23.5550, -46.6400, 2),
(6, 'P006', 'Terminal Central', -23.5505, -46.6333, 3),
(7, 'P007', 'Parque das Flores', -23.5600, -46.6500, 3),
(8, 'P008', 'Bairro C', -23.5650, -46.6600, 3);

-- Painéis
INSERT INTO painel (id, codigo, localizacao, parada_id) VALUES
(1, 'PN001', 'Terminal Central - Plataforma 1', 1),
(2, 'PN002', 'Praça da Sé - Lado Norte', 2),
(3, 'PN003', 'Av. Paulista - Sentido Centro', 3),
(4, 'PN004', 'Terminal Central - Plataforma 2', 6),
(5, 'PN005', 'Parque das Flores - Ponto Central', 7),
(6, 'PN006', 'Bairro C - Final de Linha', 8);
