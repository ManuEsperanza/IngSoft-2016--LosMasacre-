/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author manuel
 */
public class HeartModelTest {
    
    public HeartModelTest() {
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
     * Test of getInstance method, of class HeartModel.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
      
        HeartModel result = HeartModel.getInstance();
        HeartModel result2 = HeartModel.getInstance();
        if (result2.contador == 1)
        fail("Error en test de metodo getInstance");
    }

    
    
}
