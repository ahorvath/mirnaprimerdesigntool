<%-- 
miRNA Design Tool - admin menu (traffic view)
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="org.mirna.designer.logger.*" %>

<c:set var="colorrow" value="even" scope="page" />

<div id="uonline">
    
    <table id="onlineuser">
        <thead>
            <tr>
                <th>sum</th>
                <th>goal</th>
                <th>faild</th>
                <th>ip</th>
                <th>last</th>
            </tr>
        </thead>

        <tbody>

            <c:forEach items="${traffic}" var="traffic">

                <c:choose>
                    <c:when test="${colorrow == 'even'}">
                        <c:set var="colorrow" value="odd" scope="page" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="colorrow" value="even" scope="page" />
                    </c:otherwise>
                </c:choose>

                <tr class="${colorrow}">
                    <td>${traffic.suma}</td>
                    <td>${traffic.goal}</td>
                    <td>${traffic.faild}</td>
                    <td>${traffic.ip}</td>
                    <td>${traffic.lazt}</td>
                </tr>

            </c:forEach>

        </tbody>
    </table>
    
</div>

<% ActivityLogger.pageView( request, "traffic_view" ); %>