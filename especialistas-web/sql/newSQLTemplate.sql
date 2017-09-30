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

delete from UsuarioEntity;
insert into UsuarioEntity (id, cedula, nombre) values (8,123456,'juan');


delete from TarjetaEntity;
insert into TarjetaEntity (id, numero) values (8,123456);

delete from PagoEntity;
insert into PagoEntity (id, ref) values (8,123456);

insert into CitaEntity (id, comentarios) values (100,'xxxxxx');
insert into CitaEntity (id, comentarios) values (200,' ');


delete from OrdenMedicaEntity;

insert into OrdenMedicaEntity (id, descripcion) values (100,'xxxxxx');
insert into OrdenMedicaEntity (id, descripcion) values (200,' ');


delete from HoraEntity;

delete from MedicoEntity;

insert into MedicoEntity(id, nombre, especializacion, ) values (204, 'Juan Perez', 'GENERAL');
insert into MedicoEntity(id, nombre, especializacion) values (205, 'Pedro Perez', 'GENERAL');
insert into MedicoEntity(id, nombre, especializacion) values (206, 'Juan Rodriguez', 'ODONTOLOGIA');

insert into HoraEntity(id, horaInicio, horaFin, medico_id) values (101, to_date('sep 26, 2017 8:57:34 PM'), to_date('sep 26, 2017 9:17:34 PM'), 204);
insert into HoraEntity(id, horaInicio, horaFin, medico_id) values (102, to_date('sep 26, 2017 9:57:34 PM'), to_date('sep 26, 2017 10:17:34 PM'), 204);
insert into HoraEntity(id, horaInicio, horaFin, medico_id) values (103, to_date('sep 27, 2017 8:57:34 PM'), to_date('sep 27, 2017 9:17:34 PM'), 204);


delete from HospitalEntity;

delete from ConsultorioEntity;

insert into HospitalEntity(id, nombre) values (1, 'Reina');
insert into HospitalEntity(id, nombre) values (2, 'Casa');

insert into ConsultorioEntity(id, referenciaConsultorio) values (1, 'aaa');
insert into ConsultorioEntity(id, referenciaConsultorio) values (2, 'bbb');