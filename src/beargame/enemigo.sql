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

INSERT INTO enemigos (tiempo,tipo,width,height,vX,vY) VALUES (10, 1, 800, 400, -1,0), (10, 1, 800, 600, -1,0), (10, 3, 900,200,-1,0), (20, 2, 800,500,-1,0),(20, 1,850,600,-1,0), (20,2,900,350,-2,0),(30,3,850,100,-2,0), (30,3,900,200,-1,0), (30,1,900,350,-1,0), (30,2,950,500,-1,0), (30,2,900,600,-1,1), (40, 1, 800, 400, -1,0), (40, 1, 800, 600, -1,0), (40,2,900,400,-1,0),(40,2,1000,600,-2,0),(40,3,950,200,-1,0),(50,1,950,350,-2,0),(50,3,950,200,-2,0),(50,2,950,500,-1,0),(50,3,1000,150,-1,0),(60,1,1000,350,-3,0),(60,1,1000,500,-1,0),(60,1,900,600,-1,0),(60,3,900,200,-1,0),(70,2,950,350,-2,0),(70,2,1050,500,-2,0),(70,2,950,600,-2,0),(70,2,1050,450,-2,0),(80,3,950,200,-1,0),(80,2,950,600,-1,0),(80,1,1000,500,-1,0),(80,1,1000,350,-1,0),(90,2,950,650,-2,0),(90,2,1000,500,-1,0),(90,1,1000,650,-1,0),(90,3,950,175,-1,0),(100,3,1000,250,-2,0),(100,3,950,150,-1,0),(100,1,1000,550,-2,0),(100,1,950,600,-2,0),(110,3,1000,200,-1,0),(100,3,900,150,-1,0),(100,3,1500,250,-3,0),(100,2,1000,550,-2,0),(100,1,900,450,-2,0),(100,2,900,550,-2,0)