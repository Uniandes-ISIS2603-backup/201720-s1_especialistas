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

delete from TarjetaEntity;

delete from PagoEntity;

insert into CitaEntity (id, comentarios) values (100,'xxxxxx');
insert into CitaEntity (id, comentarios) values (200,' ');


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


delete from ConsultorioEntity;


delete from HospitalEntity;

insert into HospitalEntity(id, nombre) values (1, 'Reina');
insert into HospitalEntity(id, nombre) values (2, 'Casa');

insert into ConsultorioEntity(id, referenciaConsultorio) values (1, 'aaa');
insert into ConsultorioEntity(id, referenciaConsultorio) values (2, 'bbb');
