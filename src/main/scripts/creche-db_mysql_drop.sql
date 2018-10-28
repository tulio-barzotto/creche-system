ALTER TABLE `aluno` DROP FOREIGN KEY `aluno_fk0`;

ALTER TABLE `aluno` DROP FOREIGN KEY `aluno_fk1`;

ALTER TABLE `responsavel_aluno` DROP FOREIGN KEY `responsavel_aluno_fk0`;

ALTER TABLE `responsavel_aluno` DROP FOREIGN KEY `responsavel_aluno_fk1`;

DROP TABLE IF EXISTS `funcionario`;

DROP TABLE IF EXISTS `turma`;

DROP TABLE IF EXISTS `aluno`;

DROP TABLE IF EXISTS `responsavel`;

DROP TABLE IF EXISTS `responsavel_aluno`;

