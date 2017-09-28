/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jl.patarroyo
 */
@RunWith(Arquillian.class)
public class ConsultorioPersistenceTest
{  
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    @Inject
    private HospitalPersistence hospitalPersistence;
    
    @Inject
    private HoraPersistence horaPersistence;
    
    
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConsultorioEntity.class.getPackage())
                .addPackage(ConsultorioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    
    public ConsultorioPersistenceTest() {
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
        em.createQuery("delete from ConsultorioEntity").executeUpdate();
    }
    
    private List<ConsultorioEntity> data = new ArrayList<>();
    
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 100; i++) {
            ConsultorioEntity entity = factory.manufacturePojo(ConsultorioEntity.class);
            List<HoraEntity> horas = crearHoras();
            HospitalEntity hospital = crearHospital();
            entity.setHoras(horas);
            entity.setHospital(hospital);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Inject
    private ConsultorioPersistence persistence;

    /**
     * Test of create method, of class ConsultorioPersistence.
     */
    @Test
    public void testCreate() throws Exception 
    {
        PodamFactory factory = new PodamFactoryImpl();
        ConsultorioEntity newEntity = factory.manufacturePojo(ConsultorioEntity.class);
        ConsultorioEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        ConsultorioEntity entity = em.find(ConsultorioEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getReferenciaConsultorio(), entity.getReferenciaConsultorio());
        Assert.assertEquals(newEntity.getHoras(), entity.getHoras());
        Assert.assertEquals(newEntity.getHospital(), entity.getHospital());
    }

    /**
     * Test of delete method, of class ConsultorioPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        ConsultorioEntity entity = data.get(0);
        persistence.delete(entity.getId());
        ConsultorioEntity deleted = em.find(ConsultorioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of upadte method, of class ConsultorioPersistence.
     */
    @Test
    public void testUpadte() throws Exception 
    {
        ConsultorioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ConsultorioEntity newEntity = factory.manufacturePojo(ConsultorioEntity.class);
        newEntity.setId(entity.getId());
        persistence.upadte(newEntity);
        ConsultorioEntity resp = em.find(ConsultorioEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getReferenciaConsultorio(), resp.getReferenciaConsultorio());
    }

    /**
     * Test of find method, of class ConsultorioPersistence.
     */
    @Test
    public void testFind() throws Exception {
        ConsultorioEntity entity = data.get(0);
        ConsultorioEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getReferenciaConsultorio(), newEntity.getReferenciaConsultorio());
    }

    /**
     * Test of findAll method, of class ConsultorioPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<ConsultorioEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (ConsultorioEntity ent : list) {
        boolean found = false;
        for (ConsultorioEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }
    }

    /**
     * Test of findByReference method, of class ConsultorioPersistence.
     */
    @Test
    public void testFindByNumber() throws Exception 
    {
        ConsultorioEntity entity = data.get(0);
        ConsultorioEntity newEntity = persistence.findByReference(entity.getReferenciaConsultorio());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getReferenciaConsultorio(), newEntity.getReferenciaConsultorio());
    }
    
    /**
     * Método encargado de crear un hospital y persistirlo
     * @return HospitalEntity creado
     */
    private HospitalEntity crearHospital()
    {
        PodamFactory factory = new PodamFactoryImpl();
        HospitalEntity hospital = factory.manufacturePojo(HospitalEntity.class);
        if(hospitalPersistence.find(hospital.getId()) == null){
            hospitalPersistence.create(hospital);
        }
        return hospital;
    }
    
    /**
     * Método encargado de crear una lista con horas
     * @return Lista con horaEntity
     */
    private List<HoraEntity> crearHoras()
    {
        PodamFactory factory = new PodamFactoryImpl();
        List<HoraEntity> list = new ArrayList<>();
        for(int i=0; i < 10; i++){
            HoraEntity hora = factory.manufacturePojo(HoraEntity.class);
            list.add(hora);
            if(horaPersistence.find(hora.getId()) == null){
                horaPersistence.create(hora);
            }
        }
        return list;
    }
}
