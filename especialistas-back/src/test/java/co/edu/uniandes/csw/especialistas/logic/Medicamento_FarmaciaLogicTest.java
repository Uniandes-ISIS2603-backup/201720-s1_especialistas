/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.logic;

import co.edu.uniandes.csw.especialistas.ejb.FarmaciaLogic;
import co.edu.uniandes.csw.especialistas.ejb.MedicamentoLogic;
import co.edu.uniandes.csw.especialistas.ejb.Medicamento_FarmaciaLogic;
import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import co.edu.uniandes.csw.especialistas.persistence.FarmaciaPersitence;
import co.edu.uniandes.csw.especialistas.persistence.MedicamentoPersistence;
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
 * @author rc.tejon
 */
@RunWith(Arquillian.class)
public class Medicamento_FarmaciaLogicTest {
    
   @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedicamentoEntity.class.getPackage())
                .addPackage(MedicamentoPersistence.class.getPackage())
                .addPackage(MedicamentoLogic.class.getPackage())
                .addPackage(FarmaciaEntity.class.getPackage())
                .addPackage(FarmaciaPersitence.class.getPackage())
                .addPackage(FarmaciaLogic.class.getPackage())
                .addPackage(Medicamento_FarmaciaLogicTest.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private MedicamentoPersistence persistence;
    
    @Inject
    private Medicamento_FarmaciaLogic logic;
    
    @Inject 
    private FarmaciaLogic farmaciaLogic;
    
    @Inject 
    private MedicamentoLogic medicamentoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<FarmaciaEntity> dataFarmacias = new ArrayList<>();
    private List<MedicamentoEntity> dataMedicamentos = new ArrayList<>();
    
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
        em.createQuery("delete from MedicamentoEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            FarmaciaEntity entity = factory.manufacturePojo(FarmaciaEntity.class);
            
            em.persist(entity);
            dataFarmacias.add(entity);
                 
            MedicamentoEntity entitym = factory.manufacturePojo(MedicamentoEntity.class);
            
            em.persist(entitym);
            dataMedicamentos.add(entitym);      
        }
    }
    
    @After
    public void tearDown() {
    }

    private FarmaciaEntity entityf = new FarmaciaEntity();
    private MedicamentoEntity entitym = new MedicamentoEntity();

    /**
     * Test of agregarRelacion method, of class Medicamento_FarmaciaLogic.
     */
    @Test
    public void testAgregarRelacion() {
        entityf = dataFarmacias.get((int)(Math.random()*dataFarmacias.size()));
        entitym = dataMedicamentos.get((int)(Math.random()*dataMedicamentos.size()));
        assertTrue(logic.agregarRelacion(entityf.getId(), entitym.getId()));
    }

    /**
     * Test of eliminarRelacion method, of class Medicamento_FarmaciaLogic.
     */
    @Test
    public void testEliminarRelacion() throws Exception {
        entityf = dataFarmacias.get((int)(Math.random()*dataFarmacias.size()));
        entitym = dataMedicamentos.get((int)(Math.random()*dataMedicamentos.size()));
        logic.agregarRelacion(entityf.getId(), entitym.getId());
        assertTrue(logic.eliminarRelacion(entityf.getId(), entitym.getId()));
    }
    
}
