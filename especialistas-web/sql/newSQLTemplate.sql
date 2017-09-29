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

insert into CitaEntity (id, comentarios) values (100,'xxxxxx');
insert into CitaEntity (id, comentarios) values (200,' ');


delete from OrdenMedicaEntity;

insert into OrdenMedicaEntity (id, descripcion) values (100,'xxxxxx');
insert into OrdenMedicaEntity (id, descripcion) values (200,' ');


delete from HoraEntity;

delete from MedicoEntity

delete from HospitalEntity;

delete from ConsultorioEntity;

insert into HospitalEntity(id, nombre) values (1, 'Reina');
insert into HospitalEntity(id, nombre) values (2, 'Casa');

insert into ConsultorioEntity(id, referenciaConsultorio) values (1, 'aaa');
insert into ConsultorioEntity(id, referenciaConsultorio) values (2, 'bbb');