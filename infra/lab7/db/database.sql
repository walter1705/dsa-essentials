-- Editorial DB - Lab 7
-- Full SQL script: database, tables and sample data

CREATE DATABASE IF NOT EXISTS editorial_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE editorial_db;

-- Drop tables in correct FK order
DROP TABLE IF EXISTS publicaciones;
DROP TABLE IF EXISTS libros;
DROP TABLE IF EXISTS autores;

-- --------------------------------------------------------
-- Table: autores
-- --------------------------------------------------------
CREATE TABLE autores (
    id               INT           AUTO_INCREMENT PRIMARY KEY,
    nombre           VARCHAR(100)  NOT NULL,
    apellido         VARCHAR(100)  NOT NULL,
    nacionalidad     VARCHAR(100),
    fecha_nacimiento DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table: libros
-- --------------------------------------------------------
CREATE TABLE libros (
    id               INT           AUTO_INCREMENT PRIMARY KEY,
    titulo           VARCHAR(200)  NOT NULL,
    isbn             VARCHAR(20)   UNIQUE,
    anio_publicacion YEAR,
    genero           VARCHAR(100),
    id_autor         INT,
    CONSTRAINT fk_libro_autor FOREIGN KEY (id_autor)
        REFERENCES autores(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table: publicaciones
-- --------------------------------------------------------
CREATE TABLE publicaciones (
    id                INT            AUTO_INCREMENT PRIMARY KEY,
    id_libro          INT            NOT NULL,
    editorial         VARCHAR(150)   NOT NULL,
    fecha_publicacion DATE,
    precio            DECIMAL(10,2),
    stock             INT            DEFAULT 0,
    CONSTRAINT fk_pub_libro FOREIGN KEY (id_libro)
        REFERENCES libros(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Sample data: 5 autores
-- --------------------------------------------------------
INSERT INTO autores (nombre, apellido, nacionalidad, fecha_nacimiento) VALUES
('Gabriel',   'García Márquez',  'Colombiana',  '1927-03-06'),
('Jorge Luis', 'Borges',         'Argentina',   '1899-08-24'),
('Isabel',    'Allende',         'Chilena',     '1942-08-02'),
('Mario',     'Vargas Llosa',    'Peruana',     '1936-03-28'),
('Octavio',   'Paz',             'Mexicana',    '1914-03-31');

-- --------------------------------------------------------
-- Sample data: 8 libros
-- --------------------------------------------------------
INSERT INTO libros (titulo, isbn, anio_publicacion, genero, id_autor) VALUES
('Cien años de soledad',         '978-0-06-088328-7',  1967, 'Novela',        1),
('El amor en los tiempos del cólera', '978-0-307-38976-3', 1985, 'Novela',   1),
('Ficciones',                    '978-0-8021-3030-8',  1944, 'Cuento',        2),
('El Aleph',                     '978-0-14-028680-7',  1949, 'Cuento',        2),
('La casa de los espíritus',     '978-0-553-38380-1',  1982, 'Novela',        3),
('La ciudad y los perros',       '978-0-374-52285-0',  1963, 'Novela',        4),
('El laberinto de la soledad',   '978-0-394-17427-1',  1950, 'Ensayo',        5),
('Conversación en la catedral',  '978-0-06-093184-3',  1969, 'Novela',        4);

-- --------------------------------------------------------
-- Sample data: 8 publicaciones
-- --------------------------------------------------------
INSERT INTO publicaciones (id_libro, editorial, fecha_publicacion, precio, stock) VALUES
(1, 'Editorial Sudamericana',  '1967-05-30', 22.50, 120),
(1, 'Harper Collins',          '2003-09-01', 18.99,  80),
(2, 'Editorial Oveja Negra',   '1985-11-01', 19.75,  60),
(3, 'Sur',                     '1944-01-01', 15.00,  45),
(4, 'Emecé Editores',          '1949-01-01', 16.50,  55),
(5, 'Plaza & Janés',           '1982-10-01', 21.00,  90),
(6, 'Seix Barral',             '1963-10-01', 20.00,  70),
(8, 'Alfaguara',               '1969-11-01', 23.50,  40);
