-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;



insert into categoria
(id, nome)
values
(1, 'Social Media');

insert into categoria
(id, nome)
values
(2, 'Currículo');

insert into categoria
(id, nome)
values
(3, 'Apresentação');

insert into criador
(id, nome)
values
(1, 'Samuell Handerson');

insert into criador
(id, nome)
values
(2, 'Luara Sipaúba');

insert into criador
(id, nome)
values
(3, 'Rafael Junior');


insert into template
(id, nome, descricao, formato, preco, imagemurl, ativo, id_categoria, id_autor)
values
(1,
 'Template Instagram',
 'Template para posts de Instagram',
 'Canva',
 29.90,
 'https://templatify.com/instagram.jpg',
 true,
 1,
 1);

insert into template
(id, nome, descricao, formato, preco, imagemurl, ativo, id_categoria, id_autor)
values
(2,
 'Template Currículo',
 'Currículo profissional editável',
 'Word',
 19.90,
 'https://templatify.com/curriculo.jpg',
 true,
 2,
 2);

insert into template
(id, nome, descricao, formato, preco, imagemurl, ativo, id_categoria, id_autor)
values
(3,
 'Template Apresentação',
 'Apresentação profissional para empresas',
 'PowerPoint',
 39.90,
 'https://templatify.com/apresentacao.jpg',
 true,
 3,
 3);



insert into templatearquivo
(id, arquivourl, tamanhobytes, checksum)
values
(1,
 'https://templatify.com/files/instagram.zip',
 1024000,
 'hash123');

insert into templatearquivo
(id, arquivourl, tamanhobytes, checksum)
values
(2,
 'https://templatify.com/files/curriculo.docx',
 512000,
 'hash456');

insert into templatearquivo
(id, arquivourl, tamanhobytes, checksum)
values
(3,
 'https://templatify.com/files/apresentacao.pptx',
 2048000,
 'hash789');


ALTER TABLE template ALTER COLUMN id RESTART WITH 10;