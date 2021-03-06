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
    MiRNA Design Tool

    Document   : registration
    Created on : Mar 4, 2010, 10:50:42 AM
    Author     : avarga
--%>

<%@page import="org.apache.jasper.tagplugins.jstl.core.Redirect"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="org.mirna.designer.logger.*" %>
<c:set var="ptitle" value="Registration" scope="request"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">
<!-- all content by Jonathan Feaster (http://www.bitdesigner.com/) -->
<script type="text/javascript" src="design/js/md5.js"></script>
<script type="text/javascript" src="design/js/jcap.js"></script>

<div class="login">

    <p class="parag">
        Create an account.&nbsp;
        Your account gives access you to miRNA Primer Design Tool service.
    </p>

    <br/>

    <table>

        <tbody>

            <tr>
        <span id="I_msg" class="msg">${msg}</span>
        </tr>

        <tr>
            <td rowspan="10">
                <img class="loginpics" src="design/pics/regist.jpg" alt="registration">
            </td>

            <td id="reg_form">

                <table class="reg">

                    <tbody>

                        <%--tr>
                            <td><c:out value="username:"/></td>
                            <td><input id="usname" type="text" name="username" value=""/></td>
                        </tr--%>

                        <tr>
                            <td class="regLabel">
                                <c:out value="email:"/>
                            </td>
                            <td>
                                <input id="reg_email" class="userfield" type="text" name="email" value=""/>
                            </td>
                        </tr>

                        <tr>
                            <td class="regLabel">
                                <c:out value="password:"/>
                            </td>
                            <td>
                                <input id="reg_passwd" class="userfield" type="password" name="password" value=""/>
                            </td>
                        </tr>

                        <tr>
                            <td class="regLabel">
                                <c:out value="confirm password:"/>
                            </td>
                            <td>
                                <input id="reg_cpasswd" class="userfield" type="password" name="cpassword" value=""/>
                            </td>
                        </tr>

                        <tr>
                            <td class="regLabel"><c:out value="first name:"/>
                            </td>
                            <td>
                                <input id="reg_first_name" class="userfield" type="text" name="fname" value=""/>
                            </td>
                        </tr>

                        <tr>
                            <td class="regLabel"><c:out value="last name:"/>
                            </td>
                            <td>
                                <input id="reg_last_name" class="userfield" type="text" name="lname" value=""/>
                            </td>
                        </tr>

                        <tr>
                            <td class="regLabel">
                                <c:out value="organization:"/>
                            </td>
                            <td>
                                <input id="reg_org" class="userfield" type="text" name="organization" value=""/>
                            </td>
                        </tr>

                        <tr>
                            <td class="regLabel">
                                <c:out value="country:"/>
                            </td>
                            <td>
                                <jsp:include page="admin/countries.jsp" flush="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="regLabel">
                                <c:out value="The CAPTCHA password:"/>
                            </td>
                            <td>
                                <input id="captcha_password" class="userfield" size="12" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                                <script type="text/javascript">sjcap();</script>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2" align="center">
                                <br/>
                                <input class="submit"
                                       type="button"
                                       value="Registration"
                                       onclick="
                                           var fields = ['reg_passwd','reg_cpasswd', 'captcha_password', 'reg_email','reg_first_name','reg_last_name','reg_org', 'I_country'];
                                           reg_control( fields, 'reg_passwd', 'reg_cpasswd', 'captcha_password', 'reg_email', 'I_msg');
                                       " />
                            </td>
                        </tr>

                    </tbody>

                </table>

            </td>
        </tr>

        </tbody>

    </table>
</div>

<% ActivityLogger.pageView(request, "registration");%>
