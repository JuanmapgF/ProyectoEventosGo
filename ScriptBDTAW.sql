/* 
	Tabla que almacena e identifica el teleoperador y el usuario que participan en una 
	conversación.
	ID_TELEOPERADOR es una foreign key de USUARIO de rol 2
	ID_USUARIO es una foreign key de USUARIO de rol 1 o 4
	ID es un autoincremental
	ASUNTO es una cadena que indica el asunto por el que se abre la conversacion
*/
create table CONVERSACION
(
	ID INTEGER not null generated always as identity (start with 1, increment by 1),
	ASUNTO VARCHAR(100) not null,
 	ID_TELEOPERADOR INTEGER not null,
	ID_USUARIO INTEGER not null,
	primary key (ID)
);

/* 
	Guarda información sobre las entradas compradas:
	ID es un autoincremental
	ID_EVENTO es una foreign key de EVENTO 
	ID_USUARIO es una foreign key de USUARIO_EVENTO (USUARIO de rol 4)
*/
create table ENTRADA
(
	ID INTEGER not null generated always as identity (start with 1, increment by 1),
	ID_EVENTO INTEGER not null,
	ID_USUARIO INTEGER not null,
	primary key (ID)
);

/* 
	Es extensión de la tabla ENTRADA cuando el evento tiene un aforo limitado con sitios
	asignados
	ID es una foreign key de ENTRADA
	FILA y ASIENTO deben estar entre los valores posibles del aforo del evento
*/
create table ENTRADA_AFORO
(
	ID INTEGER not null,
	FILA INTEGER not null,
	ASIENTO INTEGER not null,
	primary key (ID)
);

/* 
	Guarda la informacion de un estudio estadístico
	ID es un autoincremental
	ID_ANALISTA es una foreign key de USUARIO de rol 3
	RESULTADO guarda el resultado numérico del estudio estadístico
*/
create table ESTUDIO
(
	ID INTEGER not null generated always as identity (start with 1, increment by 1),
	ID_ANALISTA INTEGER not null,
	TITULO VARCHAR(100) not null,
	RESULTADO LONG VARCHAR not null,
	primary key (ID)
);

/* 
	Guarda información de una etiqueta
	ID es autoincremental
*/
create table ETIQUETA
(
	ID INTEGER not null generated always as identity (start with 1, increment by 1),
	NOMBRE VARCHAR(50) not null,
	primary key (ID)
);

/* 
	Guarda información de un evento
	ID es un autoincremental
	FECHA_FIN_RESERVAS se podrá comprar entradas hasta esta fecha incluida
	MAXIMO_ENTRADAS_USUARIO se tendrá que tener en cuenta que se pueden únicamente compar
		como máximo este número de entradas por usuario
	AFORO es un entero que según su valor nos dirá si tiene aforo: NO (0) y SI (1)
	CREADOR es una foreign key de USUARIO de rol tipo 1
*/
create table EVENTO
(
	ID INTEGER not null generated always as identity (start with 1, increment by 1),
	TITULO VARCHAR(100) not null,
	DESCRIPCION LONG VARCHAR not null,
	FECHA_EVENTO DATE not null,
	FECHA_FIN_RESERVAS DATE not null,
	COSTE DOUBLE not null,
	MAXIMO_ENTRADAS_USUARIO INTEGER not null,
	AFORO INTEGER not null,
	ID_CREADOR INTEGER not null,
	primary key (ID)
);

/* 
	Es una extensión de EVENTO que guarda la información de un evento si este tiene 
	aforo
	ID es una foreign key de EVENTO
*/
create table EVENTO_AFORO
(
	ID INTEGER not null,
	FILAS INTEGER not null,
	ASIENTOS INTEGER not null,
	primary key (ID)
);

/* 
	Es una relación débil que permite asignar o agrupar eventos a través de las etiquetas
	ID es autoincremental
	ID_EVENTO es una foreign key de EVENTO
	ID_ETIQUETA es una foreign key de ETIQUETA
*/
create table EVENTO_ETIQUETA
(
	ID INTEGER not null generated always as identity (start with 1, increment by 1),
	ID_EVENTO INTEGER not null,
	ID_ETIQUETA INTEGER not null,
	primary key (ID)
);

/* 
	Guarda un mensaje relacionado a una conversacion y el usuario que lo envió
	ID es un autoincremental
	ID_CONVERSACION es una foreign key de CONVERSACION
	ID_USUARIO es una foreign key de USUARIO con rol 1, 2 o 4
	VISTO : 0 cuando el mensaje no ha sido leído y 1 si el mensaje ya ha sido leído
*/
create table MENSAJE
(
	ID INTEGER not null generated always as identity (start with 1, increment by 1),
	ID_CONVERSACION INTEGER not null,
	ID_USUARIO INTEGER not null,
	FECHA DATE not null,
	HORA TIME not null,
	TEXTO LONG VARCHAR not null,
	VISTO INTEGER not null,
	primary key (ID)
);

/* 
	Tabla que almacena todo los usuarios y a través de la propiedad rol
	identificamos el tipo de usuario. Los tipos que entran dentro de usuario
	son: ADMINISTRADOR (0), CREADOR (1),  TELEOPERADOR (2), 
	ANALISTA (3), USUARIO_EVENTO (4).
	El id es un autoincremental
*/
create table USUARIO
(
	ID INTEGER not null generated always as identity (start with 1, increment by 1),
	CORREO VARCHAR(50) not null unique,
	CONTRASENA VARCHAR(30) not null,
	NOMBRE VARCHAR(50) not null,
	ROL INTEGER not null,
	primary key (ID)
);

/* 
	Tabla que guarda los datos adicionales de los usuarios de rol 4
	Tiene una clave foránea con el id de la tabla Usuario
	Sexo: MASCULINO (0), FEMENINO (1), OTRO (2)
*/
create table USUARIO_EVENTO
(
	ID INTEGER not null,
	APELLIDOS VARCHAR(50) not null,
	DOMICILIO VARCHAR(100) not null,
	CIUDAD VARCHAR(50) not null,
	FECHA_NACIMIENTO DATE not null,
	SEXO INTEGER not null,
	primary key (ID)
);

/* Claves foráneas */

alter table USUARIO_EVENTO
	add foreign key (ID) references USUARIO;

alter table CONVERSACION
	add foreign key (ID_TELEOPERADOR) references USUARIO;

alter table CONVERSACION
	add foreign key (ID_USUARIO) references USUARIO;

alter table ENTRADA
	add foreign key (ID_EVENTO) references EVENTO;

alter table ENTRADA
	add foreign key (ID_USUARIO) references USUARIO_EVENTO;

alter table ENTRADA_AFORO
	add foreign key (ID) references ENTRADA;

alter table ESTUDIO
	add foreign key (ID_ANALISTA) references USUARIO;

alter table EVENTO_AFORO
	add foreign key (ID) references EVENTO;

alter table EVENTO_ETIQUETA
	add foreign key (ID_EVENTO) references EVENTO;

alter table EVENTO_ETIQUETA
	add foreign key (ID_ETIQUETA) references ETIQUETA;
	
alter table MENSAJE
	add foreign key (ID_CONVERSACION) references CONVERSACION;

alter table MENSAJE
	add foreign key (ID_USUARIO) references USUARIO;

alter table EVENTO
	add foreign key (ID_CREADOR) references USUARIO;