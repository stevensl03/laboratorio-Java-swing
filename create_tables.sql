USE testdb;

CREATE TABLE IF NOT EXISTS persona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    tipo VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS estudiante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    codigo INT UNIQUE,
    programa_nombre VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE,
    promedio DECIMAL(3,2) DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS profesor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    tipo_contrato VARCHAR(50),
    salario DECIMAL(10,2) DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS Facultad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(100),
    decano VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS programa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    codigo VARCHAR(20) UNIQUE,
    facultad_id INT,
    FOREIGN KEY (facultad_id) REFERENCES Facultad(id)
);

CREATE TABLE IF NOT EXISTS curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    codigo VARCHAR(20) UNIQUE,
    creditos INT DEFAULT 3,
    programa_id INT,
    FOREIGN KEY (programa_id) REFERENCES programa(id)
);

CREATE TABLE IF NOT EXISTS inscripcion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT,
    curso_id INT,
    fecha_inscripcion DATE DEFAULT (CURDATE()),
    estado VARCHAR(20) DEFAULT 'ACTIVA',
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id),
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE IF NOT EXISTS curso_profesor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curso_id INT,
    profesor_id INT,
    semestre VARCHAR(20),
    año INT,
    FOREIGN KEY (curso_id) REFERENCES curso(id),
    FOREIGN KEY (profesor_id) REFERENCES profesor(id)
);

INSERT INTO Facultad (nombre, ubicacion, decano) VALUES 
('Facultad de Ingeniería', 'Edificio A', 'Dr. Juan Pérez'),
('Facultad de Ciencias', 'Edificio B', 'Dra. María García');

INSERT INTO programa (nombre, codigo, facultad_id) VALUES 
('Ingeniería de Sistemas', 'ISIS', 1),
('Matemáticas', 'MATH', 2);

INSERT INTO personas (nombres, apellidos, email, tipo) VALUES 
('Juan', 'Pérez', 'juan@email.com', 'Estudiante'),
('María', 'García', 'maria@email.com', 'Profesor');

INSERT INTO estudiante (nombres, apellidos, email, codigo, programa_nombre, activo, promedio) VALUES 
('Juan', 'Pérez', 'juan@email.com', 12345, 'Ingeniería de Sistemas', TRUE, 4.2),
('Ana', 'López', 'ana@email.com', 12346, 'Matemáticas', TRUE, 3.8);

INSERT INTO profesor (nombres, apellidos, email, tipo_contrato, salario) VALUES 
('María', 'García', 'maria@email.com', 'Tiempo Completo', 5000000),
('Carlos', 'Rodríguez', 'carlos@email.com', 'Medio Tiempo', 2500000);

INSERT INTO curso (nombre, codigo, creditos, programa_id) VALUES 
('Programación I', 'PROG1', 3, 1),
('Cálculo I', 'CALC1', 4, 2);

SELECT 'Base de datos inicializada correctamente' as mensaje;
