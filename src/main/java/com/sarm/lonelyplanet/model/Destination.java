/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sarm.lonelyplanet.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.oxm.annotations.XmlCDATA;
import org.eclipse.persistence.oxm.annotations.XmlPath;

/**
 * This is the bean representation of the elements in the destination.xml. Each 
 * field is mapped to an element in the xml. Either they are single elements or 
 * list of the same elements. This class implements Cloneable  interface just 
 * to make clones of the object
 * 
 * @author sarm
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "destination")
public class Destination implements Cloneable  {

    @XmlAttribute(name = "atlas_id")
    private String atlasId;

    @XmlAttribute(name = "asset_id")
    private String assetId;

    @XmlAttribute(name = "title")
    private String title;

    @XmlAttribute(name = " title-ascii")
    private String titleAscii;

    @XmlCDATA
    @XmlPath("introductory/introduction/overview/text()")
    private String introduction;

    @XmlCDATA
    @XmlPath("history/history/history/text()")
    private List<String> histories;

    @XmlCDATA
    @XmlPath("history/history/overview/text()")
    private String historyOverview;

//    @XmlCDATA
//    @XmlPath("introductory/introduction/overview")
//    private List<String> introductionOverview;
    @XmlCDATA
    @XmlPath("transport/getting_around/overview/text()")
    private String gettingAroundOverview;

    @XmlCDATA
    @XmlPath("transport/getting_there_and_away/overview/text()")
    private String gettingThereAndAwayOverview;

    @XmlCDATA
    @XmlPath("weather/when_to_go/overview/text()")
    private List<String> whenToGoOverview;

    @XmlCDATA
    @XmlPath("work_live_study/work/business/text()")
    private List<String> workBusiness;

    @XmlCDATA
    @XmlPath("work_live_study/work/overview/text()")
    private List<String> workOverview;

//    @XmlCDATA
//    @XmlPath("")
//    private List<String> healthAndSafety;
    @XmlCDATA
    @XmlPath("practical_information/health_and_safety/before_you_go/text()")
    private List<String> beforeYouGo;
    @XmlCDATA
    @XmlPath("practical_information/health_and_safety/dangers_and_annoyances/text()")
    private List<String> dangersAndAnnoyances;
    @XmlCDATA
    @XmlPath("practical_information/health_and_safety/in_transit/text()")
    private List<String> inTransit;
    @XmlCDATA
    @XmlPath("practical_information/health_and_safety/while_youre_there/text()")
    private List<String> whileYouAreThere;

    @XmlCDATA
    @XmlPath("transport/getting_around/bus_and_tram/text()")
    private List<String> gettingAroundBusnTram;
    @XmlCDATA
    @XmlPath("transport/getting_around/hitching/text()")
    private List<String> gettingAroundHitching;
    @XmlCDATA
    @XmlPath("transport/getting_around/air/text()")
    private List<String> gettingAroundAir;

    @XmlCDATA
    @XmlPath("transport/getting_around/car_and_motorcycle/text()")
    private List<String> gettingAroundCarAndMotorCycle;

    @XmlCDATA
    @XmlPath("transport/getting_around/bicycle/text()")
    private List<String> gettingAroundBicycle;
    @XmlCDATA
    @XmlPath("transport/getting_around/boat/text()")
    private List<String> gettingAroundBoat;

    @XmlCDATA
    @XmlPath("transport/getting_around/local_transport/text()")
    private List<String> gettingAroundLocal;
    @XmlCDATA
    @XmlPath("transport/getting_around/train/text()")
    private List<String> gettingAroundTrain;
    @XmlCDATA
    @XmlPath("transport/getting_there_and_away/train/text()")
    private List<String> gettingThereAir;

    @XmlCDATA
    @XmlPath("transport/getting_there_and_away/overview/text()")
    private List<String> gettingThereOverview;

    @XmlPath("transport/getting_there_and_away/bus_and_tram/text()")
    private List<String> gettingThereBusnTram;
    @XmlCDATA
    @XmlPath("transport/getting_there_and_away/car_and_motorcycle/text()")
    private List<String> gettingThereCarAndMotorCycle;

    @XmlCDATA
    @XmlPath("transport/getting_there_and_away/bicycle/text()")
    private List<String> gettingThereBicycle;
    @XmlCDATA
    @XmlPath("transport/getting_there_and_away/boat/text()")
    private List<String> gettingThereBoat;

    @XmlCDATA
    @XmlPath("transport/getting_there_and_away/local_transport/text()")
    private List<String> gettingThereLocal;
    @XmlCDATA
    @XmlPath("transport/getting_there_and_away/train/text()")
    private List<String> gettingThereTrain;

    @XmlCDATA
    @XmlPath("transport/getting_there_and_away/hitching/text()")
    private List<String> gettingThereHitching;

    @XmlCDATA
    @XmlPath("practical_information/money_and_costs/costs/text()")
    private List<String> costs;
    @XmlCDATA
    @XmlPath("practical_information/money_and_costs/money/text()")
    private List<String> money;
    @XmlCDATA
    @XmlPath("weather/when_to_go/climate/text()")
    private List<String> whenToGoClimate;

    @XmlCDATA
    @XmlPath("practical_information/visas/overview/text()")
    private List<String> visasOverview;
    @XmlCDATA
    @XmlPath("practical_information/visas/other/text()")
    private List<String> visasOther;

    @XmlCDATA
    @XmlPath("practical_information/visas/permits/text()")
    private List<String> permits;

    public String getAtlasId() {
        return atlasId;
    }

    public void setAtlasId(String atlasId) {
        this.atlasId = atlasId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleAscii() {
        return titleAscii;
    }

    public void setTitleAscii(String titleAscii) {
        this.titleAscii = titleAscii;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<String> getHistories() {
        if (null != histories) {
            return histories;
        } else {
            return new ArrayList<>();
        }

    }

    public void setHistories(List<String> histories) {
        this.histories = histories;
    }

    public String getHistoryOverview() {
        return historyOverview;
    }

    public void setHistoryOverview(String historyOverview) {
        this.historyOverview = historyOverview;
    }

//    public List<String> getIntroductionOverview() {
//        return introductionOverview;
//    }
//
//    public void setIntroductionOverview(List<String> introductionOverview) {
//        this.introductionOverview = introductionOverview;
//    }
    public String getGettingAroundOverview() {
        return gettingAroundOverview;
    }

    public void setGettingAroundOverview(String gettingAroundOverview) {
        this.gettingAroundOverview = gettingAroundOverview;
    }

    public String getGettingThereAndAwayOverview() {
        return gettingThereAndAwayOverview;
    }

    public void setGettingThereAndAwayOverview(String gettingThereAndAwayOverview) {
        this.gettingThereAndAwayOverview = gettingThereAndAwayOverview;
    }

    public List<String> getWhenToGoOverview() {
        return whenToGoOverview;
    }

    public void setWhenToGoOverview(List<String> whenToGoOverview) {
        this.whenToGoOverview = whenToGoOverview;
    }

    public List<String> getWorkBusiness() {
        return workBusiness;
    }

    public void setWorkBusiness(List<String> workBusiness) {
        this.workBusiness = workBusiness;
    }

    public List<String> getWorkOverview() {
        return workOverview;
    }

    public void setWorkOverview(List<String> workOverview) {
        this.workOverview = workOverview;
    }

    public List<String> getBeforeYouGo() {
        return beforeYouGo;
    }

    public void setBeforeYouGo(List<String> beforeYouGo) {
        this.beforeYouGo = beforeYouGo;
    }

    public List<String> getDangersAndAnnoyances() {
        return dangersAndAnnoyances;
    }

    public void setDangersAndAnnoyances(List<String> dangersAndAnnoyances) {
        this.dangersAndAnnoyances = dangersAndAnnoyances;
    }

    public List<String> getInTransit() {
        return inTransit;
    }

    public void setInTransit(List<String> inTransit) {
        this.inTransit = inTransit;
    }

    public List<String> getWhileYouAreThere() {
        return whileYouAreThere;
    }

    public void setWhileYouAreThere(List<String> whileYouAreThere) {
        this.whileYouAreThere = whileYouAreThere;
    }

    public List<String> getGettingAroundBusnTram() {
        return gettingAroundBusnTram;
    }

    public void setGettingAroundBusnTram(List<String> gettingAroundBusnTram) {
        this.gettingAroundBusnTram = gettingAroundBusnTram;
    }

    public List<String> getGettingAroundHitching() {
        return gettingAroundHitching;
    }

    public void setGettingAroundHitching(List<String> gettingAroundHitching) {
        this.gettingAroundHitching = gettingAroundHitching;
    }

    public List<String> getGettingAroundAir() {
        return gettingAroundAir;
    }

    public void setGettingAroundAir(List<String> gettingAroundAir) {
        this.gettingAroundAir = gettingAroundAir;
    }

    public List<String> getGettingAroundCarAndMotorCycle() {
        return gettingAroundCarAndMotorCycle;
    }

    public void setGettingAroundCarAndMotorCycle(List<String> gettingAroundCarAndMotorCycle) {
        this.gettingAroundCarAndMotorCycle = gettingAroundCarAndMotorCycle;
    }

    public List<String> getGettingAroundBicycle() {
        return gettingAroundBicycle;
    }

    public void setGettingAroundBicycle(List<String> gettingAroundBicycle) {
        this.gettingAroundBicycle = gettingAroundBicycle;
    }

    public List<String> getGettingAroundBoat() {
        return gettingAroundBoat;
    }

    public void setGettingAroundBoat(List<String> gettingAroundBoat) {
        this.gettingAroundBoat = gettingAroundBoat;
    }

    public List<String> getGettingAroundLocal() {
        return gettingAroundLocal;
    }

    public void setGettingAroundLocal(List<String> gettingAroundLocal) {
        this.gettingAroundLocal = gettingAroundLocal;
    }

    public List<String> getGettingAroundTrain() {
        return gettingAroundTrain;
    }

    public void setGettingAroundTrain(List<String> gettingAroundTrain) {
        this.gettingAroundTrain = gettingAroundTrain;
    }

    public List<String> getGettingThereAir() {
        return gettingThereAir;
    }

    public void setGettingThereAir(List<String> gettingThereAir) {
        this.gettingThereAir = gettingThereAir;
    }

    public List<String> getGettingThereOverview() {
        return gettingThereOverview;
    }

    public void setGettingThereOverview(List<String> gettingThereOverview) {
        this.gettingThereOverview = gettingThereOverview;
    }

    public List<String> getGettingThereBusnTram() {
        return gettingThereBusnTram;
    }

    public void setGettingThereBusnTram(List<String> gettingThereBusnTram) {
        this.gettingThereBusnTram = gettingThereBusnTram;
    }

    public List<String> getCosts() {
        return costs;
    }

    public void setCosts(List<String> costs) {
        this.costs = costs;
    }

    public List<String> getMoney() {
        return money;
    }

    public void setMoney(List<String> money) {
        this.money = money;
    }

    public List<String> getWhenToGoClimate() {
        return whenToGoClimate;
    }

    public void setWhenToGoClimate(List<String> whenToGoClimate) {
        this.whenToGoClimate = whenToGoClimate;
    }

    public List<String> getVisasOverview() {
        return visasOverview;
    }

    public void setVisasOverview(List<String> visasOverview) {
        this.visasOverview = visasOverview;
    }

    public List<String> getVisasOther() {
        return visasOther;
    }

    public void setVisasOther(List<String> visasOther) {
        this.visasOther = visasOther;
    }

    public List<String> getGettingThereCarAndMotorCycle() {
        return gettingThereCarAndMotorCycle;
    }

    public void setGettingThereCarAndMotorCycle(List<String> gettingThereCarAndMotorCycle) {
        this.gettingThereCarAndMotorCycle = gettingThereCarAndMotorCycle;
    }

    public List<String> getGettingThereBicycle() {
        return gettingThereBicycle;
    }

    public void setGettingThereBicycle(List<String> gettingThereBicycle) {
        this.gettingThereBicycle = gettingThereBicycle;
    }

    public List<String> getGettingThereBoat() {
        return gettingThereBoat;
    }

    public void setGettingThereBoat(List<String> gettingThereBoat) {
        this.gettingThereBoat = gettingThereBoat;
    }

    public List<String> getGettingThereLocal() {
        return gettingThereLocal;
    }

    public void setGettingThereLocal(List<String> gettingThereLocal) {
        this.gettingThereLocal = gettingThereLocal;
    }

    public List<String> getGettingThereTrain() {
        return gettingThereTrain;
    }

    public void setGettingThereTrain(List<String> gettingThereTrain) {
        this.gettingThereTrain = gettingThereTrain;
    }

    public List<String> getGettingThereHitching() {
        return gettingThereHitching;
    }

    public void setGettingThereHitching(List<String> gettingThereHitching) {
        this.gettingThereHitching = gettingThereHitching;
    }

    public List<String> getPermits() {
        return permits;
    }

    public void setPermits(List<String> permits) {
        this.permits = permits;
    }
    
    
    public Destination clone() {

 

Destination destinationClone = null;

try {

destinationClone = (Destination) super.clone();

} catch (CloneNotSupportedException e) {

System.out.println(e);

}

return destinationClone;

}
    

}
