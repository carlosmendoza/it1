
-- ofertas
CREATE TABLE OFERTA(

ID NUMBER NOT NULL,
IDOPERADOR NUMBER NOT NULL, 
CAPACIDADREAL NUMBER NOT NULL , 
COSTO NUMBER(*,2) NOT NULL, 
DISPONIBILIDAD INTEGER NOT NULL CHECK(DISPONIBILIDAD<2 AND DISPONIBILIDAD>=0),
CONSTRAINT PK_OFERTA_ID PRIMARY KEY(ID),
CONSTRAINT FK_OPERADOR_ID FOREIGN KEY(IDOPERADOR) REFERENCES OPERADOR(ID),
CHECK(COSTO>=0 AND CAPACIDADREAL>0 ));


-- operadores.
CREATE TABLE OPERADOR (
	ID NUMBER NOT NULL,
    NOMBRE VARCHAR(20) NOT NULL,
	TIPO VARCHAR(20) NOT NULL,
	DOCUMENTO NUMBER NOT NULL,
	NIT NUMBER NOT NULL,
	HORAINICIAL VARCHAR(10) NOT NULL,
	HORAFINAL VARCHAR(10)NOT NULL,
	CONSTRAINT PK_OPERADOR_ID PRIMARY KEY (ID));


-- clientes.
CREATE TABLE CLIENTE(
DOCUMENTO NUMBER NOT NULL,
NOMBRE VARCHAR(20) NOT NULL, 
ROL VARCHAR(30) NOT NULL,
CONSTRAINT PK_CLIENTE_DOCUMENTO PRIMARY KEY (DOCUMENTO) );



-- inmuebles.
CREATE TABLE INMUEBLE(
ID NUMBER NOT NULL, 
IDOFERTA NUMBER NOT NULL,
TIPO VARCHAR(20) NOT NULL,
CATEGORIA VARCHAR(20) NOT NULL, 
TAMANIO NUMBER NOT NULL,
UBICACION VARCHAR(20) NOT NULL,
CONSTRAINT PK_INMUEBLE_ID PRIMARY KEY (ID),
CONSTRAINT FK_OFERTA_ID FOREIGN KEY(IDOFERTA) REFERENCES OFERTA(ID)
);



-- servicios.
CREATE TABLE SERVICIO( 
ID NUMBER NOT NULL,
NOMBRE VARCHAR(30) NOT NULL,
DESCRIPCION VARCHAR(100)NOT NULL,
CONSTRAINT PK_SERVICIO_ID PRIMARY KEY(ID)
);

--facturas.
CREATE TABLE FACTURA(
ID NUMBER NOT NULL,
VALOR NUMBER(*,2) NOT NULL CHECK (VALOR>0),
FECHA DATE NOT NULL,
CONSTRAINT PK_FACTURA_ID PRIMARY KEY(ID)
);

-- reservas.
CREATE TABLE RESERVA( 
ID NUMBER NOT NULL,
DOCUMENTOCLIENTE NUMBER NOT NULL,
IDOFERTA NUMBER NOT NULL,
IDFACTURA NUMBER NOT NULL,
ESTADO NUMBER NOT NULL,
FFECHAINICIAL DATE NOT NULL, 
FECHAFINAL DATE NOT NULL,
CONSTRAINT PK_RESERVA_ID PRIMARY KEY (ID),
CONSTRAINT FK_FACTURA_ID FOREIGN KEY(IDFACTURA) REFERENCES FACTURA(ID),
CONSTRAINT FK_OFERTA_ID FOREIGN KEY }(IDOFERTA) REFERENCES OFERTA(ID);



-- servicios de una oferta
CREATE TABLE PRESTAN(
IDOFERTA NUMBER NOT NULL,
IDSERVICIO NUMBER NOT NULL,
CONSTRAINT PK_PRESTAN_IDS PRIMARY KEY(IDOFERTA, IDSERVICIO),
CONSTRAINT FK_OFERTA_ID FOREIGN KEY (IDOFERTA) REFERENCES OFERTA(ID),
CONSTRAINT FK_SERVICIO_ID FOREIGN KEY(IDSERVICIO) REFERENCES  SERVICIO(ID)
);

--datos de los operadores
INSERT INTO OPERADOR VALUES(10, 'federico marroquin', 'persona natural', 1077974783, 0, '8:00','18:00' );
INSERT INTO OPERADOR VALUES(11, 'mario perez', 'persona natural', 1077923453243, 0, '10:00','17:00' );
INSERT INTO OPERADOR VALUES(12, 'carlos mendoza', 'persona natural', 3453474783, 0, '9:00','20:00' );
INSERT INTO OPERADOR VALUES(13, 'pablo navaz', 'persona natural', 23454783, 0, '8:30','15:00' );
INSERT INTO OPERADOR VALUES(14, 'leo messi', 'persona evento', 56756974783, 0, '10:00','17:00' );
INSERT INTO OPERADOR VALUES(15, 'cristiano ronaldo', 'persona evento', 56779747832, 0,'2:00','18:00' );
INSERT INTO OPERADOR VALUES(16, 'naruto uzumaki', 'persona evento', 104534483, 0, '9:00','20:00' );
INSERT INTO OPERADOR VALUES(17, 'tio rico MC pato', 'persona evento', 18767986783, 0, '7:00','13:00' );
INSERT INTO OPERADOR VALUES(18, 'hotel los robles', 'hotel', 0, 8008984561 , '8:00','8:00');
INSERT INTO OPERADOR VALUES(19, 'hotel paris', 'hotel', 0, 8984561800,  '8:00','8:00' );
INSERT INTO OPERADOR VALUES(20, 'sheraton', 'hotel', 0, 8984561234,  '8:00','8:00' );
INSERT INTO OPERADOR VALUES(21, 'hotel mediterraneo', 'hotel', 0, 8984561234,  '8:00','8:00' );
INSERT INTO OPERADOR VALUES(22, 'hostal Bakano', 'hostal', 0,4563561234, '8:00','8:00' );
INSERT INTO OPERADOR VALUES(23, 'la candelaria hostel', 'hostal', 0, 8984563334, '8:00','8:00' );
INSERT INTO OPERADOR VALUES(24, 'chorro de quevedo', 'hostal', 0, 8777563334, '8:00','8:00' );
INSERT INTO OPERADOR VALUES(25, 'yepeto hostal', 'hostal', 0, 8984563844, '8:00','8:00' );
INSERT INTO OPERADOR VALUES(26, 'city U', 'viviendaU', 0, 1984563334, '8:00','8:00' );
INSERT INTO OPERADOR VALUES(27, 'esstudia', 'viviendaU', 0, 1234563334, '8:00','8:00' );
INSERT INTO OPERADOR VALUES(28, 'livinnbogita', 'viviendaU', 0, 0984563330, '8:00','8:00' );
INSERT INTO OPERADOR VALUES(29, 'dormitorios loft', 'viviendaU', 0, 554456390, '8:00','8:00' );

--datos de los clientes
INSERT INTO CLIENTE VALUES(1077975782, 'fernando romero', 'estudiante'); 
INSERT INTO CLIENTE VALUES(1077975780, 'Luis Colmenares', 'estudiante'); 
INSERT INTO CLIENTE VALUES(1077975784, 'kelvin estupi�an', 'estudiante'); 
INSERT INTO CLIENTE VALUES(1077975785, 'jessy quintero', 'estudiante'); 
INSERT INTO CLIENTE VALUES(1077975786, 'nicolas cardozo', 'profesor'); 
INSERT INTO CLIENTE VALUES(1077975787, 'german bravo', 'profesor'); 
INSERT INTO CLIENTE VALUES(1077975788, 'rodrigo cardozo', 'profesor'); 
INSERT INTO CLIENTE VALUES(1077975789, 'maria camila romero', 'profesor'); 
INSERT INTO CLIENTE VALUES(1077975710, 'sergio fajardo', 'egresado'); 
INSERT INTO CLIENTE VALUES(1077975711, 'c�sar gabviria', 'egresado'); 
INSERT INTO CLIENTE VALUES(1077975712, 'jorge robledo', 'egresado'); 
INSERT INTO CLIENTE VALUES(1077975713, 'oscar ortiz', 'empleado'); 
INSERT INTO CLIENTE VALUES(1077975714, 'pedro jaramillo', 'empleado'); 
INSERT INTO CLIENTE VALUES(1077975715, 'pablo escobar', 'empleado'); 
INSERT INTO CLIENTE VALUES(1077975716, 'alvaro uribe', 'empleado'); 
INSERT INTO CLIENTE VALUES(1077975717, 'luisito comunica', 'padres'); 
INSERT INTO CLIENTE VALUES(1077975718, 'ruben gunderson', 'padres'); 
INSERT INTO CLIENTE VALUES(1077975719, 'dave mustaine', 'padres'); 
INSERT INTO CLIENTE VALUES(1077975720, 'juanes aristizabal', 'registrado evento'); 
INSERT INTO CLIENTE VALUES(1077975721, 'stephen hawking', 'profesor invitado'); 
INSERT INTO CLIENTE VALUES(1077975722, 'neil degrasse tyson', 'profesor invitado'); 
INSERT INTO CLIENTE VALUES(1077975723, 'barack obama', 'registrado evento'); 

--datos de las facturas
--INSERT INTO FACTURA VALUES(100, 100000, '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(101, 200000, '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(102, 300000, '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(103, 150000, '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(104, 80000,  '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(105, 100000, '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(106, 100000, '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(107, 400000, '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(108, 1000000,'2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(109, 2500000,'2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(110, 100000, '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(111, 100000, '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(112, 400000, '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(113, 40000,  '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(114, 24500,  '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(115, 200000, '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(116, 300000, '2018-10-28 08:12:59' );
--INSERT INTO FACTURA VALUES(117, 1000000,'2018-10-28 08:12:59' );

--datos de los inmuebles
INSERT INTO INMUEBLE VALUES(500,1000, 'apartamento', 'estandar',75 ,'Cl. 19 #572');
INSERT INTO INMUEBLE VALUES(501,1001, 'apartamento', 'estandar',100 ,'Cra. 3 #1832');
INSERT INTO INMUEBLE VALUES(502,1002, 'apartamento', 'estandar',45 ,'Cl. 17 #572');
INSERT INTO INMUEBLE VALUES(503,1003, 'apartamento', 'semisuite',125 ,'Cl. 19 #572');
INSERT INTO INMUEBLE VALUES(504,1004, 'apartamento', 'semisuite',80 ,'Cra. 4 #1832');
INSERT INTO INMUEBLE VALUES(505,1005, 'apartamento', 'semisuite',100 ,'Cl. 19 #572');
INSERT INTO INMUEBLE VALUES(506,1006, 'apartamento', 'suite',60 ,'Cl. 16 #572');
INSERT INTO INMUEBLE VALUES(507,1007, 'apartamento', 'suite',114 ,'Cra. 5 #1832');
INSERT INTO INMUEBLE VALUES(508,1008, 'apartamento', 'suite',75 ,'Cl. 12 #572');
INSERT INTO INMUEBLE VALUES(509,1009, 'apartamento', 'suite',90 ,'Cl. 22 #572');
INSERT INTO INMUEBLE VALUES(510,1010, 'habitacion', 'estandar',25 ,'Cra. 6 #1832');
INSERT INTO INMUEBLE VALUES(511,1011, 'habitacion', 'estandar',75 ,'Cl. 23 #572');
INSERT INTO INMUEBLE VALUES(512,1012, 'habitacion', 'estandar',30 ,'Cra. 2 #1832');
INSERT INTO INMUEBLE VALUES(513,1013, 'habitacion', 'estandar',35 ,'Cl. 30 #572');
INSERT INTO INMUEBLE VALUES(514,1014, 'habitacion', 'semisuite',45 ,'Cl. 19 #572');
INSERT INTO INMUEBLE VALUES(515,1015, 'habitacion', 'semisuite',50 ,'Cl. 22 #572');
INSERT INTO INMUEBLE VALUES(516,1016, 'habitacion', 'semisuite',60 ,'Cra. 1 #1832');
INSERT INTO INMUEBLE VALUES(517,1017, 'habitacion', 'suite',75 ,'Cl. 17 #572');
INSERT INTO INMUEBLE VALUES(518,1018, 'habitacion', 'suite',56 ,'Cra. 3 #1832');
INSERT INTO INMUEBLE VALUES(519,1019, 'habitacion', 'suite',25 ,'Cl. 19 #572');

--datos de los servicios
INSERT INTO SERVICIO VALUES(2000, 'alojamiento por noche','servicio de alojamiento por noche' );
INSERT INTO SERVICIO VALUES(2001, 'ba�era','el inmueble cuenta con ba�era' );
INSERT INTO SERVICIO VALUES(2002, 'jacuzzi','el inmueble cuenta con jacuzzi' );
INSERT INTO SERVICIO VALUES(2003, 'sala','el inmueble cuenta con sala' );
INSERT INTO SERVICIO VALUES(2004, 'cocineta','el inmueble cuenta con cocineta' );
INSERT INTO SERVICIO VALUES(2005, 'restaurante','incluye el servicio de restaurante' );
INSERT INTO SERVICIO VALUES(2006, 'piscina','incluye el servicio de piscina' );
INSERT INTO SERVICIO VALUES(2007, 'parqueadero','incluye el servicio de parqueadero' );
INSERT INTO SERVICIO VALUES(2008, 'WiFi','incluye el servicio de WiFi' );
INSERT INTO SERVICIO VALUES(2009, 'TV cable','incluye el servicio de TV cable' );
INSERT INTO SERVICIO VALUES(2010, 'vivienda compartidad','servicio de alojamiento por noche en habitacion compartida ' );

--datos de las reservas
--INSERT INTO RESERVA VALUES(5000,1077975722,100 ,1, '2018-05-01 08:00:00','2018-05-03 08:00:00' );
--INSERT INTO RESERVA VALUES(5001,1077975720,101 ,0, '2019-04-01 08:00:00','2019-04-05 08:00:00' );
--INSERT INTO RESERVA VALUES(5002,1077975784,102 ,1, '2018-05-01 08:00:00','2018-05-02 08:00:00' );
--INSERT INTO RESERVA VALUES(5003,1077975785,103 ,1, '2018-05-13 08:00:00','2018-05-15 08:00:00' );
--INSERT INTO RESERVA VALUES(5004,1077975786,104 ,0, '2018-06-20 08:00:00','2018-06-21 08:00:00' );
--INSERT INTO RESERVA VALUES(5005,1077975787,105 ,1, '2018-07-01 08:00:00','2018-07-06 08:00:00' );
--INSERT INTO RESERVA VALUES(5006,1077975788,106 ,1, '2019-08-09 08:00:00','2019-08-11 08:00:00' );
--INSERT INTO RESERVA VALUES(5007,1077975789,107 ,1, '2018-09-01 08:00:00','2018-10-01 08:00:00' );
--INSERT INTO RESERVA VALUES(5008,1077975710,108 ,0, '2018-05-01 08:00:00','2018-10-01 08:00:00' );
--INSERT INTO RESERVA VALUES(5009,1077975711,109 ,1, '2018-06-01 08:00:00','2018-10-01 08:00:00' );
--INSERT INTO RESERVA VALUES(5010,1077975712,110 ,1, '2018-07-01 08:00:00','2018-10-01 08:00:00' );
--INSERT INTO RESERVA VALUES(5011,1077975713,111 ,0, '2018-08-01 08:00:00','2018-10-01 08:00:00' );
--INSERT INTO RESERVA VALUES(5012,1077975714,112 ,1, '2018-09-01 08:00:00','2018-10-01 08:00:00' );
--INSERT INTO RESERVA VALUES(5013,1077975715,113 ,1, '2018-05-01 08:00:00','2018-10-01 08:00:00' );
--INSERT INTO RESERVA VALUES(5014,1077975716,114 ,0, '2018-06-01 08:00:00','2018-10-01 08:00:00' );
--INSERT INTO RESERVA VALUES(5015,1077975717,115 ,0, '2018-07-01 08:00:00','2018-10-01 08:00:00' );
--INSERT INTO RESERVA VALUES(5016,1077975718,116 ,1, '2018-08-01 08:00:00','2018-10-01 08:00:00' );
--INSERT INTO RESERVA VALUES(5017,1077975719,117 ,1, '2018-09-01 08:00:00','2018-10-01 08:00:00' );

--datos de las ofertas
INSERT INTO OFERTA VALUES(1000,10,1,700000,1); 
INSERT INTO OFERTA VALUES(1001,11,2,800000,1); 
INSERT INTO OFERTA VALUES(1002,12,3,500000,1); 
INSERT INTO OFERTA VALUES(1003,13,1,450000,0); 
INSERT INTO OFERTA VALUES(1004,14,2,700000,1); 
INSERT INTO OFERTA VALUES(1005,15,3,900000,1); 
INSERT INTO OFERTA VALUES(1006,16,1,1000000,1); 
INSERT INTO OFERTA VALUES(1007,17,2,650000,1); 
INSERT INTO OFERTA VALUES(1008,18,3,70000,0); 
INSERT INTO OFERTA VALUES(1009,19,4,45000,1); 
INSERT INTO OFERTA VALUES(1010,20,5,70000,1); 
INSERT INTO OFERTA VALUES(1011,21,1,80000,0); 
INSERT INTO OFERTA VALUES(1012,22,2,90000,1); 
INSERT INTO OFERTA VALUES(1013,23,3,30000,1); 
INSERT INTO OFERTA VALUES(1014,24,4,40000,1); 
INSERT INTO OFERTA VALUES(1015,25,1,50000,1); 
INSERT INTO OFERTA VALUES(1016,26,2,70000,1); 
INSERT INTO OFERTA VALUES(1017,27,1,60000,1); 
INSERT INTO OFERTA VALUES(1018,28,2,100000,1); 
INSERT INTO OFERTA VALUES(1019,29,3,120000,1); 
INSERT INTO OFERTA VALUES(1020,25,4,70000,1); 
INSERT INTO OFERTA VALUES(1021,23,1,80000,1); 
INSERT INTO OFERTA VALUES(1022,26,2,25000,0); 
INSERT INTO OFERTA VALUES(1023,28,3,35000,1);
INSERT INTO OFERTA VALUES(1024,26,1,50000,1); 
INSERT INTO OFERTA VALUES(1025,27,1,70000,1); 
INSERT INTO OFERTA VALUES(1026,25,3,90000,1); 






