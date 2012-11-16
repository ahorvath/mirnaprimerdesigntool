<%--
The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection. The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 

copyright (C) 2009-2012 Astrid Research Ltd. 
copyright (C) November, 2012 University of Debrecen, Clinical Genomic Center, Medical and Health Science Center, Debrecen, Hungary

The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection.  The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 

    miRNA Design Tool is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    miRNA Design Tool is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with miRNA Primer Design Tool.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%-- 
comment
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="org.mirna.designer.logger.*" %>

<c:set var="ptitle" value="" scope="request"/>

<form id="mdtform" method="post" action="<c:url value='processor'/>">

    <div class="content">
         <p id="designlink">
             <a href="<c:url value='index.jsp?action=searchpage'/>" target="_blank" title="Other Tools">Other Tools</a>
             &nbsp;
             <a href="http://genomics.med.unideb.hu/index.php?option=com_content&view=article&id=32&Itemid=17&lang=en" target="_blank" title="Contact">Contact</a>
         </p>        

         <h1>miRNA Design Tool</h1>
        
         <p class="parag">             
             The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection.&nbsp;
             The user gets the best result by two different Tm calculating methods.&nbsp;
             The tool designs the miRNA specific sequence of the stem-loop RT primer as well.&nbsp;
         </p>

        
        <h1>Primer design for optional small RNA sequence(s)</h1>
        
        <p class="parag">
            Type your optional small RNA sequence which should be minimum 17 nucleotides long.&nbsp;
            You can design several primer sets simultaneously.&nbsp;
            Be aware that the sequences should be separated by Enter. <span style="color:red">Due to this feature, please doublecheck not to have Enter followed by no sequence, before or after your input sequences.</span><br/>
            
            <em>
                <textarea class="tarea" id="ownMiRNASeq" name="ownMiRNASequence" onfocus="changeBackColor(this.id, 'on');" onblur="changeBackColor(this.id, 'blur');" rows="0" cols="0"></textarea>
                <br/>
                
                <span id="uplWord">
                    <c:out value="UPL probe:"/>
                </span>

                <select id="upl" name="upltag">
                    <option value="6;cagaggaa">6</option>
                    <option value="20;ccagccag">20</option>
                    <option value="21;tggctctg" selected>21</option>
                    <option value="64;cagcctgg">64</option>
                    <option value="82;cagaggag">82</option>
                </select>                
                
                <input id="own" class="button" type="button"  name="ownMiRNA" value="Design" title="Primer design for optional miRNA sequence(s)" onclick="miRNACheck('mdtform', 'ownmirna');"/>                
            </em>
        </p>

    </div>

</form>

<% ActivityLogger.pageView( request, "midetool2" ); %>
