/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this defaultTemplate file, choose Tools | Templates
 * and open the defaultTemplate in the editor.
 */
package com.sarm.lonelyplanet.common;

/**
 * This class is for defining constants. 
 * Most are html static tags with or without place holders for different runtime
 * generated html tags .In order to make the navigation links 
 * these HTML tags are used to
 * create the links dynamically.
 *
 * @author sarm
 */
public final class LonelyConstants {

    public static String pStart = "<p>";
    public static String pEnd = "</p>";
    public static String liStart = "<li>";
    public static String liEnd = "</li>";
    public static String h3Start = "<h3>";
    public static String h3End = "</h3>";
    public static String linkStart = "<a href=\"#\">";
    public static String linkEnd = "</a>";
    public static String ulStart = "<ul>";
    public static String ulEnd = "</ul>";
    /**
     * This describes a set of header with a div start wit the Javascript 
     * function embedded that will fold and unfold the text. 
     */
    public static String divStart2 = "<h2 onclick=\"ExpandCollapse('$divName');\">\n"
            + "                                $titleHeading\n"
            + "                                </h2>\n" +
"\n" +
"                                <div id=\"$divName\"  >)";
    
    /**
     * Another variant of the header tag with div but with H3 type Header.
     */
    public static String divStart3 = "<h3 onclick=\"ExpandCollapse('$divName');\">\n"
            + "                                $titleHeading\n"
            + "                                </h3>\n" +
"\n" +
"                                <div id=\"$divName\"  >)";
    
    /**
     * Simple variant of H3 Tag that doesnt have the cold fold unfold Javascript
     * function.
     */
    public static String header3 = "<h3>\n" +
"                                        $titleHeading\n" +
"                                        </h3>";
    
    
    public static String HEADER_TYPE_2 ="H2";
    public static String HEADER_TYPE_3 = "H3";
    public static String BREAKER = "</br>";
    public static String appPropertyFile = "taxonomy.properties";  //  ".//taxonomy.properties";
    public static String testPropertyFile = ".//test.properties";
   public static String propertyTaxonomy =  "taxonomy";
   public static String propertyDestination =  "destinations";
   public static String defaultTemplate =  "destinationTemplate.html";
    public static String defaultTaxonomy =  "../taxonomy.xml"; 
      public static String propertyHtmlTarget =  "htmlTarget";
    public static String defaultDestination = "../destinations.xml";
    public static String defaultTargetLocation = ".//src//main//webapp//html//";
    
    /**
     * Test criterias defined in test.properties
     */
    public static String destinationsConcurrantlyExpresult = "destinationsConcurrantlyExpresult";
    public static String numOfDestinations = "numOfDestinations";
     static String parentLink="parentLink";//"<li><a href=\"sudan.html\">Sudan</a><ul><li><a href=\"eastern_sudan.html\">Eastern Sudan</a><ul></ul></li></ul></li>";
    public static String childrenLink ="childrenLink"; //"<ul><li><a href=\"eastern_sudan.html\">Eastern Sudan</a></li><ul><li><a href=\"port_sudan.html\">Port Sudan</a></li></ul><li><a href=\"khartoum.html\">Khartoum</a></li></ul>";
    public static String destinationTitle ="destinationTitle" ;
    public static String expectedTaxonomyChildName= "expectedTaxonomyChildName";
    public static String expectedTaxonomyParentofChild = "expectedTaxonomyParentofChild";
    public static String indexOfNodesInTaxonomyForPH = "indexOfNodesInTaxonomyForPH";
    public static String indexOfChildrenInParentalH = "indexOfChildrenInParentalH";
    public static String indexOfChildNodes= "indexOfChildNodes";
    public static String indexOfNodeInTaxonomy = "indexOfNodeInTaxonomy";
    public static String regressDestinationfile = "regressDestinationfile";
    
    public static String destinationTemplateHtml = "<!DOCTYPE html>\n" +
"<!--\n" +
"To change this license header, choose License Headers in Project Properties.\n" +
"To change this template file, choose Tools | Templates\n" +
"and open the template in the editor.\n" +
"-->\n" +
"<html>\n" +
"\n" +
"    <head>\n" +
"        <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n" +
"        <title>Lonely Planet</title>\n" +
"        <link href=\"static/all.css\" media=\"screen\" rel=\"stylesheet\" type=\"text/css\">\n" +
"        <script type=\"text/javascript\">\n" +
"            function help_click(div, txt) {\n" +
"                if (div.innerHTML == \"\")\n" +
"                    div.innerHTML = \"<br/>   \" + txt;\n" +
"                else\n" +
"                    div.innerHTML = \"\";\n" +
"            }\n" +
"        </script>\n" +
"        <script type=\"text/javascript\">\n" +
"            function ExpandCollapse(theDiv) {\n" +
"                el = document.getElementById(theDiv);\n" +
"                if (el.style.display == 'none') {\n" +
"                    el.style.display = '';\n" +
"                } else {\n" +
"                    el.style.display = 'none';\n" +
"                }\n" +
"                return false;\n" +
"            }\n" +
"        </script>\n" +
"    </head>\n" +
"    <body>\n" +
"\n" +
"\n" +
"        <div id=\"container\">\n" +
"            <div id=\"header\">\n" +
"                <div id=\"logo\"></div>\n" +
"                <h1>Lonely Planet: $destinationName</h1>\n" +
"            </div>\n" +
"\n" +
"\n" +
"            <div id=\"wrapper\">\n" +
"\n" +
"                <div id=\"sidebar\">\n" +
"                    <div class=\"block\">\n" +
"                        <h3>Navigation</h3>\n" +
"                        <div class=\"content\">\n" +
"                            <div class=\"inner\">\n" +
"                                <ul id=\"nav\">\n" +
"                                    <!--\n" +
"                                    This is the place holder for all the navigation links\n" +
"                                    As each html has a different navigation tree\n" +
"                                    with only its children and its parents\n" +
"                                    so it has to be designed to be dynamic.\n" +
"                                    -->\n" +
"                                    $navigation\n" +
"                                 \n" +
"                                </ul>\n" +
"                            </div>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                </div>\n" +
"\n" +
"                <div id=\"main\">\n" +
"                    <div class=\"block\">\n" +
"                        <div class=\"secondary-navigation\">\n" +
"                            <ul>\n" +
"                                <li class=\"first\"><a href=\"#\">$destinationName</a></li>\n" +
"                            </ul>\n" +
"                            <div class=\"clear\"></div>\n" +
"                        </div>\n" +
"                        <div class=\"content\">\n" +
"                            <div class=\"inner\">\n" +
"                                <h2 title=\"Click on me to expand\" onclick=\"ExpandCollapse('intro');\">\n" +
"                                Introduction\n" +
"                                </h2>\n" +
"\n" +
"                                <div id=\"intro\"  >\n" +
"\n" +
"                                    <p>\n" +
"                                        $introduction\n" +
"                                    </p>\n" +
"\n" +
"                                </div>\n" +
"                                \n" +
"                                <h2 title=\"Click on me to expand\"  onclick=\"ExpandCollapse('history');\"  >\n" +
"                                    History \n" +
"                                </h2>\n" +
"                                <div id=\"history\"  style=\"display:none\" >\n" +
"                                    \n" +
"                                    \n" +
"                                     $historyOverview\n" +
"                                     $histories\n" +
"                                </div>\n" +
"\n" +
"                                \n" +
"\n" +
"                                <h2 title=\"Click on me to expand\"  onclick=\"ExpandCollapse('practicalInformation');\">\n" +
"                                Practical Information\n" +
"                                </h2>\n" +
"\n" +
"                                <div id=\"practicalInformation\" style=\"display:none\">\n" +
"\n" +
"                                    <h2 title=\"Click on me to expand\"  onclick=\"ExpandCollapse('healthAndSafety');\">\n" +
"                                    Health And Safety\n" +
"                                    </h2>\n" +
"\n" +
"                                    <div id=\"healthAndSafety\"  style=\"display:none\">\n" +
"\n" +
"                                        \n" +
"                                        $beforeYouGo\n" +
"                                        \n" +
"                                        $dangersAndAnnoyances\n" +
"                                        \n" +
"                                        $inTransit\n" +
"                                        \n" +
"                                        $whileYourArethere\n" +
"\n" +
"                                    </div>\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"                                   \n" +
"\n" +
"                                    <h2 title=\"Click on me to expand\"  onclick=\"ExpandCollapse('moneyAndCosts');\">\n" +
"                                        Money And Costs\n" +
"                                    </h2>\n" +
"                             \n" +
"                                    <div id=\"moneyAndCosts\" style=\"display:none\" >\n" +
"                                       \n" +
"                                        $costs\n" +
"                                       \n" +
"                                        $money\n" +
"\n" +
"                                    </div>\n" +
"                                    <p >\n" +
"\n" +
"                                    <h2 title=\"Click on me to expand\"  onclick=\"ExpandCollapse('visas');\">\n" +
"                                        Visas\n" +
"                                    </h2>\n" +
"                                    </p>\n" +
"                                    <div id=\"visas\"  style=\"display:none\">\n" +
"                                        \n" +
"                                        $visasOverview\n" +
"                                        \n" +
"                                        $visasOther\n" +
"                                       \n" +
"                                        $permits\n" +
"\n" +
"                                    </div>\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"                                </div>\n" +
"                                <p >  \n" +
"                                <h2 title=\"Click on me to expand\"  onclick=\"ExpandCollapse('transport');\">Transport</h2>\n" +
"                                </p>\n" +
"                                <div id=\"transport\"  style=\"display:none\">\n" +
"                                    <p >\n" +
"                                    <h2 title=\"Click on me to expand\"  onclick=\"ExpandCollapse('gettingAround');\">\n" +
"                                        Getting Around\n" +
"                                    </h2>\n" +
"                                    </p>\n" +
"                                    <div id=\"gettingAround\" style=\"display:none\" >\n" +
"                                       \n" +
"                                        $gettingAroundOverview\n" +
"                                        \n" +
"                                        $gettingAroundAir\n" +
"                                       \n" +
"                                        $gettingAroundBicycle\n" +
"                                        \n" +
"                                        $gettingAroundBoat\n" +
"                                       \n" +
"                                        $gettingAroundCarAndMotorCycle\n" +
"\n" +
"                                                                              \n" +
"                                        $gettingAroundBusnTram\n" +
"                                        \n" +
"                                           $gettingAroundTrain\n" +
"\n" +
"                                      \n" +
"                                        $gettingAroundHitching\n" +
"                                       \n" +
"                                        $gettingAroundLocal\n" +
"                                        \n" +
"                                     \n" +
"\n" +
"                                    </div>\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"                                    <p >\n" +
"\n" +
"                                    <h2 title=\"Click on me to expand\"  onclick=\"ExpandCollapse('gettingTherenAway');\">\n" +
"                                        Getting There and Away\n" +
"                                    </h2>\n" +
"                                    </p>\n" +
"                                    <div id=\"gettingTherenAway\" style=\"display:none\" >\n" +
"                                       \n" +
"                                        $gettingThereAndAwayOverview\n" +
"                                        \n" +
"                                        $gettingThereAir\n" +
"\n" +
"                                        $gettingThereBicycle\n" +
"                                        \n" +
"                                        $gettingThereBoat\n" +
"                                        \n" +
"                                        $gettingThereCarAndMotorCycle\n" +
"                                        \n" +
"                                        $gettingThereBusnTram\n" +
"                                        \n" +
"                                         $gettingThereTrain\n" +
"                                        \n" +
"                                        $gettingThereHitching\n" +
"                                       \n" +
"                                        $gettingThereLocal\n" +
"                                       \n" +
"                                       \n" +
"\n" +
"                                    </div>\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"                                </div>\n" +
"\n" +
"\n" +
"                            </div>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                </div>\n" +
"\n" +
"            </div>\n" +
"        </div>\n" +
"    </body>\n" +
"</html>\n" +
"";
   
    public static String thirtyThousandDestsFile =  ".\\30000dests.xml";
    public static String cssFileLocation = "cssFileLocation";
    public static String indexOfdestInConcurrent =  "indexOfdestInConcurrent";   
    static String sampleDestinationHtml= "sampleDestinationHtml";
   
   
     
}
