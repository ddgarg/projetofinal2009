package br.com.caelum.aeris;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class) // JUnit batch runner
public class JUnitSeamTestSuiteRunner {
    private static JUnitSeamTest seamTest = new JUnitSeamTest();
 
    @BeforeClass
    public static  void setUpBeforeClass() throws Exception {    
        // set the flag to signal batch mode
        seamTest.startContainer();
    }
 
    @AfterClass
    public static void tearDownAfterClass() throws Exception {        
        seamTest.stopContainer();
    }
}
