--
--Tabla en la que se almacenaran los canales
--
CREATE TABLE pse_param (
  id                    int8            not null  ,
  id_banco              int8            not null  ,
  tipo_credito          int8            not null  ,
  url                   varchar(255)    not null  ,
primary key (id)
);

create sequence pse_param_seq start 1 increment 1;

ALTER TABLE pse_param ADD CONSTRAINT pse_param_tipo_credito CHECK (
	tipo_credito in (1,2,3)
);


insert into pse_param(id,id_banco,tipo_credito,url)
values(nextval('pse_param_seq'), 9000, 1, 'https://www.pagosvirtualesavvillas.com.co/personal/pagos/12328'),
(nextval('pse_param_seq'), 9000, 2, 'https://www.pagosvirtualesavvillas.com.co/personal/pagos/12328' )     ,
(nextval('pse_param_seq'), 9000, 3, 'https://www.pagosvirtualesavvillas.com.co/personal/pagos/12328')      ,
(nextval('pse_param_seq'), 52, 1, 'https://www.pagosvirtualesavvillas.com.co/personal/pagos/')       ,
(nextval('pse_param_seq'), 52, 2, 'https://www.pagosvirtualesavvillas.com.co/personal/pagos/' )      ,
(nextval('pse_param_seq'), 52, 3, 'https://www.pagosvirtualesavvillas.com.co/personal/pagos/')       ,

(nextval('pse_param_seq'), 296, 1, 'https://financierajuriscoop.com.co/oficina-virtual/')      ,
(nextval('pse_param_seq'), 296, 2, 'https://financierajuriscoop.com.co/oficina-virtual/' )     ,
(nextval('pse_param_seq'), 296, 3, 'https://financierajuriscoop.com.co/oficina-virtual/')      ,

(nextval('pse_param_seq'), 65, 1, 'http://ur1')       ,
(nextval('pse_param_seq'), 65, 2, 'http://ur1' )      ,
(nextval('pse_param_seq'), 65, 3, 'http://ur1')       ,

(nextval('pse_param_seq'), 297, 1, 'http://ur1')      ,
(nextval('pse_param_seq'), 297, 2, 'http://ur1' )     ,
(nextval('pse_param_seq'), 297, 3, 'http://ur1');