USE bdmundotravel;
INSERT INTO empleado VALUES('1','admin','admin','admin','1');
INSERT INTO empleado VALUES('lmiranda','Luz','Miranda','agente','1234');
INSERT INTO empleado VALUES('emacias','Erica','Macias','asistente','1234');
INSERT INTO paquetedeviaje VALUES(1,'Orlando','4 noches');
INSERT INTO paquetedeviaje VALUES(0,'Miami','7 noches');
INSERT INTO cliente VALUES('0922577390','Cesar','Carlier','27/04/1998');
INSERT INTO registro VALUES('0922577390',1);
INSERT INTO cliente values('0912585977','Andrea','Portilla','04/12/1996');

INSERT INTO Vuelo values(0, 'AA','GYE','MCO','16/06/2019','24/06/2019','15:20:00');
INSERT INTO reservaVuelo values(0, '0912585977','2','coach',1,1);


select * from paquetedeviaje;