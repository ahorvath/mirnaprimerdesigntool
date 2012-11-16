/*
* The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection. The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 
*
* copyright (C) 2009-2012 Astrid Research Ltd. 
* copyright (C) November, 2012 University of Debrecen, Clinical Genomic Center, Medical and Health Science Center, Debrecen, Hungary
*
* The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection.  The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 
*
*    miRNA Design Tool is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    miRNA Design Tool is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with miRNA Primer Design Tool.  If not, see <http://www.gnu.org/licenses/>.
*/
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
