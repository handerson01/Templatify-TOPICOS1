INSERT INTO categoria (id, nome, descricao, ativa) VALUES
(1, 'Social Media', 'Templates para redes sociais', true),
(2, 'Currículo', 'Templates de currículos', true),
(3, 'Apresentação', 'Templates para slides', true);

INSERT INTO criador (id, nome) VALUES
(1, 'Samuell Handerson'),
(2, 'Luara Sipaúba'),
(3, 'Rafael Junior');

INSERT INTO formato (id, nome, extensao) VALUES
(1, 'Canva', 'canva'),
(2, 'Word', 'docx'),
(3, 'PowerPoint', 'pptx');

INSERT INTO precificacao (id, valorbase, moeda, percentualdesconto, iniciopromocao, fimpromocao) VALUES
(1, 29.90, 'BRL', 0, null, null),
(2, 19.90, 'BRL', 0, null, null),
(3, 39.90, 'BRL', 0, null, null);

INSERT INTO template (id, nome, descricao, ativo, id_categoria, id_autor, id_formato, id_precificacao) VALUES
(1, 'Template Instagram', 'Template para posts de Instagram', true, 1, 1, 1, 1),
(2, 'Template Currículo', 'Currículo profissional editável', true, 2, 2, 2, 2),
(3, 'Template Apresentação', 'Apresentação profissional para empresas', true, 3, 3, 3, 3),
(4, 'Template Social na Nuvem', 'Template compartilhado pela nuvem', true, 1, 1, 1, 1);

INSERT INTO templatearquivo (id, arquivourl, tamanhobytes, checksum) VALUES
(1, 'https://templatify.com/files/instagram.zip', 1024000, 'hash123'),
(2, 'https://templatify.com/files/curriculo.docx', 512000, 'hash456'),
(3, 'https://templatify.com/files/apresentacao.pptx', 2048000, 'hash789');

INSERT INTO templatenuvem (id, urlacesso, plataforma, requercontapaga) VALUES
(4, 'https://templatify.com/cloud/social', 'Canva', false);

INSERT INTO imagem (id, url, descricao, id_template) VALUES
(1, 'https://templatify.com/instagram.jpg', 'Prévia do template Instagram', 1),
(2, 'https://templatify.com/curriculo.jpg', 'Prévia do template de currículo', 2),
(3, 'https://templatify.com/apresentacao.jpg', 'Prévia do template de apresentação', 3),
(4, 'https://templatify.com/cloud/social.jpg', 'Prévia do template na nuvem', 4);

SELECT setval(pg_get_serial_sequence('categoria', 'id'), (SELECT MAX(id) FROM categoria), true);
SELECT setval(pg_get_serial_sequence('criador', 'id'), (SELECT MAX(id) FROM criador), true);
SELECT setval(pg_get_serial_sequence('formato', 'id'), (SELECT MAX(id) FROM formato), true);
SELECT setval(pg_get_serial_sequence('precificacao', 'id'), (SELECT MAX(id) FROM precificacao), true);
SELECT setval(pg_get_serial_sequence('template', 'id'), (SELECT MAX(id) FROM template), true);
SELECT setval(pg_get_serial_sequence('imagem', 'id'), (SELECT MAX(id) FROM imagem), true);