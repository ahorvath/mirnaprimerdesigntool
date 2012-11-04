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
