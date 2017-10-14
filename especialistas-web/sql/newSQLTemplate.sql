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

delete from UsuarioEntity;

delete from LaboratorioEntity;

delete from ExamenEntity;

insert into UsuarioEntity (id, cedula, nombre) values (8,123456,'juan');


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

insert into HoraEntity(id, horaInicio, horaFin, medico_id) values (101, timestamp('2017-07-22 03:00:00'), timestamp('2017-07-22 05:00:00'), 204);
insert into HoraEntity(id, horaInicio, horaFin, medico_id) values (102, timestamp('2018-07-22 12:00:00'), timestamp('2018-07-22 15:00:00'), 204);
insert into HoraEntity(id, horaInicio, horaFin, medico_id) values (103, timestamp('2017-08-22 18:00:00'), timestamp('2017-08-22 21:00:00'), 204);


delete from ConsultorioEntity;


delete from HospitalEntity;

insert into HospitalEntity(id, nombre) values (1, 'Reina');
insert into HospitalEntity(id, nombre) values (2, 'Casa');

insert into ConsultorioEntity(id, referenciaConsultorio) values (1, 'aaa');
insert into ConsultorioEntity(id, referenciaConsultorio) values (2, 'bbb');



insert into LaboratorioEntity (id, nombre) values (2, 'nuevo lab');
insert into LaboratorioEntity (id, nombre) values (20, 'Laboratorio Central');

insert into ExamenEntity (id, nombre, precio, recomendacion ) values(3, 'sangre', 2000, 'ir en ayunas');
insert into ExamenEntity (id, nombre, precio, recomendacion ) values(54, 'examen', 548000, 'ninguna');
