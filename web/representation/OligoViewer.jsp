<%-- 
comment
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="org.mirna.designer.logger.*" %>
        
<table class="contain">
    
    <tbody>

    <tr>
        <td>
            <table class="contain">
                <tbody>
                    <tr>
                        <td id="headline1">
                            <h2>
                                Result&nbsp;of&nbsp;design<br/>
                                <c:choose>
                                    <c:when test="${genedb == null}">
                                        <c:out value="${miRNAStyle} miRNA(s) (${fn:length(oligoColl)})"/>
                                    </c:when>
                                    
                                    <c:otherwise>
                                        <c:out value="${genedb} ${miRNAstyle} miRNA(s) (${fn:length(oligoColl)})"/>
                                    </c:otherwise>
                                </c:choose>
                            </h2>
                        </td>

                        <td id="headline2">
                            <input id="butt0" class="button" type="button" value="<< Back" TITLE="Go back to previous page." onClick="history.back();"/>                                
                        </td>
                    </tr>
                </tbody>
            </table>
        </td>
    </tr>
    
    <tr>
        <td>
            <table class="oligoView">
                
                <tbody>
                    
                    <c:forEach items="${oligoColl}" var="oligo">
                    <tr>
                        <td class="oligocontain">
                            
                            <table class="graphmirna">
                                <tbody class="oligonuc">

                                    <tr>
                                        <td class="oligoViewMature">Mature</td>
                                        <td class="oligoViewmiRNA">miRNA</td>
                                        <td></td>
                                        <td>AGGGTCC</td>
                                        <td></td>
                                    </tr>

                                    <tr>
                                        <td style="color: #703c21;"><em class="tail">5' - </em>${oligo.miRNAMatureSequencePre}</td>
                                        <td style="color: #703c21;">${oligo.miRNAMatureSequencePost}</td>
                                        <td>GT<em style="font-style: normal;color: #5ca5f5;"><c:out value="${uplSeq}"/></em>GTGC</td>
                                        <td></td>
                                        <td>G</td>
                                    </tr>
                                    
                                    <tr>
                                        <td style="color:#22783c"><em class="tail">3' - </em>${oligo.cdNA}</td>
                                        <td style="color: #f4a615;">${oligo.stemLoopPrefix}</td>
                                        <td>CAACCGAGACCACG</td>
                                        <td></td>
                                        <td>A</td>
                                    </tr>

                                    <tr>
                                        <td class="oligoViewCDNA">cDNA</td>
                                        <td style="color: #f4a615;">mss</td>
                                        <td></td>
                                        <td>CTTATGG</td>
                                        <td></td>                                            
                                    </tr>

                                </tbody>
                            </table>

                            <table>
                                <tbody class="oligonuc">

                                    <tr>
                                        <td class="labelRight">miRNAID:</td>

                                        <c:choose>
                                            <c:when test="${oligo.miRNALink == 'own'}">
                                                <td class="dataLeft">&nbsp;<STRONG>${oligo.miRNAName}</STRONG></td>
                                            </c:when>

                                            <c:otherwise>
                                                <td class="dataLeft">&nbsp;<a href="${oligo.miRNALink}" target="_BLANK" title="miRBase">${oligo.miRNAName}</a></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>

                                    <c:if test="${oligo.miRNALink != 'own'}" >
                                        <tr>
                                            <td class="labelRight">mature miRNAID:</td>
                                            <td class="dataLeft">&nbsp;<a href="${oligo.miRNALink}" target="_BLANK" title="miRBase">${oligo.miRNAVariantName}</a></td>
                                        </tr>
                                    </c:if>
                                    
                                    <tr>
                                        <td class="labelRight" style="color: #5ca5f5;">Universal ProbeLibrary probe #${uplNo}:</td>
                                        <td class="dataLeft">&nbsp;<em class="tail">5' - </em>${uplSeq}<em class="tail"> - 3'</em></td>                                        
                                    </tr>
                                    
                                    <tr>
                                        <td class="labelRight" style="color: #703c21;">miRNA mature sequence:</td>
                                        <td class="dataLeft">&nbsp;<em class="tail">5' - </em>${oligo.miRNAMatureSequence}<em class="tail"> - 3'</em></td>
                                    </tr>

                                    <tr>
                                        <td class="labelRight">stem-loop:</td>
                                        <td class="dataLeft">&nbsp;<em class="tail">5' - </em>${oligo.stemLoopPostfix}&nbsp;${oligo.stemLoopPrefixReverse}<em class="tail"> - 3'</em></td>
                                    </tr>

                                    <tr>
                                        <td class="labelRight" style="color: #f4a615;">miRNA specific sequence:</td>
                                        <td class="dataLeft">&nbsp;<em class="tail">5' - </em>${oligo.stemLoopPrefixReverse}<em class="tail"> - 3'</em></td>
                                    </tr> 

                                    <tr>
                                        <td class="labelRight" style="color:#22783c">cDNA:</td>
                                        <td class="dataLeft">&nbsp;<em class="tail">5' - </em>${oligo.cdNAReverse}<em class="tail"> - 3'</em></td>
                                    </tr>


                                </tbody>
                            </table>

                            <table>
                                <tbody class="oligonuc">

                                    <tr>
                                        <td class="labelRight">&nbsp;&nbsp;universal reverse primer:</td>
                                        <td class="dataLeft">&nbsp;<em class="tail">5' - </em>${oligo.reversePrimer}<em class="tail"> - 3'</em></td>
                                        <td class="dataLeft">&nbsp;<STRONG>length</STRONG>&nbsp;=&nbsp;${fn:length(oligo.reversePrimer)}&nbsp;</td>
                                        <td class="dataLeft">&nbsp;<STRONG>C+G percent</STRONG>&nbsp;=&nbsp;${oligo.reverseGCPercent}&nbsp;%&nbsp;</td>
                                    </tr>                                        

                                    <c:forEach items="${oligo.oligoTmPointCalColl}" var="tmCalc">

                                        <tr>
                                            <td class="dataLeft" colspan="5"><br/>${tmCalc.calcName}&nbsp;Melting&nbsp;Temperature&nbsp;(Tm)&nbsp;Calculations&nbsp;(<STRONG>universal reverse primer&nbsp;Tm</STRONG>&nbsp;=&nbsp;${tmCalc.reversePrimTmPoint}&nbsp;&deg;C)</td>
                                        </tr>                                        

                                        <c:forEach items="${tmCalc.forwardPrimers}" var="forward">
                                            <tr>
                                                <td class="labelRight">forward primer:</td>
                                                <td class="dataLeft">&nbsp;<em class="tail">5' - </em>${forward.sequence}<em class="tail"> - 3'</em></td>
                                                <td class="dataLeft">&nbsp;<STRONG>length</STRONG>&nbsp;=&nbsp;${fn:length(forward.sequence)}&nbsp;</td>
                                                <td class="dataLeft">&nbsp;<STRONG>C+G percent</STRONG>&nbsp;=&nbsp;${forward.gcPercentS}&nbsp;%&nbsp;</td>
                                                <td class="dataLeft">&nbsp;<STRONG>Tm</STRONG>&nbsp;=&nbsp;${forward.tmPointS}&nbsp;&deg;C</td>
                                            </tr>
                                        </c:forEach>

                                    </c:forEach>

                                </tbody>
                            </table>
                                    
                            <!--br/>

                            <table class="similarity">

                                <thead>

                                    <tr>
                                        <th>id</th>
                                        <th>miRNA mature sequence</th>
                                    </tr>

                                </thead>

                                <tbody>
                                    <tr>
                                        <%--td>${oligo.miRNAVariantName}</td>
                                        <td>${oligo.miRNAMatureSequence}</td--%>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </tbody>
                            </table-->

                        </td>

                    </tr>

                    </c:forEach>

                </tbody>
                
            </table>

        </td>

    </tr>          
    
    </tbody>

</table>

<% ActivityLogger.pageView( request, "oligoviewer" ); %>
