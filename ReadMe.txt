This is he LonelyPlanet destination Information creation. It generates static html files for each location in their destination list and contains different information for that location from the perspective of a traveller of what to expect and how to travel to that region. Some locations have more informations than the other.

It takes three inputs
1) a Taxonomy file (taxonomy.xml) - It lists the different destinations and is structured in a XML tree to represent the relationships between different location. Each parent will contain it's children and every child will contain its subsequent children.
2) A destination information file (destination.xml)- For each destination it contains different informtation in different categories. 
3) A target location for the generation of html files. 

Technology Overview:
Maven, Java7, JAXB(MOXy)
Working With Project:

How to compile:
After the project is created in its root folder $PROJECT_ROOT it can be noticed that this is a maven based project. The build is created using maven. As there is a neccessary assembly parameters in order to assemble it in an executable jar file along with some of the neccessary dependencies. Maven is needed to only build this project. 

In order to work with Maven for this project please download maven 3.2.1. It is a simple task of downloading and extracting the zip file into a location, $MAVEN_HOME. (It is also advisable to set it up $MAVEN_HOME as a system variable by adding to Path (in Windows %MAVEN_HOME%/bin)).

Option 1 to run:
$MAVEN_HOME>mvn -f$PROJECT_ROOT\pom.xml clean install

Option2 to run:
($MAVEN_HOME needs to be set as System Environment Variable)
$PROJECT_ROOT>mvn clean install 

Option 3 to compile:
To Skip JUnit tests
$PROJECT_ROOT>mvn clean install -Dmaven.test.skip=true

These two options will create an executable jar file named :  LonelyPlanet-1.0-SNAPSHOT-jar-with-dependencies.jar .
(This file is also added in the Project root for simplicty if Maven is not setup on the machine and application needs to be run as-is)
Note1: If this is the first time that maven is running on the machine, maven will create a repository and download the required plugins and jars. In order for that to happen the machine needs to have access to internet to be able to download the plugins. 
Note2: A sample of compile log (compile_sample.log) is in the project root in order to give an idea what to expect in a normal mvn clean install command.

Running the LonelyPLanet batch process application :
This is a Java application. It was build under the Java7 (It uses some new features from Java7 that I wanted to learn myself and seen them as more efficient from Java6). Please download java 7 jdk1.7.0_55 and install to $JAVA_INSTALL_ROOT. Please create $JAVA_HOME and point to $JAVA_INSTALL_ROOT. Add to Path  as $JAVA_HOME/bin (in windows %JAVA_HOME%/bin )

Sample command
($JAR_FOLDER is the folder where the LonelyPlanet-1.0-SNAPSHOT-jar-with-dependencies.jar is placed. )

$JAR_FOLDER>java -jar LonelyPlanet-1.0-SNAPSHOT-jar-with-dependencies.jar $TAXONOMY_ROOT\taxonomy.xml $DESTINATION_ROOT\destinations.xml $TARGETFOLDER_ROOT\HTMLTargetFolder\


Technical Design overview
A technical design document is supplied in the $PROJECT_ROOT/Documentation/ folder.






