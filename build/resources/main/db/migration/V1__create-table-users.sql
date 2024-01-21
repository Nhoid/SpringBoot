CREATE TABLE mydb.`users` ( -- CONFIGURAÇÕES DA TABELA
                             id INT UNSIGNED auto_increment NOT NULL, -- CRIA A COLUNA DE ID`S
                             name varchar(100) NOT NULL, -- CRIA A COLUNA DOS NOMES
                             email varchar(255) NOT NULL, -- CRIA A COLUNA DOS EMAIL`S
                             username varchar(100) NOT NULL, -- CRIA A COLUNA DOS USERNAME`S
                             password varchar(100) NOT NULL, -- CRIA A COLUNA DAS SENHAS
                             borndate DATE NOT NULL, -- CRIA A COLUNA DAS DATAS DE NASCIMENTO
                             Active BOOL NOT NULL, -- CRIA A COLUNA QUE INFORMA SE O USUARIO É ATIVO OU NÃO
                             created_at DATETIME DEFAULT now() NOT NULL, -- GUARDA A DATA E HORARIO EM QUE O REGISTRO FOI CRIADO
                             CONSTRAINT PK_User PRIMARY KEY (id), -- DEFINE QUE A COLUNA ID É A CHAVE PRIMARIA
                             CONSTRAINT unique_email UNIQUE KEY (email), -- DEFINE QUE CADA SÓ PODE SER REGISTRADO UMA VEZ NA TABELA
                             CONSTRAINT unique_username UNIQUE KEY (username) -- DEFINE QUE O USUARIO SÓ PODE SER REGISTRADO UMA VEZ NA TABELA
);
