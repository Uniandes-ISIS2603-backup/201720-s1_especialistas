/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.logic;

import co.edu.uniandes.csw.especialistas.ejb.ConsultorioLogic;
import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.especialistas.persistence.ConsultorioPersistence;
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
import static org.junit.Assert.assertTrue;
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
public class ConsultorioLogicTest {
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConsultorioEntity.class.getPackage())
                .addPackage(ConsultorioPersistence.class.getPackage())
                .addPackage(ConsultorioLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private ConsultorioPersistence persistence;
    
    @Inject
    private ConsultorioLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ConsultorioEntity> data = new ArrayList<>();
    
    public ConsultorioLogicTest() {
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
        em.createQuery("delete from ConsultorioEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            ConsultorioEntity entity = factory.manufacturePojo(ConsultorioEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    private ConsultorioEntity entity = new ConsultorioEntity();

    /**
     * Test of create method, of class ConsultorioPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        ConsultorioEntity entity = factory.manufacturePojo(ConsultorioEntity.class);

        System.out.println(entity.getId());
        logic.createConsultorio(entity);
        
        assertEquals(logic.getConsultorio(entity.getId()), entity);
        assertEquals(logic.getConsultorio(entity.getId()), entity);
    }
    
    /**
     * Test of update method, of class ConsultorioPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        ConsultorioEntity entity = factory.manufacturePojo(ConsultorioEntity.class);

        logic.createConsultorio(entity);
        
        assertEquals(logic.updateConsultorio(entity), entity);
    }
    
    /**
     * Test of findAll method, of class ConsultorioPersistence.
     */
    @Test
    public void testGet() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        ConsultorioEntity entity = factory.manufacturePojo(ConsultorioEntity.class);
        
        logic.createConsultorio(entity);
        assertEquals(logic.getConsultorios().contains(entity), true);
    }
    
    /**
     * Test of delete method, of class ConsultorioPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        ConsultorioEntity entity = factory.manufacturePojo(ConsultorioEntity.class);
        
        logic.createConsultorio(entity);
        logic.deleteConsultorio(entity.getId());
        
        boolean noExiste = false;
        try{
            logic.getConsultorio(entity.getId());
        }catch(BusinessLogicException e){
            noExiste = true;
        }
        assertTrue(noExiste);
    }
    
}
