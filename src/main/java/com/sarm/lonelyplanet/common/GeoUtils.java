/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this defaultTemplate file, choose Tools | Templates
 * and open the defaultTemplate in the editor.
 */
package com.sarm.lonelyplanet.common;

import com.sarm.lonelyplanet.model.Destination;
import com.sarm.lonelyplanet.model.Node;
import com.sarm.lonelyplanet.model.Taxonomy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * This is a utility class which a handler class can utilize. It holds different
 * static methods that take one or more arguments and process them. They do not
 * hold any state.
 *
 * @author sarm
 */
public class GeoUtils {

    static Logger logger = Logger.getLogger(GeoUtils.class);

    /**
     * This method is the top level method which segregates the ides of parsing
     * over the Taxonomy and search through the Node in the Taxonomy. It passes
     * on the control to recursiveSearchForNode which is the main method in
     * determining the Node for a certain destination.
     *
     * @param taxonomy
     * @param name
     * @return
     */
    public static Node getNodeByDestinationTitle(Taxonomy taxonomy, String name) {
        List<Node> nodeList = taxonomy.getNodesInTaxonomy();
        Node foundNode = null;
        for (Node node : nodeList) {
            if (null == foundNode) {
                foundNode = recursiveSearchForNode(node, name);
            }

        }
        return foundNode;
    }

    /**
     * This is the main method where the node for a certain destination is
     * searched through the primary node and then recursively through the
     * children in the tree.
     *
     * @param node
     * @param name
     * @return
     */
    public static Node recursiveSearchForNode(Node node, String name) {

        Node foundNode = null;

        if (node.getNodeName().equals(name)) {
            return node;
        }
        if (node.hasChildren()) {
            List<Node> children = node.getChildrenNodes();

            if (name.equals("Africa")) {
                logger.info(" At Node  ->  " + node.getNodeName());
            }

            if (children.size() > 0) {
                for (int i = 0; i < children.size(); i++) {
                    foundNode = recursiveSearchForNode(children.get(i), name);
                    if (foundNode != null) {
                        return foundNode;
                    }
                }
            }

        }

        return foundNode;
    }

    /**
     * Segregation of navigation creation for parent node. It takes the
     * navigation so far created from destination and Children of the
     * Destination node and appends each parent from the tree. As there is only
     * one parent of each node, there is no use of recursion at this point and a
     * simple Do While loop will do.
     *
     * @param node
     * @param navigation
     * @return
     */
    public static StringBuilder populateParents(Node node, StringBuilder navigation) {

        boolean firstParentFound = false;
        Node parentNode = node.getParentNode();
        String link = LonelyConstants.linkStart;
        String parentName;
        String parentLink;
        do {
            StringBuilder parentNavigation = new StringBuilder();

            parentName = parentNode.getNodeName();
            parentLink = replaceSpaces(parentName);
            link = LonelyConstants.linkStart;
            link = link.replace("#", parentLink + ".html");
            parentNavigation = parentNavigation.append(LonelyConstants.liStart).append(link).append(parentName).append(LonelyConstants.linkEnd);

            if (null == parentNode.getParentNode()) {
                firstParentFound = true;
            } else {
                parentNode = parentNode.getParentNode();
            }
            navigation = parentNavigation.append(LonelyConstants.ulStart).append(navigation).append(LonelyConstants.ulEnd).append(LonelyConstants.liEnd);
        } while (!firstParentFound);

        return navigation;
    }

    /**
     * Segregation of navigation creation for children nodes of the destination.
     * For each child it creates and places a link reference to its html file.
     * Using recursion it navigates into the children of any child node that has
     * children itself.
     *
     * @param node
     * @param childNavigation
     * @return
     */
    public static StringBuilder navigateIntoChildren(Node node, StringBuilder childNavigation) {

        childNavigation.append(LonelyConstants.ulStart);
        String link = LonelyConstants.linkStart;
        for (Node child : node.getChildrenNodes()) {
            link = LonelyConstants.linkStart;
            link = link.replace("#", replaceSpaces(child.getNodeName()) + ".html");
            childNavigation.append(LonelyConstants.liStart).append(link).append(child.getNodeName()).append(LonelyConstants.linkEnd).append(LonelyConstants.liEnd);
            if (child.hasChildren()) {
                childNavigation = navigateIntoChildren(child, childNavigation);
            }
        }
        childNavigation.append(LonelyConstants.ulEnd);
        return childNavigation;
    }

    /**
     * This is a utility method to create a file name from a title in
     * Destination or node name in Taxonomy with underscores instead of the
     * spaces.
     *
     * @param title
     * @return
     */
    public static String replaceSpaces(String title) {
        return title.replaceAll(" ", "_").toLowerCase();

    }

    /**
     * This method parses a destination and creates an HTML file for that
     * destination. Steps involved in creating an HTML file for a destination
     *
     * 1 - Initialize the HTML Template - This defaultTemplate is a generic
     * structure of the required html file. It has place holders for the
     * different portions of texts. 2 - Create a navigation string with links
     * referencing to parents and all children under the destination 3 - Fill
     * the place holders with he appropriate texts and heading tags. 4 - Create
     * an HTML file using the destination title and converting spaces into
     * underscores and make the name of the file as in lowercase.
     *
     * @param destination
     * @param node
     * @param targetLocation
     * @throws IOException
     */
    public static void createHtmlFromTemplate(Destination destination, Node node, String targetLocation) throws IOException, NullPointerException {
        StringBuilder breaker = new StringBuilder(LonelyConstants.BREAKER);
        File htmlTemplateFile = null;
        String htmlString;
        try {
           
            Reader reader = null;
            InputStream is = GeoUtils.class.getResourceAsStream("/" + LonelyConstants.defaultTemplate);
            if (null != is) {
                reader = new InputStreamReader(is);
            }
            BufferedReader bufRead = new BufferedReader(reader);
            StringBuilder htmlFileString = new StringBuilder();
            StringBuilder htmlFilefrString = new StringBuilder();
            int nextchar;
            while ((nextchar = bufRead.read()) != -1) {
                htmlFileString.append((char) nextchar);
            }

            htmlString = new String(htmlFileString);
//            logger.debug("found template on Path on location" + LonelyConstants.defaultTemplate);

        } catch (FileNotFoundException ex) {
            logger.debug("Could not find template on Path, Getting Static HTML template" + htmlTemplateFile.getAbsolutePath());
            htmlString = LonelyConstants.destinationTemplateHtml;
        }

        String navigation = createNavigation(destination, node);
        htmlString = htmlString.replace("$navigation", navigation);

        String title = destination.getTitle();
        htmlString = htmlString.replace("$title", title);
        htmlString = htmlString.replace("$destinationName", destination.getTitle());

        String overview = (null != destination.getIntroduction() ? destination.getIntroduction() : new String(breaker));
        htmlString = htmlString.replace("$introduction", overview);

        StringBuilder sb = new StringBuilder();
        if (null != destination.getHistoryOverview()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "historyOverview", "Overview");

            sb = sb.append(destination.getHistoryOverview());
            htmlString = htmlString.replace("$historyOverview", new String(sb));

        } else {
            htmlString = htmlString.replace("$historyOverview", breaker);

        }
        if (null != destination.getHistories() && destination.getHistories().size() != 0) {

            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "history", " Background History");
            sb = formatList(destination.getHistories(), sb);
            htmlString = htmlString.replace("$histories", new String(sb));

        } else {

            htmlString = htmlString.replace("$histories", breaker);
        }

        if (null != destination.getBeforeYouGo()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "beforeYouGo", "Before You Go");
            sb = formatList(destination.getBeforeYouGo(), sb);
            htmlString = htmlString.replace("$beforeYouGo", new String(sb));
        } else {
            htmlString = htmlString.replace("$beforeYouGo", breaker);
        }

        if (null != destination.getDangersAndAnnoyances()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "dangersAndAnnoyances", "Dangers And Annoyances");
            sb = formatList(destination.getDangersAndAnnoyances(), sb);
            htmlString = htmlString.replace("$dangersAndAnnoyances", new String(sb));
        } else {
            htmlString = htmlString.replace("$dangersAndAnnoyances", breaker);

        }

        if (null != destination.getWhileYouAreThere()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "whileyouarethere", "While You Are There");

            sb = formatList(destination.getWhileYouAreThere(), sb);
            htmlString = htmlString.replace("$whileYourArethere", new String(sb));
        } else {
            htmlString = htmlString.replace("$whileYourArethere", breaker);

        }

        if (null != destination.getCosts()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "costs", "Costs");

            sb = formatList(destination.getCosts(), sb);
            htmlString = htmlString.replace("$costs", new String(sb));
        } else {
            htmlString = htmlString.replace("$costs", breaker);

        }
        if (null != destination.getGettingAroundAir()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "gettingAroundAir", "Air");
            sb = formatList(destination.getGettingAroundAir(), sb);
            htmlString = htmlString.replace("$gettingAroundAir", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingAroundAir", breaker);

        }

        if (null != destination.getGettingAroundBicycle()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "gettingAroundBicycle", "Bicycle");

            sb = formatList(destination.getGettingAroundBicycle(), sb);
            htmlString = htmlString.replace("$gettingAroundBicycle", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingAroundBicycle", breaker);

        }

        if (null != destination.getGettingAroundBoat()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "gettingAroundBoat", "Boat");

            sb = formatList(destination.getGettingAroundBoat(), sb);
            htmlString = htmlString.replace("$gettingAroundBoat", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingAroundBoat", breaker);

        }

        if (null != destination.getGettingAroundBusnTram()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "gettingAroundBusnTram", "Bus And Tram");

            sb = formatList(destination.getGettingAroundBusnTram(), sb);
            htmlString = htmlString.replace("$gettingAroundBusnTram", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingAroundBusnTram", breaker);

        }

        if (null != destination.getGettingAroundCarAndMotorCycle()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "gettingAroundCarAndMotorCycle", "Car And MotorCycle");

            sb = formatList(destination.getGettingAroundCarAndMotorCycle(), sb);
            htmlString = htmlString.replace("$gettingAroundCarAndMotorCycle", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingAroundCarAndMotorCycle", breaker);

        }

        if (null != destination.getGettingAroundHitching()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "gettingAroundHitching", "Hitching");

            sb = formatList(destination.getGettingAroundHitching(), sb);
            htmlString = htmlString.replace("$gettingAroundHitching", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingAroundHitching", breaker);

        }
        if (null != destination.getGettingAroundLocal()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getGettingAroundLocal", "Local Transport");

            sb = formatList(destination.getGettingAroundLocal(), sb);
            htmlString = htmlString.replace("$gettingAroundLocal", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingAroundLocal", breaker);

        }
        if (null != destination.getGettingAroundTrain()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getGettingAroundTrain", "Train");

            sb = formatList(destination.getGettingAroundTrain(), sb);
            htmlString = htmlString.replace("$gettingAroundTrain", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingAroundTrain", breaker);

        }

        if (null != destination.getGettingAroundOverview()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getGettingAroundOverview", "Overview");

            sb = sb.append(destination.getGettingAroundOverview());
            htmlString = htmlString.replace("$gettingAroundOverview", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingAroundOverview", breaker);

        }

        if (null != destination.getGettingThereAndAwayOverview()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getGettingThereAndAwayOverview", "Overview");

            sb = sb.append(destination.getGettingThereAndAwayOverview());
            htmlString = htmlString.replace("$gettingThereAndAwayOverview", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingThereAndAwayOverview", breaker);
        }

        if (null != destination.getGettingThereAir()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getGettingThereAir", "Air");

            sb = formatList(destination.getGettingThereAir(), sb);
            htmlString = htmlString.replace("$gettingThereAir", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingThereAir", breaker);

        }

        if (null != destination.getGettingThereBusnTram()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getGettingThereBusnTram", "Bus and Tram");

            sb = formatList(destination.getGettingThereBusnTram(), sb);
            htmlString = htmlString.replace("$gettingThereBusnTram", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingThereBusnTram", breaker);

        }

        if (null != destination.getGettingThereBicycle()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getGettingThereBicycle", "Bicycle");

            sb = formatList(destination.getGettingThereBicycle(), sb);
            htmlString = htmlString.replace("$gettingThereBicycle", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingThereBicycle", breaker);

        }
        if (null != destination.getGettingThereBoat()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getGettingThereBoat", "Boat");

            sb = formatList(destination.getGettingThereBoat(), sb);
            htmlString = htmlString.replace("$gettingThereBoat", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingThereBoat", breaker);

        }
        if (null != destination.getGettingThereCarAndMotorCycle()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getGettingThereCarAndMotorCycle", "Car and MotorCycle");

            sb = formatList(destination.getGettingThereCarAndMotorCycle(), sb);
            htmlString = htmlString.replace("$gettingThereCarAndMotorCycle", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingThereCarAndMotorCycle", breaker);

        }
        if (null != destination.getGettingThereLocal()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getGettingThereLocal", "Local Transport");

            sb = formatList(destination.getGettingThereLocal(), sb);
            htmlString = htmlString.replace("$gettingThereLocal", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingThereLocal", breaker);

        }

        if (null != destination.getGettingThereTrain()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getGettingThereTrain", "Train");

            sb = formatList(destination.getGettingThereTrain(), sb);
            htmlString = htmlString.replace("$gettingThereTrain", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingThereTrain", breaker);

        }
        if (null != destination.getGettingThereHitching()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getGettingThereHitching", "Hitching");

            sb = formatList(destination.getGettingThereHitching(), sb);
            htmlString = htmlString.replace("$gettingThereHitching", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingThereHitching", breaker);

        }
        if (null != destination.getInTransit()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getInTransit", "In Transit");

            sb = formatList(destination.getInTransit(), sb);
            htmlString = htmlString.replace("$inTransit", new String(sb));
        } else {
            htmlString = htmlString.replace("$inTransit", breaker);

        }
        if (null != destination.getMoney()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "money", "Money");

            sb = formatList(destination.getMoney(), sb);
            htmlString = htmlString.replace("$money", new String(sb));
        } else {
            htmlString = htmlString.replace("$money", breaker);

        }
        if (null != destination.getVisasOther()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "visasOther", "Visa");

            sb = formatList(destination.getVisasOther(), sb);
            htmlString = htmlString.replace("$visasOther", new String(sb));
        } else {
            htmlString = htmlString.replace("$visasOther", breaker);

        }
        if (null != destination.getPermits()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "permits", "Permits");

            sb = formatList(destination.getPermits(), sb);
            htmlString = htmlString.replace("$permits", new String(sb));
        } else {
            htmlString = htmlString.replace("$permits", breaker);

        }

        if (null != destination.getVisasOverview()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "VisasOverview", "Overview");

            sb = formatList(destination.getVisasOverview(), sb);
            htmlString = htmlString.replace("$visasOverview", new String(sb));
        } else {
            htmlString = htmlString.replace("$visasOverview", breaker);

        }

        if (null != destination.getWhenToGoClimate()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getWhenToGoClimate", "Climate");

            sb = formatList(destination.getWhenToGoClimate(), sb);
            htmlString = htmlString.replace("$whenToGoClimate", new String(sb));
        } else {
            htmlString = htmlString.replace("$whenToGoClimate", breaker);

        }
        if (null != destination.getWhenToGoOverview()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getWhenToGoOverview", "Overview");

            sb = formatList(destination.getWhenToGoOverview(), sb);
            htmlString = htmlString.replace("$whenToGoOverview", new String(sb));
        } else {
            htmlString = htmlString.replace("$whenToGoOverview", breaker);

        }
        if (null != destination.getWhileYouAreThere()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getWhileYouAreThere", "While You Are There");

            sb = formatList(destination.getWhileYouAreThere(), sb);
            htmlString = htmlString.replace("$whileYouAreThere", new String(sb));
        } else {
            htmlString = htmlString.replace("$whileYouAreThere", breaker);

        }
        if (null != destination.getWorkBusiness()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getWorkBusiness", "Business");

            sb = formatList(destination.getWorkBusiness(), sb);
            htmlString = htmlString.replace("$workBusiness", new String(sb));
        } else {
            htmlString = htmlString.replace("$workBusiness", breaker);

        }
        if (null != destination.getWorkOverview()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getWorkOverview", "Overview");

            sb = formatList(destination.getWorkOverview(), sb);
            htmlString = htmlString.replace("$workOverview", new String(sb));
        } else {
            htmlString = htmlString.replace("$workOverview", breaker);

        }
        if (null != destination.getGettingAroundHitching()) {
            sb = getStringBuilderwithHeader(LonelyConstants.HEADER_TYPE_3, "getGettingAroundHitching", "Hitching");

            sb = formatList(destination.getGettingAroundHitching(), sb);
            htmlString = htmlString.replace("$gettingAroundHitching", new String(sb));
        } else {
            htmlString = htmlString.replace("$gettingAroundHitching", breaker);

        }

        String fileName = GeoUtils.replaceSpaces(destination.getTitle()) + ".html";
//        logger.info(" fileName of html file to be created  :  " + fileName);
        File newHtmlFile = new File(targetLocation + fileName);
        FileUtils.writeStringToFile(newHtmlFile, htmlString, "UTF-8");
    }

    /**
     * Concatenating each CData of a certain type into a body of paragraphs '
     * appended to a StringBuilder object. This String Builder will be the body
     * of the text for the specific place holder
     *
     * @param listOfTexts
     * @return
     */
    public static StringBuilder formatList(List<String> listOfTexts, StringBuilder sb) {
        if (listOfTexts.size() > 0) {
//            histories = histories.append(LonelyConstants.pStart + "History " + "" + LonelyConstants.pEnd);
            for (String text : listOfTexts) {
                sb = sb.append(LonelyConstants.pStart).append(text).append(LonelyConstants.pEnd);

            }
        } else {
            return new StringBuilder("");
        }
        return sb;
    }

    /**
     * Creates an Html Navigation links for the destination. The navigation
     * contains links to the parents as well as the children of the destination
     * and their children.
     *
     * @param destination
     * @param node
     * @return
     * @throws NullPointerException
     */
    public static String createNavigation(Destination destination, Node node) throws NullPointerException {

        String link = LonelyConstants.linkStart;
        try {
            link = link.replace("#", GeoUtils.replaceSpaces(node.getNodeName()) + ".html");
        } catch (NullPointerException npe) {
            logger.debug(" destination.getTitle()" + destination.getTitle());
            logger.debug("node.getNodeName()" + node.getNodeName());

            throw npe;
        }
        StringBuilder navigation = new StringBuilder();
        navigation = navigation.append(LonelyConstants.liStart).append(link).append(node.getNodeName()).append(LonelyConstants.linkEnd);
        if (null != node.getChildrenNodes()) {
            navigation = GeoUtils.navigateIntoChildren(node, navigation);
            navigation.append(LonelyConstants.linkEnd);
        }
        Node parentNode = node.getParentNode();
        if (null != parentNode) {
            navigation = GeoUtils.populateParents(node, navigation);
        }
        return navigation.toString();

    }

    /**
     * Made a separate method in order not to clutter the code with div tag or
     * H2 or H3 tag creation. The repetitive nature of the operation demanded
     * that it be segregated into its own method.
     *
     * @param headerType
     * @param divName
     * @param title
     * @return
     */
    public static StringBuilder getStringBuilderwithHeader(String headerType, String divName, String title) {
        StringBuilder sb = new StringBuilder();
        String divHeader = new String();
        if (headerType.equals(LonelyConstants.HEADER_TYPE_2)) {

            divHeader = LonelyConstants.divStart2;
            divHeader = divHeader.replace("$divName", divName);
            divHeader = divHeader.replace("$titleHeading", title);

        } else if (headerType.equals(LonelyConstants.HEADER_TYPE_3)) {
            divHeader = LonelyConstants.header3;
            divHeader = divHeader.replace("$titleHeading", title);
        }

        sb = sb.append(divHeader);

        return sb;
    }

    /**
     * This method copies the all.css file to the target location given where
     * the html files are needed to be created.
     *
     * @param newCSSTargetLocation
     * @param cssFileLocation
     */
    public static void copyCSS(String newCSSTargetLocation, String cssFileLocation) {
        File source = null;
        String line = "";
        String cssString = null;
        source = new File(cssFileLocation);

        File dest = new File(newCSSTargetLocation + "/static/all.css");
    
        InputStream input;
        input = GeoUtils.class.getClassLoader().getResourceAsStream(cssFileLocation);

        StringBuilder sb = new StringBuilder("");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input));) {
            line = "";

            if (null == reader) {
                logger.debug("reader  is null");
            }

            while ((line = reader.readLine()) != null) {
                sb = sb.append(line);
                sb = sb.append("\n");
            }

            cssString = new String(sb);

        } catch (IOException | NullPointerException ex) {
            logger.error("Exception in Buffered reader writer ....");
            ex.printStackTrace();

        }

        if (null == dest) {
            logger.debug("destination file is a null file Cannot proceed to create all.css in " + newCSSTargetLocation);
        } else {
            try {
                FileUtils.writeStringToFile(dest, cssString, "UTF-8");
              } catch (IOException ex) {
                logger.error("Exception occured when writing to  " + dest.getAbsolutePath());
                ex.printStackTrace();
            }
        }

    }
}
