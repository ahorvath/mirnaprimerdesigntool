<%-- 
miRNA Design Tool - admin menu (account modify)
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="org.mirna.designer.logger.*" %>

<c:set var="usnamevalid" value="true" scope="request" />

<div id="modifycontent">

    <table id="userList">
        <thead>
            <tr>
                <th>username</th>
                <th>usertype</th>
                <th>admin</th>
                <th>valid</th>
            </tr>
        </thead>
        <tbody>

            <c:forEach items="${usrList}" var="u">
                <tr id="I${u.usname}" 
                    class="unclickedRow" 
                    onclick="showHideUser( 'I${u.usname}', 'I${u.usname}_R' );" 
                    onmouseover="selectUnselect( 'I${u.usname}', 'sel' );" 
                    onmouseout="selectUnselect( 'I${u.usname}', 'un' );">

                    <td><c:out value="${u.usname}" /></td>
                    <td><c:out value="${u.usertype}" /></td>
                    <td><c:out value="${u.adminrol}" /></td>
                    <td><c:out value="${u.valid}" /></td>
                </tr>
                <tr>
                    <td colspan="4">
                        <div id="I${u.usname}_R" class="hideUser">
                        
                          <c:set var="usr" value="${u}" scope="request" />
                          
                          <jsp:include page="AdminMenuAccountCreate.jsp" flush="true" />
                          
                        </div>
                    </td> 
                </tr>
            </c:forEach>

        </tbody>
    </table>

</div>

<% ActivityLogger.pageView( request, "acount_modify" ); %>