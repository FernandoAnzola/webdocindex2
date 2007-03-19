CREATE TABLE usuarios (
  id_usuario INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  email VARCHAR(100) NOT NULL,
  password BLOB NOT NULL,
  UNIQUE KEY EMAIL_UNIQUE (email),
  PRIMARY KEY(id_usuario)
)ENGINE = InnoDB;


CREATE TABLE documentos (
  id_documento INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  id_usuario INTEGER UNSIGNED NOT NULL,
  url VARCHAR(100) NOT NULL,
  descripcion VARCHAR(500),
  doc BLOB NOT NULL,
  PRIMARY KEY(id_documento)
)ENGINE = InnoDB;

CREATE TABLE  sesiones (
  id_sesion int(30) unsigned NOT NULL auto_increment,
  id_usuario int(11) unsigned NOT NULL default '0',
  codigo varchar(100) character set latin1 NOT NULL default '',
  ip varchar(15) character set latin1 NOT NULL default '',
  tiempo bigint(20) unsigned NOT NULL default '0',
  PRIMARY KEY  (id_sesion)
) ENGINE=InnoDB;

ALTER TABLE documentos ADD CONSTRAINT FK_DOCUMENTOS_USUARIOS FOREIGN KEY FK_DOCUMENTOS_USUARIOS (id_usuario) REFERENCES usuarios (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE sesiones ADD CONSTRAINT FK_SESIONES_USUARIOS  FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE;