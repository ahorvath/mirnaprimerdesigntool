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
 miRNA Design Tool - Login
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="org.mirna.designer.logger.*" %>
        
<c:set var="ptitle" value="Login" scope="request"/>

<div class="login">

    <p id="designlink">
        <span onclick="regist();" style="cursor:pointer;" title="Registretion to service.">Registration</span>
        &nbsp;
        <a href="http://genomics.med.unideb.hu/index.php?option=com_content&view=article&id=32&Itemid=17&lang=en" target="_blank" title="Contact">Contact</a>
    </p>
    
    <p class="parag">
        The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection.&nbsp;
        The user gets the best result by two different Tm calculating methods.&nbsp;
        The tool designs the miRNA specific sequence of the stem-loop RT primer as well.&nbsp;
        By using the miRNA Design Tool developed by Astrid Research You have to accept to cite the results of the tests 
        published in PLOSONE 2012 with the following title and authors*.
        
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
    
     <div align ="center" style='width: 98%; margin-left: 30px;margin-right: 30px;' >  
       <p align ="left">
        <font size="0.5">*<u>Title:</u> <b>A versatile method to design stem-loop primer-based quantitative PCR assays for detecting small regulatory RNA molecules</b>
        <br> 
        <u>Authors:</u> Zsolt Czimmerer<sup>1</sup>, Hulvely Julianna<sup>2</sup>, Zoltan Simandi<sup>1</sup>, Eva Varallyay<sup>3</sup>, Zoltan Havelda<sup>3</sup>, Erzsebet Szabo<sup>1</sup>, Attila Varga<sup>2</sup>, Balazs Dezso<sup>4</sup>, Maria Balogh<sup>1</sup>, Attila Horvath<sup>1,2</sup>, Balint Domokos<sup>2</sup>, 
        <br> Zsolt Torok<sup>2</sup>, Laszlo Nagy<sup>1,5</sup>, Balint L. Balint<sup>1,6,7</sup>
        <br>
        <u>Affiliations:</u>
        <sup>1</sup>University of Debrecen, Medical and Health Science Center, Department of Biochemistry and Molecular Biology, 
        <sup>2</sup>Astrid Research Ltd, Debrecen, 
        <br>
        <sup>3</sup>Agricultural Biotechnology Center, Gödöllö, 
        <sup>4</sup>University of Debrecen, Medical and Health Science Center, Institute of Pathology, 
        <sup>5</sup>Hungarian Academy of Sciences Apoptosis and Genomics Research Group, 
        <sup>6</sup>University of Debrecen, Medical and Health Science Center, Clinical Genomics Center, 
        <sup>7</sup>UD-Genomed Ltd, Debrecen
         </font>
       </p>
    </div>
</form>
