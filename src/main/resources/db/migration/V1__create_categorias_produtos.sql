CREATE TABLE tb_categorias (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE tb_produtos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    categoria_id BIGINT NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES tb_categorias(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
