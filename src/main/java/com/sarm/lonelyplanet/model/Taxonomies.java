/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarm.lonelyplanet.model;


import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the Bean for Taxonomies.It holds the Taxonomy, which is mapped to the
 * taxonomy element in the taxonomies.xml
 * 
 * @author sarm
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "taxonomies")
public class Taxonomies {
    
    @XmlElement(name = "taxonomy")
   private Taxonomy taxonomy;

    public  Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy= taxonomy;
    }
    
    
}
