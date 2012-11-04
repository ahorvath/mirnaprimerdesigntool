<%-- 
miRNA Design Tool - admin menu (account create)
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="org.mirna.designer.logger.*" %>

<c:choose>
    <c:when test="${menuaction == 'create'}">
        <c:set var="divclass" value="ucreated" scope="page" />
    </c:when>
    
    <c:otherwise>
        <c:set var="divclass" value="umodified" scope="page" />
    </c:otherwise>
</c:choose>

<form id="I${usr.id}_F" method="post" action="<c:url value='control'/>">
    
    <div id="I${usr.id}_D" class="${divclass}">
        
        <span id="I${usr.id}_msg" class="msg">${msg}</span>
        
        <table class="ucreatet">
            <tbody>
                <tr>
                    <td class="ulabel">first name:</td>
                    <td><input id="I${usr.id}_fname" class="userfield" name="userfname" type="text" value="${usr.firstname}" /></td>
                    <td class="ulabel">username:</td>
                    <td id="I${usr.id}_usrn">
                        <c:choose>
                            <c:when test="${menuaction == 'modify'}">
                                <input type="text" class="userfield" name="username" value="${usr.usname}" disabled />
                                <input id="I${usr.id}_username" type="hidden" name="username" value="${usr.usname}"/>
                            </c:when>
                            <c:otherwise>
                                <input id="I${usr.id}_username" class="userfield" type="text" name="username" value="${usr.usname}" />
                            </c:otherwise>
                        </c:choose>
                    </td>                    
                </tr>
                
                <tr>
                    <td class="ulabel">last name:</td>
                    <td><input id="I${usr.id}_lname" class="userfield" name="userlname" type="text" value="${usr.lastname}" /></td>
                    
                    <c:choose>
                        <c:when test="${menuaction == 'modify'}">
                            <td class="ulabel">password:</td>
                            <td><input id="I${usr.id}_passw" class="userfield" name="userpass" type="password" value="" /></td>
                        </c:when>
                        
                        <c:otherwise>
                            <td></td>
                            <td></td>
                        </c:otherwise>                    
                    </c:choose>
                    
                </tr>
                
                <tr>
                    <td class="ulabel">comp/org:</td>
                    <td><input id="I${usr.id}_org" class="userfield" name="userorg" type="text" value="${usr.comp_org}"/></td>
                    <td class="ulabel">user type:</td>
                    <td>
                        <jsp:include page="usertypes.jsp" flush="true" />
                    </td>                    
                </tr>
                
                <tr>
                    <td class="ulabel">country:</td>
                    <td>
                        <jsp:include page="countries.jsp" flush="true" />
                    </td>
                    <td class="ulabel">user not valid:</td>
                    <td><input id="I${usr.id}_valid" type="checkbox" name="uservalid" value="true"></td>                    
                </tr>
                
                <tr>
                    <td class="ulabel">tel.:</td>
                    <td><input id="I${usr.id}_tel" class="userfield" type="text" name="usertel" value="${usr.tel}" /></td>
                    <td></td>
                    <td></td>
                </tr>
                
                <tr>
                    <td class="ulabel">e-mail:</td>
                    <td>
                        <input id="I${usr.id}_email" class="userfield" type="text" name="useremail" value="${usr.email}"/>
                    </td>
                    <td></td>
                    <td>
                        <input style="width:200px;"
                               class="button"
                               type="button"
                               value="${menuaction}"
                               onclick="menuAccountCreateProcess(['I${usr.id}_fname','I${usr.id}_lname','I${usr.id}_country','I${usr.id}_org','I${usr.id}_email','I${usr.id}_username','I${usr.id}_type'],'I${usr.id}_F','${menuaction}','I${usr.id}_msg','I${usr.id}_email','I${usr.id}_passw');" />
                    </td>                    
                </tr>
                
            </tbody>
        </table>
        
    </div>
    
</form>

<c:if test="${usnamevalid}">
    <script type="text/javascript">
        document.getElementById('I${usr.id}_usrn').borderColor = 'red';
        var ids = ['I${usr.id}_country','I${usr.id}_type','I${usr.id}_valid'];
        var data = ['${usr.country}','${usr.usertype}','${usr.valid}'];
        remainderFill( ids, data );
    </script>
</c:if>

<c:if test="${menuaction == 'create'}">
    <% ActivityLogger.pageView( request, "acount_create" ); %>
</c:if>
