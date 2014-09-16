/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this defaultTemplate file, choose Tools | Templates
 * and open the defaultTemplate in the editor.
 */
package com.sarm.lonelyplanet.process;

import static com.sarm.lonelyplanet.process.LPUnMarshaller.logger;
import com.sarm.lonelyplanet.common.LonelyConstants;
import com.sarm.lonelyplanet.model.Node;
import com.sarm.lonelyplanet.model.Taxonomies;
import com.sarm.lonelyplanet.model.Taxonomy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author sarm
 */
public class TaxonomyProcessor {

    public static Taxonomies processTaxonomy(String taxonomyFileName) throws FileNotFoundException, JAXBException {
        JAXBContext jaxbcontext = JAXBContext.newInstance(Taxonomies.class);
        Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();

        Taxonomies taxonomies = new Taxonomies();
//        logger.info("Relativepath " + new File(".").getAbsolutePath());
        /**
         * if the passed in file name is empty then defaulting to packaged file.
         */
        if (null == taxonomyFileName || taxonomyFileName.isEmpty() || taxonomyFileName.equals("")) {
            logger.debug("Passed in taxonomyFileName was not valid, defaulting to local file ..");
            taxonomyFileName = LonelyConstants.defaultTaxonomy;
        }
        try (FileReader input = new FileReader(new File(taxonomyFileName));) {
            if (null != input) {
                taxonomies = (Taxonomies) unmarshaller.unmarshal(input);
            } else {
                logger.error("Input Stream  input is NULL   ... ");
            }
        } catch (IOException ioex) {
            logger.fatal("IOException  caught ...");
            ioex.printStackTrace();
        } finally {
//    reader.close();  close should handled by Try With resources block
        }
        taxonomies = setParentalHierarchy(taxonomies);
        return taxonomies;

    }

    public static Taxonomies setParentalHierarchy(Taxonomies taxonomies) {

        List<Node> nodes = taxonomies.getTaxonomy().getNodesInTaxonomy();
        nodes = setParentalHierarchy(nodes);

        return taxonomies;
    }

    public static List<Node> setParentalHierarchy(List<Node> nodes) {
        List<Node> populatedNodes = new ArrayList<>();
        for (Node node : nodes) {
            node = setParentalHierarchy(node);
            populatedNodes.add(node);
        }
        return populatedNodes;
    }

    /**
     *
     * @param node
     * @return
     */
    public static Node setParentalHierarchy(Node node) {

        if (node.hasChildren()) {
            for (Node child : node.getChildrenNodes()) {
                child.setParentNode(node);
                if (child.hasChildren()) {
                    child = setParentalHierarchy(child);
                }
            }

        } else {
            logger.info("No childnode present in " + node.getNodeName());
        }

        return node;
    }

}
