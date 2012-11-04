<%--
miRNA Primer Design Tool
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="org.mirna.designer.logger.*" %>

<c:set var="top" value="1.2em"/>
<c:set var="left" value="20px"/>

<form id="mdtform" method="post" action="<c:url value='processor'/>" >

    <table class="contain">

        <tbody>
            <tr>
                <td>
                    <table class="contain">
                        <tbody>
                            <tr>
                                <td id="headline1">
                                    <h2>
                                        <c:out value="${sessionScope.genedb} ${sessionScope.miRNAStyle} miRNA(s) (${miRNANumber})"/>
                                    </h2>
                                </td>

                                <td id="headline2">
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
                                    
                                    <input id="butt0" class="button" type="button" value="Design" name="Design" title="Design UPL 21 probe for microRNA detection." onclick="sender('mdtform', 'design');" disabled />
                                    
                                    &nbsp;&nbsp;&nbsp;
                                    <input id="butt1" class="button" type="button" value="<< Back" onClick="history.back();" title="Go back to previous page."/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </td>
            </tr>

            <tr>
                <td>
                    <table class="contain">
                        <thead>
                            <tr>
                                <th width="30" align="center"><input id="checkall" type="checkbox" onclick="checkallbox( 'selectedMiRNA' );"/></th>
                                <th width="120">miRNA name</th>
                                <th width="90">Start</th>
                                <th width="90">End</th>
                                <th width="50">Chr</th>                
                                <th width="100">GeneID</th>
                                <th width="210">Official<br/>Symbol</th>
                                <th>TranscriptID</th>
                                <th>ProteinID</th>
                                <th>AffymetrixID<br/><font class="lowerlabel">HG 133 Plus 2.0</font></th>
                            </tr>
                        </thead>

                        <tbody id="containbody">
                            <c:forEach items="${miRNAColl}" var="miRNA">

                                <tr class="recordout" onmouseover="javascript:this.className='recordover'" onmouseout="javascript:this.className='recordout'">
                                    <td align="center">
                                        <input type="checkbox" name="selectedMiRNA" value="${miRNA.id}" onclick="designButton( 'selectedMiRNA' );" />
                                    </td>

                                    <td class="word" align="left">
                                        <a href="${miRNA.miRNALink}" target="_BLANK" title="MiRBase | Sequences">${miRNA.miRNAName}</a>
                                    </td>

                                    <td class="numeric" align="right">
                                        <c:forEach items="${miRNA.miRNAInfoColl}" var="miRNAInfo">
                                            <c:out value="${miRNAInfo.miRNAStart}"/>
                                            <br/>
                                        </c:forEach>                        
                                    </td>

                                    <td class="numeric" align="right">
                                        <c:forEach items="${miRNA.miRNAInfoColl}" var="miRNAInfo">
                                            <c:out value="${miRNAInfo.miRNAEnd}"/>
                                            <br/>
                                        </c:forEach>                        
                                    </td>

                                    <td class="numeric" align="center">
                                        <c:forEach items="${miRNA.miRNAInfoColl}" var="miRNAInfo">
                                            <c:out value="${miRNAInfo.miRNAChr}"/>
                                            <br/>
                                        </c:forEach>
                                    </td>

                                    <td class="word" align="center">
                                        <c:forEach items="${miRNA.miRNAInfoColl}" var="miRNAInfo">
                                            
                                            <c:if test="${fn:length(miRNAInfo.geneColl) == 0}">
                                                <div class="ToolText">&nbsp;</div>
                                            </c:if>

                                            <c:forEach items="${miRNAInfo.geneColl}" var="gene">
                                                <div class="ToolText" onmouseover="tooltipviewer(this,'ToolTextHover',null,null,null);" onmouseout="tooltipviewer(this,'ToolText','${top}','${left}');">
                                                    
                                                    <c:forTokens items="${gene.geneId}" delims="_" var="geneLink" begin="0" end="0">
                                                        <c:set var="gLink" value="${geneLink}"></c:set>
                                                    </c:forTokens>

                                                    <a href="http://www.ncbi.nlm.nih.gov/sites/entrez?db=gene&amp;cmd=Retrieve&amp;dopt=Graphics&amp;list_uids=${gLink}" target="_BLANK">${gene.geneId}</a>

                                                    <div class="ToolTexts">
                                                        NCBI | Entrez Gene<br/>
                                                        GeneID:&nbsp;<strong>${gLink}</strong><br/>
                                                        Gene full name:&nbsp;<strong>${gene.geneFullName}</strong><br/>
                                                        Pos.:&nbsp;<strong>${gene.geneStart}</strong>&nbsp;-&nbsp;<strong>${gene.geneEnd}</strong><br/>
                                                        Chr.:&nbsp;<strong>${gene.geneChr}</strong>
                                                    </div>
                                                    
                                                </div> 
                                           </c:forEach>

                                       </c:forEach>                        
                                    </td>                    

                                    <td class="word" align="center">
                                        <c:forEach items="${miRNA.miRNAInfoColl}" var="miRNAInfo">
                                            
                                            <c:if test="${fn:length(miRNAInfo.geneColl) == 0}">
                                                <div>&nbsp;</div>
                                            </c:if>                                            

                                            <c:forEach items="${miRNAInfo.geneColl}" var="gene">
                                                <div class="ToolText" onmouseover="tooltipviewer(this,'ToolTextHover',null,null);" onmouseout="tooltipviewer(this,'ToolText','${top}','${left}');">
                                                    
                                                    <c:out value="${gene.geneSymbol}" />
                                                    <div class="ToolTexts" style="width:auto;">
                                                        <c:forTokens items="${gene.geneAlternativeSymbol}" delims=";" var="element">                                                                
                                                            <c:out value="${element}" /><br/>
                                                        </c:forTokens>
                                                    </div>
                                                    
                                                </div>
                                            </c:forEach>

                                        </c:forEach>
                                    </td>

                                    <td class="word" align="center">
                                        <c:forEach items="${miRNA.miRNAInfoColl}" var="miRNAInfo">
                                            
                                            <c:if test="${fn:length(miRNAInfo.geneColl) == 0}">
                                                <div>&nbsp;</div>
                                            </c:if>                                            

                                            <c:forEach items="${miRNAInfo.geneColl}" var="gene">
                                                <c:choose>

                                                    <c:when test="${fn:contains(gene.transcriptID,';')}" >
                                                        <div class="ToolText" onmouseover="tooltipviewer(this,'ToolTextHover',null,null);" onmouseout="tooltipviewer(this,'ToolText','${top}','${left}');">
                                                            <strong><c:out value="${fn:substringBefore(gene.transcriptID,';')}" /></strong>
                                                            <div class="ToolTexts" style="width:auto;">
                                                                <c:forTokens items="${fn:substringAfter(gene.transcriptID,';')}" delims=";" var="element">
                                                                   <c:out value="${element}" /><br/>
                                                                </c:forTokens>
                                                            </div>                                                   
                                                        </div>
                                                    </c:when>

                                                    <c:otherwise>
                                                        <c:out value="${gene.transcriptID}" />
                                                    </c:otherwise>

                                                </c:choose>
                                            </c:forEach>
                                            
                                        </c:forEach>
                                    </td>

                                    <td class="word" align="center">
                                        <c:forEach items="${miRNA.miRNAInfoColl}" var="miRNAInfo">
                                          
                                            <c:if test="${fn:length(miRNAInfo.geneColl) == 0}">
                                                <div>&nbsp;</div>
                                            </c:if>
                                            
                                          <c:forEach items="${miRNAInfo.geneColl}" var="gene">
                                              <c:choose>

                                                <c:when test="${fn:contains(gene.proteinID,';')}" >
                                                      <div class="ToolText" onmouseover="tooltipviewer(this,'ToolTextHover',null,null);" onmouseout="tooltipviewer(this,'ToolText','${top}','${left}');">
                                                          <strong><c:out value="${fn:substringBefore(gene.proteinID,';')}" /></strong>
                                                          <div class="ToolTexts" style="width:auto;">
                                                             <c:forTokens items="${fn:substringAfter(gene.proteinID,';')}" delims=";" var="element">
                                                                 <c:out value="${element}" /><br/>
                                                              </c:forTokens>
                                                          </div>
                                                     </div>                                                        
                                                  </c:when>

                                                  <c:otherwise>
                                                      <c:out value="${gene.proteinID}" />
                                                 </c:otherwise>

                                              </c:choose>
                                          </c:forEach>
                                          
                                        </c:forEach>
                                    </td>

                                    <td class="word" align="center">
                                        <c:forEach items="${miRNA.miRNAInfoColl}" var="miRNAInfo">
                                            
                                            <c:if test="${fn:length(miRNAInfo.geneColl) == 0}">
                                                <div>&nbsp;</div>
                                            </c:if>
                                            
                                            <c:forEach items="${miRNAInfo.geneColl}" var="gene">
                                                <c:choose>

                                                    <c:when test="${fn:contains(gene.affymetrixID,';')}" >
                                                        <div class="ToolText" onmouseover="tooltipviewer(this,'ToolTextHover',null,null);" onmouseout="tooltipviewer(this,'ToolText','${top}','${left}');">
                                                            <strong><c:out value="${fn:substringBefore(gene.affymetrixID,';')}" /></strong>
                                                            <div class="ToolTexts" style="width:auto;">
                                                                <c:forTokens items="${fn:substringAfter(gene.affymetrixID,';')}" delims=";" var="element">
                                                                   &nbsp; <c:out value="${element}" /><br/>
                                                                </c:forTokens>
                                                            </div>
                                                        </div>                                                        
                                                    </c:when>

                                                    <c:otherwise>
                                                        <c:out value="${gene.affymetrixID}" />
                                                    </c:otherwise>

                                                </c:choose>
                                            </c:forEach>

                                        </c:forEach>
                                    </td>
                               </tr>

                            </c:forEach> 

                        </tbody>                     

                   </table>

                </td>

            </tr>

        </tbody>

    </table>

</form>

<% ActivityLogger.pageView( request, "genetomirna" ); %>

<c:if test="${miRNANumber == 0}">
    <script type="text/javascript">
        alert("\nPlease check your search settings!\n");
        history.back();
    </script>
</c:if>
