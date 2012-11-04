<%--
miRNA Design Tool - admin menu (log view)
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="org.mirna.designer.logger.*" %>

<div id="logview_D">
    
    <div id="logviewcontent">
        
        <c:choose>
            <c:when test="${logaction == 'duo'}">                
                <table id="userList">
                    <tbody>
                        <c:forEach items="${logtext}" var="logMap">
                            <tr id="I${logMap.key}" 
                                align="left" 
                                class="unclickedRow" 
                                onclick="showHideUser( 'I${logMap.key}', 'I${logMap.key}_R' );"
                                onmouseover="selectUnselect( 'I${logMap.key}', 'sel' );" 
                                onmouseout="selectUnselect( 'I${logMap.key}', 'un' );">
                                
                                <td><c:out value="${logMap.key}" /></td>
                            </tr>
                            <tr id="I${logMap.key}_R" class="hideUser">
                                <td><textarea class="logarea" cols="135" rows="15"><c:out value="${logMap.value}" /></textarea></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            
            <c:otherwise>
                <textarea class="logarea" cols="0" rows="0"><c:out value="${logtext}" /></textarea>
            </c:otherwise>
        </c:choose>
        
    </div>
    
    <div>
        <form id="logform" name="viewform" method="post" action="<c:url value='control'/>">
    
            <table id="logmenu">
                <tbody>
                    <tr>
                        <td>
                            <span>username:</span>
                            <select id="uname" name="uname">
                                <option value="unco">unco</option>
                                <c:forEach items="${usercoll}" var="usr">
                                    <option value="${usr}"><c:out value="${usr}" />
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <span>date:</span>
                            <select id="udate" name="udate">
                                <option value="today"><c:out value="today" />
                                <c:forEach items="${datecoll}" var="dtime">
                                    <option value="${dtime}"><c:out value="${dtime}" />
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="radio" name="uview" value="usrlog" /><c:out value="username" />
                        </td>
                        <td>
                            <input type="radio" name="uview" value="datlog" checked /><c:out value="date log" />
                        </td>
                        <td>
                            <input type="radio" name="uview" value="dat_usrlog" /><c:out value="username & date log" />
                        </td>
                        <td>
                            <input class="button" type="button" value="refresh" onclick="sender( 'logform', 'logview' )" />
                        </td>
                    </tr>
                </tbody>
            </table>
            
        </form>
    </div>
    
</div>

<% ActivityLogger.pageView( request, "log_view" ); %>