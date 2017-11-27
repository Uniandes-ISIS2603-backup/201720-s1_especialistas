/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jr.restom10
 * Created: 20/09/2017
 */

delete from OrdenMedicaEntity;
delete from CitaEntity;
delete from ConsultorioEntity;
delete from HospitalEntity;
delete from UsuarioEntity;
delete from LaboratorioEntity;
delete from ExamenEntity;
delete from HoraEntity;
delete from MedicoEntity;
delete from FarmaciaEntity;
delete from PagoEntity;
delete from TarjetaEntity;
delete from MedicamentoEntity;
delete from MedicoEntity;


insert into UsuarioEntity (id, cedula, nombre) values (8,123456,'juan');
insert into UsuarioEntity (id, cedula, nombre) values (9,123456,'pedro');
insert into UsuarioEntity (id, cedula, nombre) values (10,123456,'paulo');
insert into UsuarioEntity (id, cedula, nombre) values (11,123456,'leon');
insert into UsuarioEntity (id, cedula, nombre) values (12,123456,'kira');



insert into TarjetaEntity (id, numero) values (8,123456);

insert into PagoEntity (id, ref) values (8,123456);





insert into FarmaciaEntity (id, nombre, radio) values (100,'xxxxxx',2);
insert into FarmaciaEntity (id, nombre, radio) values (200,'yyyyyy',3);



insert into MedicamentoEntity (id, nombre, precio) values (100,'xxxxxx',2.213);
insert into MedicamentoEntity (id, nombre, precio) values (200,'yyyyyy',3.131);



insert into MedicoEntity(id, nombre, especializacion) values (204, 'Juan Perez', 0);
insert into MedicoEntity(id, nombre, especializacion) values (205, 'Pedro Perez', 0);
insert into MedicoEntity(id, nombre, especializacion) values (206, 'Juan Rodriguez', 1);

insert into HoraEntity(id, horaInicio, horaFin, medico_id) values (101, timestamp('2017-07-22 03:00:00'), timestamp('2017-07-22 05:00:00'), 204);
insert into HoraEntity(id, horaInicio, horaFin, medico_id) values (102, timestamp('2018-07-22 12:00:00'), timestamp('2018-07-22 15:00:00'), 204);
insert into HoraEntity(id, horaInicio, horaFin, medico_id) values (103, timestamp('2017-08-22 18:00:00'), timestamp('2017-08-22 21:00:00'), 204);

insert into CitaEntity (id, comentarios,hora_id) values (100,'Revision general',101);
insert into CitaEntity (id, comentarios,hora_id) values (200,'Chequeo de avances del tumor',101);

insert into OrdenMedicaEntity (id, descripcion,cita_id) values (100,'Sorry :(',200);
insert into OrdenMedicaEntity (id, descripcion,cita_id) values (200,'cuidar el colesterol',100);



insert into HospitalEntity(id, nombre) values (1, 'Reina');
insert into HospitalEntity(id, nombre) values (2, 'Casa');

insert into ConsultorioEntity(id, numero) values (1, 'aaa');
insert into ConsultorioEntity(id, numero) values (2, 'bbb');

update HoraEntity set consultorio_id = 1;

insert into LaboratorioEntity (id, nombre) values (2, 'nuevo lab');
insert into LaboratorioEntity (id, nombre) values (20, 'Laboratorio Central');

insert into ExamenEntity (id, nombre, precio, recomendacion ) values(3, 'análisis de sangre', 10000, 'ayuno de 8 a 12 horas');
insert into ExamenEntity (id, nombre, precio, recomendacion ) values(54, 'electrocardiograma', 25500, 'ninguna');
insert into ExamenEntity (id, nombre, precio, recomendacion ) values(54, 'tilt test', 175000, 'asistir con ropa cómoda');
insert into ExamenEntity (id, nombre, precio, recomendacion ) values(54, 'tilt test', 175000, 'asistir con ropa cómoda');