/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

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
     * Inyección de la dependencia a la clase OrdenMedicaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private OrdenMedicaPersistence persistence;

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
        for (int i = 0; i < 3; i++) {
            OrdenMedicaEntity entity = factory.manufacturePojo(OrdenMedicaEntity.class);

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
    public void TestCreate()  {
       PodamFactory factory = new PodamFactoryImpl();
    OrdenMedicaEntity newEntity = factory.manufacturePojo(OrdenMedicaEntity.class);
    OrdenMedicaEntity result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    OrdenMedicaEntity entity = em.find(OrdenMedicaEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getId(), entity.getId());
    Assert.assertEquals(newEntity.getCita(), entity.getCita());
    Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    Assert.assertEquals(newEntity.getExamenes(), entity.getExamenes());
    Assert.assertEquals(newEntity.getMedicamentos(), entity.getMedicamentos());
    
        Assert.assertEquals(false, entity.equals(null));
        Assert.assertEquals(false, entity.equals("objet"));
        Assert.assertEquals(true, entity.equals(entity)); 
        
        Assert.assertEquals(newEntity.hashCode(), entity.hashCode());
        entity.setId(null);
        Assert.assertEquals(false, entity.equals(entity));
        Assert.assertEquals(false, entity.equals(null));
        Assert.assertEquals(false, newEntity.equals(entity));
        Assert.assertEquals(entity.hashCode(), entity.hashCode());
    }

    /**
     * Test of findById method, of class OrdenMedicaPersistence.
     */
    @Test
public void testFind() {
    OrdenMedicaEntity entity = data.get(0);
    OrdenMedicaEntity newEntity = persistence.findById(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Test of deleteById method, of class OrdenMedicaPersistence.
     */
    @Test
public void testDelete() {
    OrdenMedicaEntity entity = data.get(0);
    persistence.deleteById(entity.getId());
    OrdenMedicaEntity deleted = em.find(OrdenMedicaEntity.class, entity.getId());
    Assert.assertNull(deleted);
    }

    /**
     * Test of update method, of class OrdenMedicaPersistence.
     */
    @Test
public void testUpdate() {
    OrdenMedicaEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    OrdenMedicaEntity newEntity = factory.manufacturePojo(OrdenMedicaEntity.class);

    newEntity.setId(entity.getId());

    persistence.update(newEntity);

    OrdenMedicaEntity resp = em.find(OrdenMedicaEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
    }

    /**
     * Test of findAll method, of class OrdenMedicaPersistence.
     */
   @Test
public void testFindAll() {
    List<OrdenMedicaEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (OrdenMedicaEntity ent : list) {
        boolean found = false;
        for (OrdenMedicaEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }
    }
    
}
