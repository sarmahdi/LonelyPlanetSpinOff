/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sarm.lonelyplanet.process;

import com.sarm.lonelyplanet.common.LonelyConstants;
import com.sarm.lonelyplanet.model.Destination;
import com.sarm.lonelyplanet.model.Destinations;
import com.sarm.lonelyplanet.model.Taxonomies;
import com.sarm.lonelyplanet.model.Taxonomy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sarm
 */
public class DestinationProcessorTest {

    static Logger logger = Logger.getLogger(DestinationProcessorTest.class);
    private static String taxonomyFileName;
    private static String targetLocation;
    private static String destinationFileName;
    private static Taxonomies sampleTesttaxonomies;
 
    private static Integer numOfDestinations;
    private static String destinationsConcurrantlyExpresult;
    private static String regressDestinationfile;
    private static String indexOfdestInConcurrent;

    public DestinationProcessorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        logger.info("DestinationProcessorTest  Commencing loading test properties ...");
        Properties prop = new Properties();

        String propFileName = LonelyConstants.testPropertyFile;

        try (InputStream input = new FileInputStream(propFileName)) {

            if (input == null) {
                logger.debug("input Stream for test.properties file : is Null  ");
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            logger.debug("Loading properties file   " + propFileName);

            prop.load(input);
        } catch (FileNotFoundException ex) {
            logger.debug("FileNotFoundException  " + propFileName);
            ex.printStackTrace();
        } catch (IOException ex) {
            logger.debug("IOException  " + propFileName);
            ex.printStackTrace();
        }

        taxonomyFileName = prop.getProperty(LonelyConstants.propertyTaxonomy);
        targetLocation = prop.getProperty(LonelyConstants.propertyHtmlTarget);
        destinationFileName = prop.getProperty(LonelyConstants.propertyDestination);
        destinationsConcurrantlyExpresult = prop.getProperty(LonelyConstants.destinationsConcurrantlyExpresult);
        numOfDestinations = Integer.valueOf(prop.getProperty(LonelyConstants.numOfDestinations));
        regressDestinationfile = prop.getProperty(LonelyConstants.regressDestinationfile);
        indexOfdestInConcurrent = prop.getProperty(LonelyConstants.indexOfdestInConcurrent);
        try {
            sampleTesttaxonomies = TaxonomyProcessor.processTaxonomy(taxonomyFileName);
        } catch (FileNotFoundException ex) {
            logger.debug("FileNotFoundException  on file  : " + taxonomyFileName);
            ex.printStackTrace();
        } catch (JAXBException ex) {
            logger.debug("JAXBException  : ");
            ex.printStackTrace();
        }

        try {
            testMarshall(".//destinations.xml");
        } catch (JAXBException | FileNotFoundException | XMLStreamException | UnsupportedEncodingException ex) {
            ex.printStackTrace();

        }

    }

    /**
     * Test of processDestinationsConcurrently method, of class LPUnMarshaller.
     */
    @Test
    public void testProcessDestinationsConcurrently() throws Exception {
        logger.info(" Testing processDestinationsConcurrently");
        Taxonomy taxonomy = sampleTesttaxonomies.getTaxonomy();
        DestinationProcessor destinationProcessor = new DestinationProcessor();
        String expResult = destinationsConcurrantlyExpresult;
        List<Destination> result = destinationProcessor.processDestinationsConcurrently(taxonomy, destinationFileName, targetLocation);
        assertNotNull(result);
        int index =  Integer.valueOf(indexOfdestInConcurrent);
        assertEquals(expResult, result.get(index).getTitle());
    }



    /**
     * Test of processDestinationByStAX method, of class LPUnMarshaller. The
     * destinationFile is the normal destinations.xml with 24 destinations
     */
    @Test
    public void testProcessDestinationByStAX() throws Exception {
        logger.info("Testing processDestinationByStAX");
//         destinationFileName = ".//destinations.xml";
        DestinationProcessor destinationProcessor = new DestinationProcessor();
        int expResult = numOfDestinations;
        logger.info("parsing  " + destinationFileName);
        List<Destination> result = destinationProcessor.processDestinationByStAX(destinationFileName);
        logger.info("Successfully Parsed  : " + result.size() + " destinations ...");
        assertEquals(expResult, result.size());
    }

    /**
     * Test of processDestinationByStAX method, of class LPUnMarshaller. The
     * destinationFile is a test file with approximately 30000 destination This
     * file is created by the normal destinations file and added them over again
     * to make up 30000 destinations. 
     */
    @Test
    public void testProcessDestinationByStAXfor30000() throws Exception {
        logger.info("Testing processDestinationByStAX30000");

        String destinationFileName = regressDestinationfile;
        DestinationProcessor destinationProcessor = new DestinationProcessor();
        int expResult = 36000;
        List<Destination> result = destinationProcessor.processDestinationByStAX(regressDestinationfile);

        assertEquals(expResult, result.size());
        logger.info("Successfully Parsed  : " + result.size() + " destinations ...");
    }

    /**
     * This method tests the JAXB provider. The validates if the JAXB provider
     * is eclipse provider MOXy. In essence it tests that the jaxb.properties
     * file is present and it has the correct JAXBContextFactory configured.
     */
    @Test
    public void testJAXBContext() {
        logger.info("Testing testJAXBContext ");
        JAXBContext jc = null;
        try {

            jc = JAXBContext.newInstance(Destinations.class);
        } catch (JAXBException ex) {
     logger.error(ex);
        ex.printStackTrace();
        }
        String context = "class org.eclipse.persistence.jaxb.JAXBContext";
        assertEquals(context, jc.getClass().toString());

    }

    /**
     * This method is not a JUnit test method. This is used to create an XML
     * file of 30000 destinations and to regressively test the UnMarshalling
     * technique used in the LPUnMarshaller. This is called by the test method :
     * testProcessDestinationByStAXfor30000 to test the 30000 destinations.
     *
     * @param destinationFileName
     * @throws JAXBException
     * @throws FileNotFoundException
     * @throws XMLStreamException
     * @throws UnsupportedEncodingException
     */
    public static void testMarshall(String destinationFileName) throws JAXBException, FileNotFoundException, XMLStreamException, UnsupportedEncodingException {
        DestinationProcessor destinationProcessor = new DestinationProcessor();
        List<Destination> destinations = destinationProcessor.processDestinationByStAX(destinationFileName);
        List<Destination> tempDestinations = new ArrayList<>();
        for (int i = 0; i < 1500; i++) {
            for (Destination destination : destinations) {
                Destination temp = new Destination();
                temp = destination.clone();
                temp.setTitle(temp.getTitle() + i);
                tempDestinations.add(temp);
            }
        }
        Destinations destinationList = new Destinations();
        destinationList.setDestinations(tempDestinations);
        logger.info(" destinationList " + destinationList.getDestinations().size());

        JAXBContext jc = JAXBContext.newInstance(Destinations.class);
        logger.debug(jc.getClass());
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(destinationList, new File(regressDestinationfile));
        logger.info("Successfully created regressDestinationfile " + regressDestinationfile);
        
        

    }

    @AfterClass
    public static void tearDownClass() {
       
            logger.info("Tearing down DestinationProcessorTest and deleting the 30000dests.xml file and "+targetLocation+"  folder");
            File file = new File(regressDestinationfile);
            file.delete();
          
            File file2 = new File(targetLocation);
            FileUtils.deleteQuietly(file2);
    }

}
