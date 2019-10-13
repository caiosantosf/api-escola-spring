
create database 4NYxV3JwRt;

use 4NYxV3JwRt;

create table professores(
	id int not null AUTO_INCREMENT,
    nome varchar(50),
    numeroRegistro int,
    email varchar(50),
    PRIMARY KEY (id));

create table materias(
	id int not null AUTO_INCREMENT,
    descricao varchar(50),
    quantidadeAulas int,
    professorId int not null,
    cursoId int not null,
    PRIMARY KEY (id));

create table cursos(
	id int not null AUTO_INCREMENT,
    descricao varchar(50),
    PRIMARY KEY (id));
    
create table turmas(
	id int not null AUTO_INCREMENT,
    descricao varchar(50),
    cursoId int not null,
    PRIMARY KEY (id));
    
create table alunos(
	id int not null AUTO_INCREMENT,
    nome varchar(50),
    email varchar(50),
    turmaId int not null,
    PRIMARY KEY (id)); 
 
ALTER TABLE materias ADD CONSTRAINT `fk_professor` FOREIGN KEY ( professorId ) REFERENCES professores ( id ) ;
ALTER TABLE materias ADD CONSTRAINT `fk_curso_materia` FOREIGN KEY ( cursoId ) REFERENCES cursos ( id ) ;
ALTER TABLE turmas ADD CONSTRAINT `fk_curso_turma` FOREIGN KEY ( cursoId ) REFERENCES cursos ( id ) ;
ALTER TABLE alunos ADD CONSTRAINT `fk_turma` FOREIGN KEY ( turmaId ) REFERENCES turmas ( id ) ;


INSERT INTO `professores` (`id`, `nome`, `numeroRegistro`, `email`) VALUES (NULL, 'Gabriel Divan', '348234', 'gdivan@hotmail.com');
INSERT INTO `professores` (`id`, `nome`, `numeroRegistro`, `email`) VALUES (NULL, 'João Carvalho', '123412', 'jcarvalho@gmail.com');

INSERT INTO `cursos` (`id`, `descricao`) VALUES (NULL, 'Curso de Java');

INSERT INTO `materias` (`id`, `descricao`, `quantidadeAulas`, `professorId`, `cursoId`) VALUES (NULL, 'Orientação a Objetos', '12', '1', '1');
INSERT INTO `materias` (`id`, `descricao`, `quantidadeAulas`, `professorId`, `cursoId`) VALUES (NULL, 'Spring', '8', '2', '1')

INSERT INTO `turmas` (`id`, `descricao`, `cursoId`) VALUES (NULL, 'Diurno', '1');
INSERT INTO `turmas` (`id`, `descricao`, `cursoId`) VALUES (NULL, 'Noturno', '2');

INSERT INTO `alunos` (`id`, `nome`, `email`, `turmaId`) VALUES (NULL, 'Caio Santos Freitas', 'caiosantos@gmail.com', '2');
INSERT INTO `alunos` (`id`, `nome`, `email`, `turmaId`) VALUES (NULL, 'Leila Germano', 'leilag@gmail.com', '2');

