/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.CitaEntity;
import co.edu.uniandes.csw.especialistas.entities.ExamenEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import co.edu.uniandes.csw.especialistas.entities.OrdenMedicaEntity;
import co.edu.uniandes.csw.especialistas.entities.OrdenMedicaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jr.restom10
 */
@RunWith(Arquillian.class)
public class OrdenMedicaPersistenceTest {

    /**
     * Inyección de la dependencia a la clase OrdenMedicaPersistence cuyos
     * métodos se van a probar.
     */
    @Inject
    private OrdenMedicaPersistence persistence;
    
    /**
     * Inyección de la persistencia de citas
     */
    @Inject
    private CitaPersistence citaPersistence;
    
    /**
     * Inyección de la persistencia de medicamentos
     */
    @Inject
    private MedicamentoPersistence medicamentoPersistence;
    
    /**
     * Inyección de la persistencia de exámenes
     */
    @Inject
    private ExamenPersistence examenPersistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     *
     */
    private List<OrdenMedicaEntity> data = new ArrayList<OrdenMedicaEntity>();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de OrdenMedica, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrdenMedicaEntity.class.getPackage())
                .addPackage(OrdenMedicaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public OrdenMedicaPersistenceTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() {
        em.createQuery("delete from OrdenMedicaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 10; i++) {
            OrdenMedicaEntity entity = factory.manufacturePojo(OrdenMedicaEntity.class);
            //List<MedicamentoEntity> medicamentos = createMedicamentos();
            //List<ExamenEntity> examenes = createExamenes();
            //CitaEntity cita = createCita();
            //entity.setCita(cita);
            //entity.setExamenes(examenes);
            //entity.setMedicamentos(medicamentos);
            em.persist(entity);
            data.add(entity);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class OrdenMedicaPersistence.
     */
    @Test
    public void createOrdenMedicaEntityTest() {
        PodamFactory factory = new PodamFactoryImpl();
        OrdenMedicaEntity newEntity = factory.manufacturePojo(OrdenMedicaEntity.class);
        OrdenMedicaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        OrdenMedicaEntity entity = em.find(OrdenMedicaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCita(), entity.getCita());
        //Assert.assertEquals(newEntity.getMedicamentos(), entity.getMedicamentos());
        //Assert.assertEquals(newEntity.getExamenes(), entity.getExamenes());
    }

    /**
     * Test of findById method, of class OrdenMedicaPersistence.
     */
    @Test
    public void getOrdenMedicaTest() {
        OrdenMedicaEntity entity = data.get(0);
        OrdenMedicaEntity newEntity = persistence.findById(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
}

//    /**
//     * Test of deleteById method, of class OrdenMedicaPersistence.
//     */
//    
//
//    /**
//     * Test of update method, of class OrdenMedicaPersistence.
//     */
//    @Test
//    public void deleteOrdenMedicaTest() {
//        OrdenMedicaEntity entity = data.get(0);
//        persistence.deleteById(entity.getId());
//        OrdenMedicaEntity deleted = em.find(OrdenMedicaEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//
//        newEntity.setId(entity.getId());
//
//        persistence.update(newEntity);
//
//        OrdenMedicaEntity resp = em.find(OrdenMedicaEntity.class, entity.getId());
//
//        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
//    }
//
//    /**
//     * Test of findAll method, of class OrdenMedicaPersistence.
//     */
//    public void updateOrdenMedicaTest() {
//        OrdenMedicaEntity entity = data.get(0);
//        PodamFactory factory = new PodamFactoryImpl();
//        OrdenMedicaEntity newEntity = factory.manufacturePojo(OrdenMedicaEntity.class);
//            }
//            Assert.assertTrue(found);
//        }
//    }
//    
//    /**
//     * Método encargado de crear y persistir una cita
//     * @return cita creada
//     */
//    private CitaEntity createCita()
//    {
//        PodamFactory factory = new PodamFactoryImpl();
//        CitaEntity cita = factory.manufacturePojo(CitaEntity.class);
//        if(citaPersistence.findById(cita.getId()) == null){
//            citaPersistence.create(cita);
//        }
//        return cita;
//    }
//    
//    /**
//     * Método encargado de crear una lista de medicamentos
//     * @return lista de medicamentos
//     */
//    private List<MedicamentoEntity> createMedicamentos(){
//        List<MedicamentoEntity> list = new ArrayList<>();
//        PodamFactory factory = new PodamFactoryImpl();
//        for(int i=0; i < 10; i++){
//            MedicamentoEntity medicamento = factory.manufacturePojo(MedicamentoEntity.class);
//            if(medicamentoPersistence.findById(medicamento.getId()) == null){
//                medicamentoPersistence.create(medicamento);
//            }
//            list.add(medicamento);
//        }
//        return list;
//    }
//    
//    /**
//     * Método encargado de crear una lista de exámenes
//     * @return lista de exámenes
//     */
//    private List<ExamenEntity> createExamenes(){
//        List<ExamenEntity> list = new ArrayList<>();
//        PodamFactory factory = new PodamFactoryImpl();
//        for(int i=0; i < 10; i++){
//            ExamenEntity examen = factory.manufacturePojo(ExamenEntity.class);
//            if(examenPersistence.find(examen.getId()) == null){
//                examenPersistence.create(examen);
//            }
//            list.add(examen);
//        }
//        return list;
//    }
//
//}
//    @Test
//    public void getOrdenMedicasTest() {
//        List<OrdenMedicaEntity> list = persistence.findAll();
//        Assert.assertEquals(data.size(), list.size());
//        for (OrdenMedicaEntity ent : list) {
//            boolean found = false;
//            for (OrdenMedicaEntity entity : data) {
//                if (ent.getId().equals(entity.getId())) {
//                    found = true;
//                }
