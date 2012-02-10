-- Trechos
INSERT INTO trecho(id, destino, origem) VALUES (1, 'NOVA_IORQUE', 'SAO_PAULO');
INSERT INTO trecho(id, destino, origem) VALUES (2, 'SAO_PAULO', 'RIO_DE_JANEIRO');
-- Vôos do trecho acima
INSERT INTO voo(id, codigo, datachegada, datapartida, horachegada, horapartida, trecho_id) VALUES (1, 'HDI152', '2012-05-21', '2012-05-21', '12:00:00', '09:00:00', 1);
INSERT INTO voo(id, codigo, datachegada, datapartida, horachegada, horapartida, trecho_id) VALUES (2, 'YRI233', '2012-05-29', '2012-05-29', '13:00:00', '09:00:00', 1);
INSERT INTO voo(id, codigo, datachegada, datapartida, horachegada, horapartida, trecho_id) VALUES (3, 'CVGD23', '2012-05-30', '2012-05-30', '11:00:00', '09:00:00', 2);