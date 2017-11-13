/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.logic;

import co.edu.uniandes.csw.especialistas.ejb.FarmaciaLogic;
import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import co.edu.uniandes.csw.especialistas.persistence.FarmaciaPersitence;
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
public class FarmaciaLogicTest {
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FarmaciaEntity.class.getPackage())
                .addPackage(FarmaciaPersitence.class.getPackage())
                .addPackage(FarmaciaLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private FarmaciaPersitence persistence;
    
    @Inject
    private FarmaciaLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<FarmaciaEntity> data = new ArrayList<>();
    
    public FarmaciaLogicTest() {
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
        em.createQuery("delete from FarmaciaEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            FarmaciaEntity entity = factory.manufacturePojo(FarmaciaEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    private FarmaciaEntity entity = new FarmaciaEntity();

    /**
     * Test of create method, of class FarmaciaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        FarmaciaEntity entity = factory.manufacturePojo(FarmaciaEntity.class);

        System.out.println(entity.getId());
        logic.createFarmacia(entity);
        
        assertEquals(logic.getFarmacia(entity.getId()), entity);
        assertEquals(logic.getFarmacia(entity.getId()), entity);
    }
    
    /**
     * Test of update method, of class FarmaciaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        FarmaciaEntity entity = factory.manufacturePojo(FarmaciaEntity.class);

        logic.createFarmacia(entity);
        
        assertEquals(logic.updateFarmacia(entity), entity);
    }
    
    /**
     * Test of findAll method, of class FarmaciaPersistence.
     */
    @Test
    public void testGet() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        FarmaciaEntity entity = factory.manufacturePojo(FarmaciaEntity.class);
        
        logic.createFarmacia(entity);
        assertEquals(logic.getFarmacias().contains(entity), true);
    }
    
    /**
     * Test of delete method, of class FarmaciaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        FarmaciaEntity entity = factory.manufacturePojo(FarmaciaEntity.class);
        
        logic.createFarmacia(entity);
        logic.deleteFarmacia(entity.getId());
        assertEquals(logic.getFarmacia(entity.getId()), null);
    }
    
}
