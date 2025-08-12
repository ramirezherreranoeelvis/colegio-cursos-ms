-- Creamos la tabla para los Cursos (Courses)
CREATE TABLE courses (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

---

-- Creamos la tabla para las Aulas (Classrooms)
CREATE TABLE classrooms (
    id VARCHAR(255) PRIMARY KEY,
    number INT,
    floor INT
);

---

-- Creamos la tabla para los Horarios (Schedules)
CREATE TABLE schedules (
    id VARCHAR(255) PRIMARY KEY,
    day ENUM('LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES') NOT NULL,
    startTime TIME NOT NULL,
    endTime TIME NOT NULL,

    -- Columnas para las claves foráneas
    course_id VARCHAR(255) NOT NULL,
    -- 1. Se quita el "NOT NULL" para permitir valores nulos.
    classroom_id VARCHAR(255), 

    -- Definición de la llave foránea hacia la tabla Courses
    CONSTRAINT fk_schedule_course
        FOREIGN KEY (course_id) REFERENCES Courses(id)
        ON DELETE CASCADE,

    -- Definición de la llave foránea hacia la tabla Classrooms
    CONSTRAINT fk_schedule_classroom
        FOREIGN KEY (classroom_id) REFERENCES Classrooms(id)
        -- 2. Se quita la regla "ON DELETE". La acción por defecto es "ON DELETE RESTRICT",
        -- que previene que se borre un aula si un horario la está usando.
);