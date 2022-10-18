#drop database nutrion;
#create database nutrion;
use nutrion;

INSERT INTO tb_usuarios
(nome_usuario, email_usuario, senha_usuario, telefone_usuario, autenticado, experiencia, tipo, avaliacao, dtype)
VALUES
('Lucas Henrique', 'henrique.adelino@gmail.com', 'lucas123', '11-95219-3268', FALSE, 0.0, 'P', 0.0, "UsuarioPaciente"),
('Victor Aquino', 'victor.aquino@gmail.com', 'victor1020', '11-95214-3256',FALSE, 0.0, 'P', 0.0, "UsuarioPaciente"),
('Victor Ferreira', 'victor.ferreira@gmail.com', 'ferreiraV10','11-96874-2541',FALSE, 0.0, 'P', 0.0, "UsuarioPaciente"),
('Pedro Altoe', 'pedro.atoe@gmail.com', 'pedrao2030', '11-5458-5478',FALSE, 0.0, 'P', 0.0, "UsuarioPaciente"),
('Kevin Dias', 'kevin.dias@gmail.com', 'kdias100','11-6547-5569',FALSE, 0.0, 'P', 0.0, "UsuarioPaciente");

INSERT INTO tb_usuarios
(nome_usuario, email_usuario, senha_usuario, telefone_usuario, autenticado, experiencia, tipo, avaliacao, dtype, categoria,
 data_nascimento, crn, especialidade)
VALUES
    ('Taciane Pinheiro', 'taci@gmail.com', 'taci1020','11-92541-5245',FALSE, 0.0, 'N', 5.0, "UsuarioNutri",
     'Sem Classificação', '25/02/1995','7485659', 'Esportiva'),
    ('Renato Cariane', 'rcariane@gmail.com', 'renato123','11-96589-8745',FALSE, 0.0, 'N', 5.0, "UsuarioNutri",
     'Sem Classificação', '30/07/1981', '6598452', 'Competição');

--CREATE TABLE acesso (
--    id_acesso int primary key auto_increment,
--    fk_usuario int ,
--    cadastro date,
--    ultimo_login date
--);
--
--insert into acesso values (null,2,'2022/10/11','2022/10/12');
--insert into acesso values (null,3,'2022/10/09','2022/10/12');
--insert into acesso values (null,4,'2022/10/08','2022/10/12');
--insert into acesso values (null,5,'2022/09/10','2022/09/12');
--insert into acesso values (null,6,'2022/09/11','2022/09/12');
--insert into acesso values (null,7,'2022/09/09','2022/09/12');
--insert into acesso values (null,8,'2022/09/08','2022/09/12');
--
