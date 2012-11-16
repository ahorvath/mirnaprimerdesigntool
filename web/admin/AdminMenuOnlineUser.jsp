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
miRNA Design Tool - admin menu (online user)
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="org.mirna.designer.logger.*" %>

<c:set var="colorrow" value="even" scope="page" />

<div id="uonline">

    <table id="onlineuser">
        <thead>
            <tr>
                <th>username</th>
                <th>comp/org</th>
                <th>log in</th>
                <th>ip</th>
                <th>valid</th>
            </tr>
        </thead>

        <tbody>

            <c:forEach items="${online}" var="onusr">
                
                <c:choose>
                    <c:when test="${colorrow == 'even'}">
                        <c:set var="colorrow" value="odd" scope="page" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="colorrow" value="even" scope="page" />
                    </c:otherwise>
                </c:choose>
               
                <tr class="${colorrow}">
                    <td>${onusr.usname}</td>
                    <td>${onusr.comp_org}</td>
                    <td>${onusr.ondate}</td>
                    <td>${onusr.ip}</td>
                    <td>
                        <form id="${onusr.usname}" method="post" action="<c:url value='control'/>">
                            <input class="button" type="button" value="kill" onclick="sender('${onusr.usname}', 'shutdown');">
                            <input type="hidden" name="sid" value="${onusr.sessid}">
                        </form>
                    </td>
                </tr>

            </c:forEach>

        </tbody>
    </table>

</div>

<% ActivityLogger.pageView( request, "online_user" ); %>
