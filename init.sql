CREATE DATABASE IF NOT EXISTS testdb CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE testdb;

-- Tabla persona (base para estudiantes y profesores)
CREATE TABLE IF NOT EXISTS persona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    correo VARCHAR(100) NOT NULL UNIQUE,
    tipo VARCHAR(20) NOT NULL
) ENGINE=InnoDB;

-- Tabla Facultad
CREATE TABLE IF NOT EXISTS facultad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    decano_id INT,
    FOREIGN KEY (decano_id) REFERENCES persona(id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Tabla programa
CREATE TABLE IF NOT EXISTS programa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    duracion DOUBLE,
    registro DATE,
    facultad_id INT,
    FOREIGN KEY (facultad_id) REFERENCES facultad(id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Tabla profesor
CREATE TABLE IF NOT EXISTS profesor (
    id INT PRIMARY KEY,
    facultad_id INT,
    tipo_contrato VARCHAR(50),
    FOREIGN KEY (id) REFERENCES persona(id) ON DELETE CASCADE,
    FOREIGN KEY (facultad_id) REFERENCES facultad(id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Tabla estudiante
CREATE TABLE IF NOT EXISTS estudiante (
    id INT PRIMARY KEY,
    codigo VARCHAR(20),
    programa_id INT,
    activo BOOLEAN,
    promedio DOUBLE,
    FOREIGN KEY (id) REFERENCES persona(id) ON DELETE CASCADE,
    FOREIGN KEY (programa_id) REFERENCES programa(id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Tabla curso
CREATE TABLE IF NOT EXISTS curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    programa_id INT,
    activo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (programa_id) REFERENCES programa(id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Tabla inscripcion
CREATE TABLE IF NOT EXISTS inscripcion (
    curso_id INT,
    anio INT,
    semestre INT,
    estudiante_id INT,
    FOREIGN KEY (curso_id) REFERENCES curso(id) ON DELETE SET NULL,
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Tabla cursoprofesor
CREATE TABLE IF NOT EXISTS cursoprofesor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curso_id INT,
    profesor_id INT,
    anio INT,
    semestre INT,
    FOREIGN KEY (curso_id) REFERENCES curso(id) ON DELETE SET NULL,
    FOREIGN KEY (profesor_id) REFERENCES profesor(id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Datos de ejemplo
INSERT INTO persona (id, nombre, apellido, correo, tipo) VALUES 
(1, 'Juan', 'Pérez', 'juan@email.com', 'Estudiante'),
(2, 'María', 'García', 'maria@email.com', 'Profesor'),
(3, 'Ana', 'López', 'ana@email.com', 'Estudiante'),
(4, 'Carlos', 'Rodríguez', 'carlos@email.com', 'Profesor'),
(5, 'Dr. Juan', 'Pérez', 'decano1@email.com', 'Decano'),
(6, 'Dra. María', 'García', 'decano2@email.com', 'Decano');

INSERT INTO facultad (id, nombre, decano_id) VALUES 
(1, 'Facultad de Ingeniería', 5),
(2, 'Facultad de Ciencias', 6);

INSERT INTO programa (id, nombre, duracion, registro, facultad_id) VALUES 
(1, 'Ingeniería de Sistemas', 5.0, '2020-01-15', 1),
(2, 'Matemáticas', 4.0, '2020-01-15', 2);

INSERT INTO profesor (id, facultad_id, tipo_contrato) VALUES 
(2, 1, 'Tiempo Completo'),
(4, 2, 'Medio Tiempo');

INSERT INTO estudiante (id, codigo, programa_id, activo, promedio) VALUES 
(1, '12345', 1, TRUE, 4.2),
(3, '12346', 2, TRUE, 3.8);

INSERT INTO curso (id, nombre, programa_id, activo) VALUES 
(1, 'Programación I', 1, TRUE),
(2, 'Cálculo I', 2, TRUE),
(3, 'Base de Datos', 1, TRUE);

INSERT INTO inscripcion (curso_id, anio, semestre, estudiante_id) VALUES 
(1, 2024, 1, 1),
(2, 2024, 1, 3);

INSERT INTO cursoprofesor (id, curso_id, profesor_id, anio, semestre) VALUES 
(1, 1, 2, 2024, 1),
(2, 2, 4, 2024, 1);

SELECT 'Base de datos inicializada correctamente' AS mensaje;