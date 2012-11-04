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