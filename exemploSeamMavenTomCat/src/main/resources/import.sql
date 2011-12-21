-- Trechos
INSERT INTO trecho(id, destino, origem) VALUES (1, 'NOVA_IORQUE', 'SAO_PAULO');
INSERT INTO trecho(id, destino, origem) VALUES (2, 'SAO_PAULO', 'RIO_DE_JANEIRO');
-- Vôos do trecho acima
INSERT INTO voo(id, codigo, datachegada, datapartida, horachegada, horapartida, trecho_id) VALUES (1, '001', '2011-12-21', '2011-12-21', '12:00:00', '09:00:00', 1);
INSERT INTO voo(id, codigo, datachegada, datapartida, horachegada, horapartida, trecho_id) VALUES (2, '002', '2011-12-29', '2011-12-21', '13:00:00', '09:00:00', 1);
INSERT INTO voo(id, codigo, datachegada, datapartida, horachegada, horapartida, trecho_id) VALUES (3, '003', '2011-12-30', '2011-12-20', '11:00:00', '09:00:00', 2);