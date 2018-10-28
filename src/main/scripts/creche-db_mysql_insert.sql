--FUNCIONÁRIOS
INSERT INTO `creche-db`.`funcionario` (`nome`, `sobrenome`, `email`, `cpf`, `login`, `password`) VALUES ('Maria', 'Silva Educardora', 'maria@creche.com.br', '21674350880', 'maria.silva', '$2a$10$ncsRbv1fQoV.Xq5ua9fbOeab7Jk3LbOeU9b0qy4H33vflOX7TSWQm');
INSERT INTO `creche-db`.`funcionario` (`nome`, `sobrenome`, `email`, `cpf`, `login`, `password`) VALUES ('Carlos', 'Ciclano Educador', 'carlos@creche.com.br', '07474658607', 'carlos.ciclano', '$2a$10$ncsRbv1fQoV.Xq5ua9fbOeab7Jk3LbOeU9b0qy4H33vflOX7TSWQm');
INSERT INTO `creche-db`.`funcionario` (`nome`, `sobrenome`, `email`, `cpf`, `login`, `password`) VALUES ('Julia', 'Menezes Educadora', 'julia@creche.com.br', '86530335560', 'julia.menezes', '$2a$10$ncsRbv1fQoV.Xq5ua9fbOeab7Jk3LbOeU9b0qy4H33vflOX7TSWQm');
--TURMAS
INSERT INTO `creche-db`.turma (nome, lotacao_maxima, minimo_meses, maximo_meses) VALUES ('Berçário I', 10, 3, 12);
INSERT INTO `creche-db`.turma (nome, lotacao_maxima, minimo_meses, maximo_meses) VALUES ('Berçário II', 10, 13, 24);
INSERT INTO `creche-db`.turma (nome, lotacao_maxima, minimo_meses, maximo_meses) VALUES ('Maternal I', 10, 25, 36);
INSERT INTO `creche-db`.turma (nome, lotacao_maxima, minimo_meses, maximo_meses) VALUES ('Maternal II', 10, 37, 48);
INSERT INTO `creche-db`.turma (nome, lotacao_maxima, minimo_meses, maximo_meses) VALUES ('Pré-Escola I', 10, 49, 60);
INSERT INTO `creche-db`.turma (nome, lotacao_maxima, minimo_meses, maximo_meses) VALUES ('Pré-Escola II', 10, 61, 72);
--RESPONSÁVEL
INSERT INTO `creche-db`.`responsavel` (`nome`, `telefone`, `ddd_telefone`, `endereco`, `municipio`, `uf`) VALUES ('Tulio', 991919191, 51, 'Av Bento Gonçalves', 'Porto Alegre', 'RS');
INSERT INTO `creche-db`.`responsavel` (`nome`, `telefone`, `ddd_telefone`, `endereco`, `municipio`, `uf`) VALUES ('Juliana', 991919191, 51, 'Av Bento Gonçalves', 'Porto Alegre', 'RS');
INSERT INTO `creche-db`.`responsavel` (`nome`, `telefone`, `ddd_telefone`, `endereco`, `municipio`, `uf`) VALUES ('Mãe solteira', 988112233, 51, 'Av Assis Brasil', 'Porto Alegre', 'RS');
INSERT INTO `creche-db`.`responsavel` (`nome`, `telefone`, `ddd_telefone`, `endereco`, `municipio`, `uf`) VALUES ('João', 982828282, 51, 'Av Ceará', 'Porto Alegre', 'RS');
INSERT INTO `creche-db`.`responsavel` (`nome`, `telefone`, `ddd_telefone`, `endereco`, `municipio`, `uf`) VALUES ('Maria', 995949192, 51, 'Av Ceará', 'Porto Alegre', 'RS');
--RESPONSAVEL_ALUNO
INSERT INTO `creche-db`.`responsavel_aluno` (`responsavel_mae_id`, `responsavel_pai_id`)
	VALUES ((SELECT ID FROM `creche-db`.`responsavel` WHERE NOME like 'Juliana'),
          (SELECT ID FROM `creche-db`.`responsavel` WHERE NOME like 'Tulio'));
INSERT INTO `creche-db`.`responsavel_aluno` (`responsavel_mae_id`, `responsavel_pai_id`)
	VALUES ((SELECT ID FROM `creche-db`.`responsavel` WHERE NOME like 'Mãe solteira'),
			null);
INSERT INTO `creche-db`.`responsavel_aluno` (`responsavel_mae_id`, `responsavel_pai_id`)
	VALUES ((SELECT ID FROM `creche-db`.`responsavel` WHERE NOME like 'Maria'),
          (SELECT ID FROM `creche-db`.`responsavel` WHERE NOME like 'João'));
--ALUNO
INSERT INTO `creche-db`.`aluno` (`nome`, `nascimento`, `responsavel_aluno_id`, `turma_id`)
VALUES ('Bento', '2018-05-31', (select ra.id
                                    from `creche-db`.`responsavel` r
                                           inner join `creche-db`.`responsavel_aluno` ra on r.id = ra.responsavel_mae_id
                                    where r.nome like 'Juliana'), 1);
INSERT INTO `creche-db`.`aluno` (`nome`, `nascimento`, `responsavel_aluno_id`, `turma_id`)
VALUES ('Francisco', '2017-06-10', (select ra.id
                                        from `creche-db`.`responsavel` r
                                               inner join `creche-db`.`responsavel_aluno` ra
                                                 on r.id = ra.responsavel_mae_id
                                        where r.nome like 'Mãe solteira'), 2);
INSERT INTO `creche-db`.`aluno` (`nome`, `nascimento`, `responsavel_aluno_id`, `turma_id`)
VALUES ('Manuela', '2016-07-25', (select ra.id
                                      from `creche-db`.`responsavel` r
                                             inner join `creche-db`.`responsavel_aluno` ra
                                               on r.id = ra.responsavel_mae_id
                                      where r.nome like 'Maria'), 3);
INSERT INTO `creche-db`.`aluno` (`nome`, `nascimento`, `responsavel_aluno_id`, `turma_id`)
VALUES ('Clara', '2018-07-02', (select ra.id
                                    from `creche-db`.`responsavel` r
                                           inner join `creche-db`.`responsavel_aluno` ra on r.id = ra.responsavel_mae_id
                                    where r.nome like 'Maria'), 1);