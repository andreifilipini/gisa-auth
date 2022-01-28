INSERT INTO user(id, name, login, password)
VALUES
(1, 'Usuário Associado', 'user.associado', '54d4de5d2010457068b60c41ca862afe04b5d9415e83b917734d2de2917c3ae3199d15346ce07661a120d637174859fa2e90d42b73ad9e55528ac04ea21694a0'),
(2, 'Usuário Conveniado', 'user.conveniado', '54d4de5d2010457068b60c41ca862afe04b5d9415e83b917734d2de2917c3ae3199d15346ce07661a120d637174859fa2e90d42b73ad9e55528ac04ea21694a0'),
(3, 'Usuário Prestador', 'user.prestador', '54d4de5d2010457068b60c41ca862afe04b5d9415e83b917734d2de2917c3ae3199d15346ce07661a120d637174859fa2e90d42b73ad9e55528ac04ea21694a0');

INSERT INTO role(id, user_id, name)
VALUES
(1, (SELECT id FROM user WHERE login = 'user.associado'), 'ASSOCIADO'),
(2, (SELECT id FROM user WHERE login = 'user.conveniado'), 'CONVENIADO'),
(3, (SELECT id FROM user WHERE login = 'user.prestador'), 'PRESTADOR');
