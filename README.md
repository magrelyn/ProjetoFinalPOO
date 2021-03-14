# Projeto Final POO

O sistema foi pensado para uma concessionária, onde temos carros a venda, os quais queremos guardar informações sobre o modelo, ano, cor, preço e marca.
Cada carro possui uma marca, a qual queremos armazenar o nome, CNPJ e slogan.  

Modelo Relacional:

MARCA (id_mar, nome, cnpj, slogan)
	id_mar é PK
	
CARRO (id_car, modelo, ano, cor, preco, marca)
	id_car é PK
	marca REFERENCIA MARCA
