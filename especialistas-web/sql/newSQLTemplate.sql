/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jr.restom10
 * Created: 20/09/2017
 */

delete from CitaEntity;

insert into CitaEntity (id, comentarios,hora_id) values (100,'Revision general',101);
insert into CitaEntity (id, comentarios,hora_id) values (200,'Chequeo de avances del tumor',102);

delete from UsuarioEntity;

delete from LaboratorioEntity;

delete from ExamenEntity;

insert into UsuarioEntity (id, cedula, nombre,rol) values (8,123456,'juan','USUARIO');
insert into UsuarioEntity (id, cedula, nombre,rol) values (9,123456,'pedro','USUARIO');
insert into UsuarioEntity (id, cedula, nombre,rol) values (10,123456,'paulo','USUARIO');
insert into UsuarioEntity (id, cedula, nombre,rol) values (11,123456,'Ryusaky','USUARIO');
insert into UsuarioEntity (id, cedula, nombre,rol) values (12,123456,'kira','USUARIO');
insert into UsuarioEntity (id, cedula, nombre,rol) values (1,1256,'Admin','ADMINISTRADOR');


delete from TarjetaEntity;
insert into TarjetaEntity (id, numero) values (8,123456);

delete from PagoEntity;
insert into PagoEntity (id, ref) values (8,123456);

delete from OrdenMedicaEntity;

insert into OrdenMedicaEntity (id, descripcion) values (100,'xxxxxx');
insert into OrdenMedicaEntity (id, descripcion) values (200,' ');

delete from HoraEntity;

delete from MedicoEntity;

delete from FarmaciaEntity;

insert into FarmaciaEntity (id, nombre, radio) values (100,'xxxxxx',2);
insert into FarmaciaEntity (id, nombre, radio) values (200,'yyyyyy',3);

delete from MedicamentoEntity;

insert into MedicamentoEntity (id, nombre, precio) values (100,'xxxxxx',2.213);
insert into MedicamentoEntity (id, nombre, precio) values (200,'yyyyyy',3.131);

delete from MedicoEntity;

insert into MedicoEntity(id, nombre, especializacion) values (204, 'Juan Perez', 0);
insert into MedicoEntity(id, nombre, especializacion) values (205, 'Pedro Perez', 0);
insert into MedicoEntity(id, nombre, especializacion) values (206, 'Juan Rodriguez', 1);

insert into HoraEntity(id, horaInicio, horaFin, medico_id) values (101, timestamp('2017-11-27 09:00:00'), timestamp('2017-11-27 09:20:00'), 204);
insert into HoraEntity(id, horaInicio, horaFin, medico_id) values (102, timestamp('2017-11-29 12:00:00'), timestamp('2017-11-29 12:20:00'), 204);
insert into HoraEntity(id, horaInicio, horaFin, medico_id) values (103, timestamp('2017-11-31 15:00:00'), timestamp('2017-11-31 15:20:00'), 204);


delete from ConsultorioEntity;


delete from HospitalEntity;

insert into HospitalEntity(id, nombre) values (1, 'Reina');
insert into HospitalEntity(id, nombre) values (2, 'Casa');

insert into ConsultorioEntity(id, referenciaConsultorio) values (1, 'aaa');
insert into ConsultorioEntity(id, referenciaConsultorio) values (2, 'bbb');

update HoraEntity set consultorio_id = 1;

insert into LaboratorioEntity (id, nombre) values (5, 'Clinilab');
insert into LaboratorioEntity (id, nombre) values (6, 'Laboratorio Central');
insert into LaboratorioEntity (id, nombre) values (7, 'Laboratorio Clínico Sanitas');

insert into ExamenEntity (id, nombre, precio, recomendacion ) values(3, 'análisis de sangre', 10000, 'ayuno de 8 a 12 horas');
insert into ExamenEntity (id, nombre, precio, recomendacion ) values(54, 'electrocardiograma', 25500, 'ninguna');
insert into ExamenEntity (id, nombre, precio, recomendacion ) values(155, 'tilt test', 175000, 'asistir con ropa cómoda');
insert into ExamenEntity (id, nombre, precio, recomendacion ) values(256, 'audiometría', 35000, 'asistir con los canales auditivos limpios');
insert into ExamenEntity (id, nombre, precio, recomendacion ) values(257, 'análisis de orina', 12000, 'ayuno de 8 a 12 horas');
insert into ExamenEntity (id, nombre, precio, recomendacion ) values(256, '', 35000, 'asistir con los canales auditivos limpios');

delete from HosEntity;

insert into HosEntity (id, nombre) values (500, 'Hospital de muñecos');
insert into HosEntity (id, nombre) values (600, 'Hospital de carros');

delete from ConsEntity;

insert into ConsEntity (id, numero,hospital) values (600, 'ABC123',500);
insert into ConsEntity (id, numero,hospital) values (700, 'BCD234',600);
