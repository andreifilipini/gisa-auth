INSERT INTO gisaauthdb.user(name, login, password)
VALUES
('Usuário Associado', 'user.associado', '54d4de5d2010457068b60c41ca862afe04b5d9415e83b917734d2de2917c3ae3199d15346ce07661a120d637174859fa2e90d42b73ad9e55528ac04ea21694a0'),
('Usuário Conveniado', 'user.conveniado', '54d4de5d2010457068b60c41ca862afe04b5d9415e83b917734d2de2917c3ae3199d15346ce07661a120d637174859fa2e90d42b73ad9e55528ac04ea21694a0'),
('Usuário Prestador', 'user.prestador', '54d4de5d2010457068b60c41ca862afe04b5d9415e83b917734d2de2917c3ae3199d15346ce07661a120d637174859fa2e90d42b73ad9e55528ac04ea21694a0');

INSERT INTO gisaauthdb.role(user_id, name)
VALUES
((SELECT id FROM gisaauthdb.user WHERE login = 'user.associado'), 'ASSOCIADO'),
((SELECT id FROM gisaauthdb.user WHERE login = 'user.conveniado'), 'CONVENIADO'),
((SELECT id FROM gisaauthdb.user WHERE login = 'user.prestador'), 'PRESTADOR');
