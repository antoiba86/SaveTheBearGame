CREATE DATABASE SAVEBEAR  CHARACTER SET utf8
COLLATE utf8_spanish_ci;


use SAVEBEAR;

CREATE TABLE enemigos (
	id INT NOT NULL AUTO_INCREMENT,
	tiempo INT NOT NULL,
	tipo INT NOT NULL,
	width DOUBLE NOT NULL,
	height DOUBLE NOT NULL,
	vX DOUBLE NOT NULL,
	vY DOUBLE NOT NULL,
	PRIMARY KEY(id)
)

INSERT INTO enemigos (tiempo,tipo,width,height,vX,vY) VALUES (10, 1, 800, 400, -1,0), (10, 1, 800, 600, -1,0),(10, 3, 900,200,-1,0),(20, 2, 800,500,-1,0),(20, 1,850,600,-1,0)