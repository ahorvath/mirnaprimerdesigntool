<%-- 
comment
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
                                        <c:out value="${sessionScope.genedb} gene(s) (${geneNumber})"/>
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
                                    
                                    <input id="butt0" class="button" type="button" value="Design" name="Design" title="Design UPL 21 probe for microRNA detection." onclick="sender( 'mdtform', 'design' );" disabled />

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
                                <th width="100">GeneID</th>
                                <th width="90">Start</th>
                                <th width="90">End</th>
                                <th width="50">Chr</th>
                                <th width="220">Official<br/>Symbol</th>
                                <th>TrascriptID</th>
                                <th>ProteinID</th>
                                <!--th>AffymetrixID</th-->
                                <th>MiRNA name</th>
                                <th><input id="checkall" type="checkbox" onclick="checkallbox( 'selectedMiRNA' );"/></th>
                            </tr>
                        </thead>

                        <tbody id="containbody">
                            <c:forEach items="${geneColl}" var="gene">

                                <tr class="recordout" onmouseover="javascript:this.className='recordover'" onmouseout="javascript:this.className='recordout'">
                                    <td class="word" align="center">                                    
                                        <div class="ToolText" onmouseover="tooltipviewer(this,'ToolTextHover',null,null);" onmouseout="tooltipviewer(this,'ToolText','${top}','${left}');">
                                        
                                            <a href="http://www.ncbi.nlm.nih.gov/sites/entrez?db=gene&amp;cmd=Retrieve&amp;dopt=Graphics&amp;list_uids=${gene.geneId}" target="_BLANK">${gene.geneId}</a>

                                            <div class="ToolTexts">
                                                NCBI | Entrez Gene<br/>
                                                GeneID:&nbsp;<strong>${gene.geneId}</strong><br/>
                                                Gene full name:&nbsp;<strong>${gene.geneFullName}</strong><br/>
                                            </div>                                        
                                        </div>                                    
                                    </td>

                                    <td class="numeric">
                                        <c:out value="${gene.geneStart}"/>
                                    </td>                    

                                    <td class="numeric">
                                        <c:out value="${gene.geneEnd}"/>
                                    </td>

                                    <td class="word" align="center">
                                        <c:out value="${gene.geneChr}"/>
                                    </td>

                                    <td class="word" align="center">
                                        <div class="ToolText" onmouseover="tooltipviewer(this,'ToolTextHover',null,null);" onmouseout="tooltipviewer(this,'ToolText','${top}','${left}');">
                                            <c:out value="${gene.geneSymbol}" />
                                            
                                            <div class="ToolTexts" style="width:auto;">
                                                <c:forTokens items="${gene.geneAlternativeSymbol}" delims=";" var="element">                                                                
                                                    <c:out value="${element}" /><br/>
                                                </c:forTokens>
                                            </div>
                                            
                                        </div>

                                    </td>

                                    <td class="word" align="center">                                    
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
                                    </td>

                                    <td class="word" align="center">
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
                                    </td>

                                    <!--td class="word" align="center">
                                        <%--c:choose>

                                            <c:when test="${fn:contains(gene.affymetrixID,';')}" >
                                                <div class="ToolText" onmouseover="tooltipviewer(this,'ToolTextHover',null,null);" onmouseout="tooltipviewer(this,'ToolText','${top}','${left}');">
                                                    <strong><c:out value="${fn:substringBefore(gene.affymetrixID,';')}" /></strong>
                                                    
                                                    <div class="ToolTexts" style="width:auto;">
                                                        <c:forTokens items="${fn:substringAfter(gene.affymetrixID,';')}" delims=";" var="element">
                                                           <c:out value="${element}" /><br/>
                                                        </c:forTokens>
                                                    </div>
                                                    
                                                </div>                                                        
                                            </c:when>

                                            <c:otherwise>
                                                <c:out value="${gene.affymetrixID}" />
                                            </c:otherwise>

                                        </c:choose--%>
                                    </td-->                                

                                    <td class="word" align="left">
                                        <c:forEach items="${gene.miRNAInfoColl}" var="miRNAInfo">
                                            <div class="ToolText" onmouseover="tooltipviewer(this,'ToolTextHover',null,null);" onmouseout="tooltipviewer(this,'ToolText','${top}','${left}');">
                                                <a href="${miRNAInfo.miRNA.miRNALink}" target="_BLANK">${miRNAInfo.miRNA.miRNAName}</a>
                                                <br/>
                                                
                                                <div class="ToolTexts">
                                                    MiRBase | Sequences<BR/>
                                                    miRNA Name:&nbsp;<strong>${miRNAInfo.miRNA.miRNAName}</strong><br/>
                                                    Pos.:&nbsp;<strong>${miRNAInfo.miRNAStart}</strong>&nbsp;-&nbsp;<B>${miRNAInfo.miRNAEnd}</B><br/>
                                                    Chr.:&nbsp;<strong>${miRNAInfo.miRNAChr}</strong><br/>

                                                    <c:choose>
                                                        <c:when test="${fn:contains(miRNAInfo.standard,':')}">
                                                            Standard:&nbsp;<strong>${fn:substringBefore(miRNAInfo.standard,':')}</strong><br/>
                                                            Overlap:&nbsp;<strong>${fn:substringAfter(miRNAInfo.standard,':')}</strong>
                                                        </c:when>
                                                        <c:otherwise>
                                                            Standard:&nbsp;<strong>${miRNAInfo.standard}</strong>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </div>
                                                
                                            </div>
                                        </c:forEach>
                                    </td>

                                    <td align="center">
                                        <c:forEach items="${gene.miRNAInfoColl}" var="miRNAInfo">
                                            <input type="checkbox" NAME="selectedMiRNA" value="${miRNAInfo.miRNA.id}" onclick="designButton( 'selectedMiRNA' );" />
                                            <br/>                                    
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

<% ActivityLogger.pageView( request, "mirnatogene" ); %>

<c:if test="${geneNumber == 0}">
    <script type="text/javascript">
        alert("\nPlease check your search settings!\n\n" + 
              "Problems could be: there is no gene for the given miRNA"+
              " or the gene do not exist int he database.\n");
        history.back();
    </script>
</c:if>
