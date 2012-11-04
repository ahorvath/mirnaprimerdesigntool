package org.mirna.bean.gene;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Attila
 */
public class GeneGroup {
    
    private String groupName;
    private int groupDepth;
    private String groupID;
    private String levelGroup;
  
    private int size;
    private Collection<GeneGroup> geneGroupColl = new ArrayList<GeneGroup>();
    
    public GeneGroup( String groupName, int groupDepth ) {
        
        this.groupName = groupName;
        this.groupDepth = groupDepth;        
        this.groupID = groupName.replaceAll(" ", "");
        this.groupID = this.groupID.replaceAll(",", ":");
        
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupDepth() {
        return groupDepth;
    }

    public void setGroupDepth(int groupDepth) {
        this.groupDepth = groupDepth;
    }

    public Collection<GeneGroup> getGeneGroupColl() {
        return geneGroupColl;
    }

    public void setGeneGroupColl(Collection<GeneGroup> geneGroupColl) {
        this.geneGroupColl = geneGroupColl;
    }
    
    public int getSize() {
        return geneGroupColl.size();
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getLevelGroup() {
        return levelGroup;
    }

    public void setLevelGroup(String levelGroup) {
        this.levelGroup = levelGroup;
    }

}
