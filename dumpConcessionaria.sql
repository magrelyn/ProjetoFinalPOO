CREATE DATABASE CONCESSIONARIA;

USE CONCESSIONARIA;

CREATE TABLE MARCA (
	id_mar INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL UNIQUE,
	cnpj VARCHAR(25) NOT NULL,
	slogan VARCHAR(40) NOT NULL
);
	
CREATE TABLE CARRO (
	id_car INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	modelo VARCHAR(50) NOT NULL,
	ano INT NOT NULL,
	cor VARCHAR(30) NOT NULL,
	preco DOUBLE NOT NULL,
	marca INT NOT NULL,
	
	FOREIGN KEY (marca) REFERENCES MARCA(id_mar)
);

INSERT INTO MARCA (id_mar, nome, cnpj, slogan) VALUES 
(1,"Toyota", "92.932.713/7729-83", "Let's Go Places Moving Forward"),
(2,"Volkswagen","82.337.713/8391-24","Das Auto"),
(3,"Ford","55.568.446/8245-26","Go Further"),
(4,"Honda","54.511.436/2881-89","The Power of Dreams"),
(5,"Nissan","64.135.566/2462-63","Innovation that Excites"),
(6,"Hyundai","71.841.457/9383-52","Drive your way"),
(7,"Chevrolet","91.781.047/8387-72","Find New Roads"),
(8,"Mercedes-Benz","83.734.731/8711-62","The Best or Nothing"),
(9,"BMW","99.921.821/2713-38","Freude Am Fahren");

INSERT INTO CARRO (modelo, ano, cor, preco, marca) VALUES 
("Camry", 2020, "vermelho", 309990.00, 1),
("Comfortline 200 TSI", 2021, "branco", 91805.00, 2),
("Mustang Black Shadow", 2020, "branco", 396900.00, 3),
("Honda Civic EXL", 2017, "cinza", 122800.00, 4),
("NISSAN GT-R", 2017, "laranja", 900000.00 , 5),
("New Azera", 2020, "branco", 269900.00, 6),
("Camaro 2020", 2020, "vermelho", 403000.00, 7),
("Classe A Sedan", 2021, "branco", 190900.00, 8),
("BMW Serie 8 Coupe", 2020, "laranja metalico", 881950.00, 9);