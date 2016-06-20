

import java.awt.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class AfinadorVistaTest {
    
    public AfinadorVistaTest() {
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

    /**
     * Test of createView method, of class AfinadorVista.
     */
    @Test
    public void testCreateView() {
        System.out.println("createView");
        int i =1;
        AfinadorModelo modeloTest = new AfinadorModelo();
        AfinadorControlador controladorTest = new AfinadorControlador(modeloTest,i);
        String test = controladorTest.vista1.bpmOutputLabel.getText();
        if( test != "APAGADO ")
        fail("The test case is a prototype.");
    }

  
    
}
