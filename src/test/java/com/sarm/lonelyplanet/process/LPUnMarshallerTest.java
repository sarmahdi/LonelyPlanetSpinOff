/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sarm.lonelyplanet.process;

import com.sarm.lonelyplanet.common.LonelyConstants;
import static com.sarm.lonelyplanet.process.DestinationProcessorTest.logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author sarm
 */
public class LPUnMarshallerTest {

    static Logger logger = Logger.getLogger(LPUnMarshallerTest.class);

    private static LPUnMarshaller lp;
    private static String taxonomyFileName;
    private static String destinationFileName;
    private static String targetLocation;

    public LPUnMarshallerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
      

        Properties prop = new Properties();
        logger.info("LPUnMarshallerTest : Commencing loading test properties ...");
        String propFileName = LonelyConstants.testPropertyFile;

        try (InputStream input = new FileInputStream(propFileName)) {

            if (input == null) {
                logger.debug("input Stream for test.properties file : is Null  ");
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            prop.load(input);

        } catch (FileNotFoundException ex) {
            logger.debug("FileNotFoundException ");
            ex.printStackTrace();
        } catch (IOException ex) {
            logger.debug(" IOException");
            ex.printStackTrace();
        }
        taxonomyFileName = prop.getProperty(LonelyConstants.propertyTaxonomy);
        targetLocation = prop.getProperty(LonelyConstants.propertyHtmlTarget);
        destinationFileName = prop.getProperty(LonelyConstants.propertyDestination);
          lp = new LPUnMarshaller(taxonomyFileName,destinationFileName,targetLocation,null);

    }

    /**
     * Deleting the TestFolder where the output from the Test cases were created
     */
    @AfterClass
    public static void tearDownClass() {
        logger.info("Tearing down LPUnMarshallerTest and deleting  " + targetLocation + "  folder");

        File file2 = new File(targetLocation);
        FileUtils.deleteQuietly(file2);
    }

    /**
     * Test of startProcess method, of class LPUnMarshaller.
     */
    @Test
    public void testStartProcess() {
        logger.info(" Testing LPUnmarshaller startProcess");
        LPUnMarshaller instance = lp;
        boolean expResult = true;
        boolean result = instance.startProcess();
        assertEquals(expResult, result);
    }

}
