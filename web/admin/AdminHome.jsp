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
miRNA Design Tool - admin face page
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript" language="javascript" src="design/js/admin/menucontroller.js"></script>
<script type="text/javascript" language="javascript" src="design/js/admin/admincontroller.js"></script>
<script type="text/javascript" language="javascript" src="design/js/admin/adminlogcontroller.js"></script>

<c:set var="ptitle" value="Admin" scope="request"/>

<div id="adminmenu">
    
    <jsp:include page="AdminMenu.jsp" flush="true" />
    
</div>
<div id="admincontent">

    <c:choose>        
        <c:when test="${menuaction == 'create'}">
            <jsp:include page="AdminMenuAccountCreate.jsp" flush="true" />
        </c:when>

        <c:when test="${menuaction == 'modify'}">
            <jsp:include page="AdminMenuAccountModify.jsp" flush="true" />
        </c:when>

        <c:when test="${menuaction == 'traffic'}">
            <jsp:include page="AdminMenuTrafficView.jsp" flush="true" />
        </c:when>

        <c:when test="${menuaction == 'online'}">
            <jsp:include page="AdminMenuOnlineUser.jsp" flush="true" />
        </c:when>

        <c:when test="${menuaction == 'logview'}">
            <jsp:include page="AdminMenuLogView.jsp" flush="true" />
        </c:when>

        <c:when test="${menuaction == 'refresh'}">
            <jsp:include page="../update/update.jsp" flush="true" />
        </c:when>

        <c:otherwise>
            <script type="text/javascript">
                sender('menuform', 'online');
            </script>
        </c:otherwise>        
    </c:choose>

</div>