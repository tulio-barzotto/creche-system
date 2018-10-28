CREATE TABLE `funcionario` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`nome` varchar(20) NOT NULL,
	`sobrenome` varchar(50) NOT NULL,
	`email` varchar(50) NOT NULL,
	`cpf` varchar(11) NOT NULL UNIQUE,
	`login` varchar(50) NOT NULL UNIQUE,
	`password` varchar(100) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `turma` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`nome` varchar(100) NOT NULL UNIQUE,
	`lotacao_maxima` int NOT NULL,
	`minimo_meses` int NOT NULL,
	`maximo_meses` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `aluno` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`nome` varchar(100) NOT NULL UNIQUE,
	`nascimento` DATE NOT NULL,
	`responsavel_aluno_id` bigint NOT NULL,
	`turma_id` bigint NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `responsavel` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`nome` varchar(100) NOT NULL UNIQUE,
	`telefone` int(9) NOT NULL,
	`ddd_telefone` int(2) NOT NULL,
	`endereco` varchar(100) NOT NULL,
	`municipio` varchar(100) NOT NULL,
	`uf` varchar(2) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `responsavel_aluno` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`responsavel_mae_id` bigint NOT NULL,
	`responsavel_pai_id` bigint,
	PRIMARY KEY (`id`)
);

ALTER TABLE `aluno` ADD CONSTRAINT `aluno_fk0` FOREIGN KEY (`responsavel_aluno_id`) REFERENCES `responsavel_aluno`(`id`);

ALTER TABLE `aluno` ADD CONSTRAINT `aluno_fk1` FOREIGN KEY (`turma_id`) REFERENCES `turma`(`id`);

ALTER TABLE `responsavel_aluno` ADD CONSTRAINT `responsavel_aluno_fk0` FOREIGN KEY (`responsavel_mae_id`) REFERENCES `responsavel`(`id`);

ALTER TABLE `responsavel_aluno` ADD CONSTRAINT `responsavel_aluno_fk1` FOREIGN KEY (`responsavel_pai_id`) REFERENCES `responsavel`(`id`);

