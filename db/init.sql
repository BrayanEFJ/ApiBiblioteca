CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

-- Tabla autores
CREATE TABLE IF NOT EXISTS autores (
  autor_id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  biografia TEXT,
  Documento VARCHAR(20),
  Activo TINYINT(1)
);

INSERT INTO autores (autor_id, nombre, biografia, Documento, Activo) VALUES
  (1, 'gabriel garcia marquez', 'autor colombiano conocido por cien años de soledad.', '1098632598', 1),
  (2, 'j.k. rowling', 'autora británica conocida por la serie de harry potter.', '1097568452', 1),
  (3, 'David alonso', 'Nuevo autor', '312443245', 1),
  (4, 'Angelica Toloza', 'Autora contemporanea caracterizada por una fuerte creatividad y adaptabibilidad de tramas', '3223151555', 1);

-- Tabla admin
CREATE TABLE IF NOT EXISTS admin (
  id INT AUTO_INCREMENT PRIMARY KEY,
  usuario VARCHAR(255),
  contrasena VARCHAR(255)
);

INSERT INTO admin (id, usuario, contrasena) VALUES
  (1, 'username99', '123456');

-- Tabla editoriales
CREATE TABLE IF NOT EXISTS editoriales (
  editorial_id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  direccion VARCHAR(255)
);

INSERT INTO editoriales (editorial_id, nombre, direccion) VALUES
  (1, 'penguin random house', 'new york, usa'),
  (2, 'bloomsbury publishing', 'london, uk');

-- Tabla libros
CREATE TABLE IF NOT EXISTS libros (
  libro_id INT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(200),
  autor_id INT,
  editorial_id INT,
  año_publicacion INT,
  genero VARCHAR(100),
  FOREIGN KEY (autor_id) REFERENCES autores (autor_id),
  FOREIGN KEY (editorial_id) REFERENCES editoriales (editorial_id)
);

INSERT INTO libros (libro_id, titulo, autor_id, editorial_id, año_publicacion, genero) VALUES
  (1, 'Cien años de soledad', 1, 1, 1967, 'Realismo mágico'),
  (2, 'Harry Potter y la piedra filosofal', 2, 2, 1997, 'Fantasía');

