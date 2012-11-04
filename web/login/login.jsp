 <%-- 
 miRNA Design Tool - Login
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="org.mirna.designer.logger.*" %>
        
<c:set var="ptitle" value="Login" scope="request"/>

<div class="login">

    <p id="designlink">
        <span onclick="regist();" style="cursor:pointer;" title="Registretion to service.">Registration</span>
        &nbsp;
        <a href="http://www.astridbio.com/about-us.html" target="_blank" title="Contact">Contact</a>
    </p>
    
    <p class="parag">
        The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection.&nbsp;
        The user gets the best result by two different Tm calculating methods.&nbsp;
        The tool designs the miRNA specific sequence of the stem-loop RT primer as well.&nbsp;
    </p>
    
    <br/>
    
    <table>

        <tbody>
            
            <tr>                    
                <td class="error" colspan="2">
                    <c:if test="${param.action == 'invalidpass'}">
                        <c:out value="Invalid username or password!"/>
                        <br/>
                        <c:out value="Please try again!"/>
                    </c:if>
                </td>
            </tr>

            <tr>
                <td>
                    <img class="loginpics" src="design/pics/login.png" alt="padlock">
                </td>

                <td>
                    <table class="log">
                        <tbody>

                            <tr>
                                <td>
                                    <c:out value="username:"/>
                                </td>

                                <td class="login">
                                    <input id="usname" type="text" name="username" value=""/>
                                </td> 
                            </tr>

                            <tr>
                                <td>
                                    <c:out value="password:"/>
                                </td>

                                <td class="login">
                                    <input id="passwd" type="password" name="password" value=""/>
                                </td>
                            </tr>

                            <tr>
                                <td></td>

                                <td align="right">
                                    <input class="submit" type="button" value="Login" onclick="getpasskey('loginprocess');"/>
                                </td>
                            </tr>

                        </tbody>
                    </table>

                </td>
            </tr>

        </tbody>

    </table>

</div>

<script type="text/javascript">
    setFocus('usname');
</script>

<% ActivityLogger.pageView( request, "login" ); %>

<form id="loginprocess" name="loginprocess" method="post" action="loginCheck" > 
    <input id="sendaction" type="hidden" name="action" value="login"/>
    <input id="sendusname" type="hidden" name="username" value=""/>
    <input id="sendpassword_data" type="hidden" name="password_data" value=""/>
</form>
