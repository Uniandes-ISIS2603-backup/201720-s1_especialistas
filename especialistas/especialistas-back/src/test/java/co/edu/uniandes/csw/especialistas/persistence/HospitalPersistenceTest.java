/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

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
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jl.patarroyo
 */
@RunWith(Arquillian.class)
public class HospitalPersistenceTest {
    
    @Inject
    private HospitalPersistence persistance;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<HospitalEntity> data = new ArrayList<HospitalEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HospitalPersistence.class.getPackage())
                .addPackage(HospitalPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public HospitalPersistenceTest(){
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
        em.createQuery("delete from HospitalEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            HospitalEntity entity = factory.manufacturePojo(HospitalEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class HospitalPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        fail("testCreate()");
    }

    /**
     * Test of delete method, of class HospitalPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        fail("testDelete()");
    }

    /**
     * Test of upadte method, of class HospitalPersistence.
     */
    @Test
    public void testUpadte() throws Exception {
        fail("testUpdate()");
    }

    /**
     * Test of find method, of class HospitalPersistence.
     */
    @Test
    public void testFind() throws Exception {
        fail("testFind()");
    }

    /**
     * Test of findAll method, of class HospitalPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        fail("testFindAll())");
    }

    /**
     * Test of findByReference method, of class HospitalPersistence.
     */
    @Test
    public void testFindByReference() throws Exception {
        fail("testFindByReference()");
    }
    
}
