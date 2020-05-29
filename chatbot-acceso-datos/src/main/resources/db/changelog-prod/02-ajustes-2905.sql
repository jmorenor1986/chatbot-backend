CREATE SEQUENCE parametros_app_seq START WITH 1 INCREMENT BY 1 ;

INSERT INTO BDPremierPrueba_0306.WhatsAppWS.parametros_app
(id, clave, valor)
VALUES (NEXT VALUE FOR parametros_app_seq, 'MESES_EXTRACTO' , '12')
;