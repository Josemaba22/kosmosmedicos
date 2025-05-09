/* Creacion de Doctores */
INSERT INTO doctores (nombre, apellido_paterno, apellido_materno,especialidad) VALUES ('Jose Manuel', 'Barraza', 'Aispuro','Neurologo');
INSERT INTO doctores (nombre, apellido_paterno, apellido_materno,especialidad) VALUES ('Simon', 'Pedro', 'Piedra','Traumatologo');
INSERT INTO doctores (nombre, apellido_paterno, apellido_materno,especialidad) VALUES ('Jesus', 'Cristo', 'De Nazaret','Cardiologo');

/* Creacion de Pacientes */
INSERT INTO pacientes (nombre, fecha_nacimiento, email) VALUES ('Elias Israel','1995-01-01','elias.israel@mail.com');
INSERT INTO pacientes (nombre, fecha_nacimiento, email) VALUES ('Juan Pérez','1990-05-15','juan.perez@example.com');
INSERT INTO pacientes (nombre, fecha_nacimiento, email) VALUES ('Maria Garcia','1985-10-22','maria.garcia@example.com');
INSERT INTO pacientes (nombre, fecha_nacimiento, email) VALUES ('Carlos Rodriguez', '2002-03-08','carlos.rodriguez@example.com');

/* Creacion de citas */
INSERT INTO citas (fecha_hora, doctor_id, paciente_id) VALUES('2025-05-12 09:00:00',1L,1L);
INSERT INTO citas (fecha_hora, doctor_id, paciente_id) VALUES('2025-05-12 10:00:00',1L,2L);
INSERT INTO citas (fecha_hora, doctor_id, paciente_id) VALUES('2025-05-12 12:00:00',1L,3L);

