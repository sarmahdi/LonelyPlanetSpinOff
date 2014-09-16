/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sarm.lonelyplanet.process;

import com.sarm.lonelyplanet.common.LonelyConstants;
import com.sarm.lonelyplanet.model.Node;
import com.sarm.lonelyplanet.model.Taxonomies;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sarm
 */
public class TaxonomyProcessorTest {

    static Logger logger = Logger.getLogger(TaxonomyProcessorTest.class);
    private static String taxonomyFileName;
    private static Taxonomies taxonomies;
    private static String expectedTaxonomyChildName;
    private static String expectedTaxonomyParentofChild;
    private static int indexOfNodesInTaxonomyForPH;
    private static int indexOfChildrenInParentalH;
    private static int indexOfChildNodes;
    private static int indexOfNodeInTaxonomy;

    public TaxonomyProcessorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Properties prop = new Properties();
        logger.info("Taxonomy Processor Test : Commencing loading test properties ...");
        String propFileName = LonelyConstants.testPropertyFile;

        try (InputStream input = new FileInputStream(propFileName)) {

            if (input == null) {
                logger.debug("input Stream for test.properties file : is Null  ");
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            prop.load(input);

        } catch (FileNotFoundException ex) {
            logger.debug("FileNotFoundException ....");
            ex.printStackTrace();
        } catch (IOException ex) {
            logger.debug("IOException....");
            ex.printStackTrace();
        }
        taxonomyFileName = prop.getProperty(LonelyConstants.propertyTaxonomy);

        expectedTaxonomyChildName = prop.getProperty(LonelyConstants.expectedTaxonomyChildName);
        expectedTaxonomyParentofChild = prop.getProperty(LonelyConstants.expectedTaxonomyParentofChild);
        indexOfChildrenInParentalH = Integer.valueOf(prop.getProperty(LonelyConstants.indexOfChildrenInParentalH));
        indexOfChildNodes = Integer.valueOf(prop.getProperty(LonelyConstants.indexOfChildNodes));
        indexOfNodeInTaxonomy = Integer.valueOf(prop.getProperty(LonelyConstants.indexOfNodeInTaxonomy));
        indexOfNodesInTaxonomyForPH = Integer.valueOf(prop.getProperty(LonelyConstants.indexOfNodesInTaxonomyForPH));

        try {
            taxonomies = TaxonomyProcessor.processTaxonomy(taxonomyFileName);
        } catch (FileNotFoundException ex) {
            logger.debug("FileNotFoundException  on file  : " + taxonomyFileName);
            ex.printStackTrace();
        } catch (JAXBException ex) {
            logger.debug("JAXBException  : ");
            ex.printStackTrace();
        }

    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of processTaxonomy method, of class TaxonomyProcessor.
     */
    @Test
    public void testProcessTaxonomy() {
        logger.info("processTaxonomy");

        String expResult = expectedTaxonomyChildName;
        Taxonomies result = new Taxonomies();
        
            result = taxonomies;
        
        Node node = result.getTaxonomy().getNodesInTaxonomy().get(indexOfNodeInTaxonomy).getChildrenNodes().get(indexOfChildNodes);

        assertEquals(expResult, node.getNodeName());
    }

    @Test
    public void testSetParentalHierarchy() {
        logger.info("setParentalHierarchy");

        String expResult = expectedTaxonomyParentofChild;
        Taxonomies result = new Taxonomies();
        result = taxonomies;
        Node node = result.getTaxonomy().getNodesInTaxonomy().get(indexOfNodesInTaxonomyForPH).getChildrenNodes().get(indexOfChildrenInParentalH);

        assertEquals(expResult, node.getParentNode().getNodeName());
    }

}
