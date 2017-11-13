/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.logic;

import co.edu.uniandes.csw.especialistas.ejb.MedicoLogic;
import co.edu.uniandes.csw.especialistas.entities.MedicoEntity;
import co.edu.uniandes.csw.especialistas.persistence.MedicoPersistence;
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
public class MedicoLogicTest {
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedicoEntity.class.getPackage())
                .addPackage(MedicoPersistence.class.getPackage())
                .addPackage(MedicoLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private MedicoPersistence persistence;
    
    @Inject
    private MedicoLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<MedicoEntity> data = new ArrayList<>();
    
    public MedicoLogicTest() {
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
        em.createQuery("delete from MedicoEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            MedicoEntity entity = factory.manufacturePojo(MedicoEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    private MedicoEntity entity = new MedicoEntity();

    /**
     * Test of create method, of class MedicoPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        MedicoEntity entity = factory.manufacturePojo(MedicoEntity.class);

        System.out.println(entity.getId());
        logic.createMedico(entity);
        
        assertEquals(logic.getMedico(entity.getId()), entity);
        assertEquals(logic.getMedico(entity.getId()), entity);
    }
    
    /**
     * Test of update method, of class MedicoPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        MedicoEntity entity = factory.manufacturePojo(MedicoEntity.class);

        logic.createMedico(entity);
        
        assertEquals(logic.updateMedico(entity.getId(), entity), entity);
    }
    
    /**
     * Test of findAll method, of class MedicoPersistence.
     */
    @Test
    public void testGet() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        MedicoEntity entity = factory.manufacturePojo(MedicoEntity.class);
        
        logic.createMedico(entity);
        assertEquals(logic.getMedicos().contains(entity), true);
    }
    
    /**
     * Test of delete method, of class MedicoPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        MedicoEntity entity = factory.manufacturePojo(MedicoEntity.class);
        
        logic.createMedico(entity);
        logic.deleteMedico(entity.getId());
        assertEquals(logic.getMedico(entity.getId()), null);
    }
    
}
