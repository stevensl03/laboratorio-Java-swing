-- Script de inicialización para Oracle Database
-- Crear usuario y esquema
CREATE USER testdb IDENTIFIED BY 1234;
GRANT CONNECT, RESOURCE, DBA TO testdb;
GRANT UNLIMITED TABLESPACE TO testdb;

-- Conectar como testdb
CONNECT testdb/1234@XE;

-- Tabla persona (base para estudiantes y profesores)
CREATE TABLE persona (
    id NUMBER(10) PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    apellido VARCHAR2(100) NOT NULL,
    correo VARCHAR2(150) UNIQUE NOT NULL,
    tipo VARCHAR2(50) NOT NULL
);

-- Tabla facultad
CREATE TABLE facultad (
    id NUMBER(10) PRIMARY KEY,
    nombre VARCHAR2(200) NOT NULL,
    decano_id NUMBER(10)
);

-- Tabla programa
CREATE TABLE programa (
    id NUMBER(10) PRIMARY KEY,
    nombre VARCHAR2(200) NOT NULL,
    duracion NUMBER(5,2) NOT NULL,
    registro DATE NOT NULL,
    facultad_id NUMBER(10),
    FOREIGN KEY (facultad_id) REFERENCES facultad(id)
);

-- Tabla profesor
CREATE TABLE profesor (
    id NUMBER(10) PRIMARY KEY,
    facultad_id NUMBER(10),
    tipo_contrato VARCHAR2(100),
    FOREIGN KEY (id) REFERENCES persona(id),
    FOREIGN KEY (facultad_id) REFERENCES facultad(id)
);

-- Tabla estudiante
CREATE TABLE estudiante (
    id NUMBER(10) PRIMARY KEY,
    codigo VARCHAR2(20) UNIQUE NOT NULL,
    programa_id NUMBER(10),
    activo NUMBER(1) DEFAULT 1,
    promedio NUMBER(3,2),
    FOREIGN KEY (id) REFERENCES persona(id),
    FOREIGN KEY (programa_id) REFERENCES programa(id)
);

-- Tabla curso
CREATE TABLE curso (
    id NUMBER(10) PRIMARY KEY,
    nombre VARCHAR2(200) NOT NULL,
    programa_id NUMBER(10),
    activo NUMBER(1) DEFAULT 1,
    FOREIGN KEY (programa_id) REFERENCES programa(id)
);

-- Tabla inscripcion
CREATE TABLE inscripcion (
    id NUMBER(10) PRIMARY KEY,
    anio NUMBER(4) NOT NULL,
    semestre NUMBER(1) NOT NULL,
    estudiante_id NUMBER(10),
    curso_id NUMBER(10),
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id),
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

-- Tabla cursoprofesor
CREATE TABLE cursoprofesor (
    id NUMBER(10) PRIMARY KEY,
    curso_id NUMBER(10),
    profesor_id NUMBER(10),
    anio NUMBER(4),
    semestre NUMBER(1),
    FOREIGN KEY (curso_id) REFERENCES curso(id),
    FOREIGN KEY (profesor_id) REFERENCES profesor(id)
);

-- Secuencias para auto-incremento
CREATE SEQUENCE persona_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE facultad_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE programa_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE curso_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE inscripcion_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE cursoprofesor_seq START WITH 1 INCREMENT BY 1;

-- Triggers para auto-incremento
CREATE OR REPLACE TRIGGER persona_trigger
    BEFORE INSERT ON persona
    FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := persona_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER facultad_trigger
    BEFORE INSERT ON facultad
    FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := facultad_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER programa_trigger
    BEFORE INSERT ON programa
    FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := programa_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER curso_trigger
    BEFORE INSERT ON curso
    FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := curso_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER inscripcion_trigger
    BEFORE INSERT ON inscripcion
    FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := inscripcion_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER cursoprofesor_trigger
    BEFORE INSERT ON cursoprofesor
    FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := cursoprofesor_seq.NEXTVAL;
    END IF;
END;
/

-- Datos de ejemplo
INSERT INTO persona (id, nombre, apellido, correo, tipo) VALUES 
(1, 'Juan', 'Pérez', 'juan@email.com', 'Estudiante');
INSERT INTO persona (id, nombre, apellido, correo, tipo) VALUES 
(2, 'María', 'García', 'maria@email.com', 'Profesor');
INSERT INTO persona (id, nombre, apellido, correo, tipo) VALUES 
(3, 'Ana', 'López', 'ana@email.com', 'Estudiante');
INSERT INTO persona (id, nombre, apellido, correo, tipo) VALUES 
(4, 'Carlos', 'Rodríguez', 'carlos@email.com', 'Profesor');
INSERT INTO persona (id, nombre, apellido, correo, tipo) VALUES 
(5, 'Dr. Juan', 'Pérez', 'decano1@email.com', 'Decano');
INSERT INTO persona (id, nombre, apellido, correo, tipo) VALUES 
(6, 'Dra. María', 'García', 'decano2@email.com', 'Decano');

INSERT INTO facultad (id, nombre, decano_id) VALUES 
(1, 'Facultad de Ingeniería', 5);
INSERT INTO facultad (id, nombre, decano_id) VALUES 
(2, 'Facultad de Ciencias', 6);

INSERT INTO programa (id, nombre, duracion, registro, facultad_id) VALUES 
(1, 'Ingeniería de Sistemas', 5.0, DATE '2020-01-15', 1);
INSERT INTO programa (id, nombre, duracion, registro, facultad_id) VALUES 
(2, 'Matemáticas', 4.0, DATE '2020-01-15', 2);

INSERT INTO profesor (id, facultad_id, tipo_contrato) VALUES 
(2, 1, 'Tiempo Completo');
INSERT INTO profesor (id, facultad_id, tipo_contrato) VALUES 
(4, 2, 'Medio Tiempo');

INSERT INTO estudiante (id, codigo, programa_id, activo, promedio) VALUES 
(1, '12345', 1, 1, 4.2);
INSERT INTO estudiante (id, codigo, programa_id, activo, promedio) VALUES 
(3, '12346', 2, 1, 3.8);

INSERT INTO curso (id, nombre, programa_id, activo) VALUES 
(1, 'Programación I', 1, 1);
INSERT INTO curso (id, nombre, programa_id, activo) VALUES 
(2, 'Matemáticas Básicas', 2, 1);

INSERT INTO inscripcion (id, anio, semestre, estudiante_id, curso_id) VALUES 
(1, 2024, 1, 1, 1);
INSERT INTO inscripcion (id, anio, semestre, estudiante_id, curso_id) VALUES 
(2, 2024, 1, 3, 2);

INSERT INTO cursoprofesor (id, curso_id, profesor_id, anio, semestre) VALUES 
(1, 1, 2, 2024, 1);
INSERT INTO cursoprofesor (id, curso_id, profesor_id, anio, semestre) VALUES 
(2, 2, 4, 2024, 1);

COMMIT;
