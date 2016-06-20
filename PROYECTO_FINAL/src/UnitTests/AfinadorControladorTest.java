

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class AfinadorControladorTest {
    
    public AfinadorControladorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testIncreaseBPM() {
        System.out.println("increaseBPM");
        AfinadorModelo modeloTest = new AfinadorModelo();
        AfinadorControlador controladorTest = new AfinadorControlador(modeloTest);
        controladorTest.setBPM(1);
        controladorTest.increaseBPM();
        if (modeloTest.getBPM() !=2)
        fail("Error en test de metodo IncreaseBPM. ");
    }

    /**
     * Test of decreaseBPM method, of class AfinadorControlador.
     */
    @Test
    public void testDecreaseBPM() {
        System.out.println("decreaseBPM");
        AfinadorModelo modeloTest = new AfinadorModelo();
        AfinadorControlador controladorTest = new AfinadorControlador(modeloTest);
        controladorTest.setBPM(0);
        controladorTest.decreaseBPM();
        if (modeloTest.getBPM() !=6)
        fail("Error en test de metodo DecreaseBPM.");
    }

   
    
}
