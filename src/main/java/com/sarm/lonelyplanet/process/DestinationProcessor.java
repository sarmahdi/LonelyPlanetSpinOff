/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarm.lonelyplanet.process;

import com.sarm.lonelyplanet.model.Destination;
import com.sarm.lonelyplanet.model.Taxonomy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.log4j.Logger;

/**
 * This class processes the destinations.xml. It uses StAX to process the xml
 * and un marshals to list of destinations objects. It uses ForkJoinPool to 
 * process for each destination in the list in order to create htmls for each of 
 * the destination.
 * @author sarm
 */
public class DestinationProcessor {
    
     static Logger logger = Logger.getLogger(DestinationProcessor.class);

    private ForkJoinPool forkJoinPool;

    /**
     *
     * This method has two steps. One is to parse the destination xml file
     * and get the destinations in a List Collection and second is to
     * concurrently process for each destination and using the taxonomy generate
     * the html file for each destination.
     *
     * @param taxonomy - It is passed on to the Destination Handler to generate
     * navigation.
     * @param destinationFileName - Used to locate and parse the destination.xml
     * @return
     * @throws XMLStreamException
     * @throws FileNotFoundException
     * @throws JAXBException -
     * @throws UnsupportedEncodingException - Thrown by createXMLStreamReader
     */
    public List<Destination> processDestinationsConcurrently(Taxonomy taxonomy, String destinationFileName, String htmlTargetLocation) throws XMLStreamException, FileNotFoundException, JAXBException, UnsupportedEncodingException {
        List<Destination> destinations = processDestinationByStAX(destinationFileName);
        forkJoinPool = new ForkJoinPool(destinations.size()/2);
        forkJoinPool.invoke(new DestinationHandler(destinations, taxonomy, destinations.size(), htmlTargetLocation));

        return destinations;

    }

    /**
     *
     * StAX is Java API for processing XML Streams The XMLStreamReader interface
     * allows forward, read-only access to XML. It is designed to be the lowest
     * level and most efficient way to read XML data. The Cursor Approach in
     * StAX has been used to get to the next event "START_ELEMENT". when the
     * unmarshaller un marshals the xml element into object, the cursor is
     * pointing to the next event. There is an iterator approach that goes
     * through events instead of Tag, but for larger files the cursor approach
     * is more efficient.
     *
     * @param taxonomy
     * @param destinationFileName
     * @return
     * @throws XMLStreamException
     * @throws FileNotFoundException
     * @throws JAXBException -
     * @throws UnsupportedEncodingException - Thrown by createXMLStreamReader
     */
    public List<Destination> processDestinationByStAX(String destinationFileName) throws JAXBException, XMLStreamException, UnsupportedEncodingException, FileNotFoundException {
        XMLInputFactory xif = XMLInputFactory.newInstance();
        /**
         * The reason of using the FileInputStream was to get the CData in UTF-8
         * Other wise the apostrophes were becoming a39TM characters
         */
        List<Destination> destinations = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream(destinationFileName);) {
            XMLStreamReader xsr = xif.createXMLStreamReader(new InputStreamReader(inputStream, "UTF-8"));
            xsr.nextTag();

            Destination destination = new Destination();

            JAXBContext jc = JAXBContext.newInstance(Destination.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            while (xsr.nextTag() == XMLStreamConstants.START_ELEMENT) {

                destination = (Destination) unmarshaller.unmarshal(xsr);
                destinations.add(destination);

            }
        } catch (Exception ex) {
            logger.debug("Exception occured during processDestinationByStAX ...");
            ex.printStackTrace();

        }

        return destinations;
    }

}
