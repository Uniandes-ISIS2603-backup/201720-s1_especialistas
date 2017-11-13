/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.logic;

import co.edu.uniandes.csw.especialistas.ejb.HospitalLogic;
import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import co.edu.uniandes.csw.especialistas.persistence.HospitalPersistence;
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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ce.quintero
 */
@RunWith(Arquillian.class)
public class HospitalLogicTest {
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HospitalEntity.class.getPackage())
                .addPackage(HospitalPersistence.class.getPackage())
                .addPackage(HospitalLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private HospitalPersistence persistence;
    
    @Inject
    private HospitalLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<HospitalEntity> data = new ArrayList<>();
    
    public HospitalLogicTest() {
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
            try{
                utx.rollback();
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
    
    
    private void clearData() {
        em.createQuery("delete from HospitalEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            HospitalEntity entity = factory.manufacturePojo(HospitalEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    private HospitalEntity entity = new HospitalEntity();

    /**
     * Test of create method, of class HospitalPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        HospitalEntity entity = factory.manufacturePojo(HospitalEntity.class);

        System.out.println(entity.getId());
        logic.createHospital(entity);
        
        assertEquals(logic.getHospital(entity.getId()), entity);
        assertEquals(logic.getHospital(entity.getId()), entity);
    }
    
    /**
     * Test of update method, of class HospitalPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        HospitalEntity entity = factory.manufacturePojo(HospitalEntity.class);

        logic.createHospital(entity);
        
        assertEquals(logic.updateHospital(entity), entity);
    }
    
    /**
     * Test of findAll method, of class HospitalPersistence.
     */
    @Test
    public void testGet() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        HospitalEntity entity = factory.manufacturePojo(HospitalEntity.class);
        
        logic.createHospital(entity);
        assertEquals(logic.getHospitales().contains(entity), true);
    }
    
    /**
     * Test of delete method, of class HospitalPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        HospitalEntity entity = factory.manufacturePojo(HospitalEntity.class);
        
        logic.createHospital(entity);
        logic.deleteHospital(entity.getId());
        assertEquals(logic.getHospital(entity.getId()), null);
    }
    
}
