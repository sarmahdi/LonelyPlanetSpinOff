/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarm.lonelyplanet.model;

import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sarm
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Node",namespace = "com.sarm.lonelyplanet.model.Taxonomies")
public class Node {
    
    @XmlElement(name="node_name")
    String NodeName;
    
    @XmlAttribute(name="atlas_node_id")
    String atlasNodeId;
    
     @XmlAttribute(name="ethyl_content_object_id")
    String ethylContentObjectId;
    
      @XmlAttribute(name="geo_id")
    String geoId;
    
    Boolean hasChildren;
    
    int numberOfChildNodes;
    
    
    @ManyToOne
              //@JoinColumn
    Node parentNode;
    
    
    @XmlElement(name="node")
    @OneToMany(mappedBy = "parentNode")
    //@JoinColumn
    List<Node> childrenNodes;

    public String getNodeName() {
        return NodeName;
    }

    public void setNodeName(String NodeName) {
        this.NodeName = NodeName;
    }

    public String getAtlasNodeId() {
        return atlasNodeId;
    }

    public void setAtlasNodeId(String atlasNodeId) {
        this.atlasNodeId = atlasNodeId;
    }

    public String getEthylContentObjectId() {
        return ethylContentObjectId;
    }

    public void setEthylContentObjectId(String ethylContentObjectId) {
        this.ethylContentObjectId = ethylContentObjectId;
    }

    public String getGeoId() {
        return geoId;
    }

    public void setGeoId(String geoId) {
        this.geoId = geoId;
    }

    public Boolean hasChildren() {
        if (null != this.getChildrenNodes() && this.getChildrenNodes().isEmpty()!= true){
            setHasChildren(true);
            this.setNumberOfChildNodes(this.getChildrenNodes().size());
        }else
            setHasChildren(false);
        this.setNumberOfChildNodes(0);
    
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        
        this.hasChildren = hasChildren;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public List<Node> getChildrenNodes() {
        return childrenNodes;
    }

    public void setChildrenNodes(List<Node> childrenNodes) {
      if (null != childrenNodes ){ 
          
          this.childrenNodes = childrenNodes;
        this.setHasChildren(childrenNodes.size()>0 ? true:false);
        this.setNumberOfChildNodes(this.childrenNodes.size());
      }
    }

    public int getNumberOfChildNodes() {
        if (this.hasChildren()){
        this.setNumberOfChildNodes(this.getChildrenNodes().size());
        }
        return numberOfChildNodes;
    }

    public void setNumberOfChildNodes(int numberOfChildNodes) {
        this.numberOfChildNodes = numberOfChildNodes;
    }
    
    
    
    
}
