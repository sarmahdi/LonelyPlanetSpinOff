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
 *
 * @author sarm
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "com.sarm.lonelyplanet.model.Taxonomies")
public class Taxonomy {
    @XmlElement(name="taxonomy_name")
   private  String taxonomyName;
    @XmlElement(name="node")
   private List<Node> NodesInTaxonomy;

    public String getTaxonomyName() {
        return taxonomyName;
    }

    public void setTaxonomyName(String taxonomyName) {
        this.taxonomyName = taxonomyName;
    }

    public List<Node> getNodesInTaxonomy() {
        return NodesInTaxonomy;
    }

    public void setNodesInTaxonomy(List<Node> NodesInTaxonomy) {
        this.NodesInTaxonomy = NodesInTaxonomy;
    }
    
    
}
