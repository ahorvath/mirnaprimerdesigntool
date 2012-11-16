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
miRNA Design Tool - registration error
Exception(s): 
       AuthenticationFailedException,
       AddressException,
       MessagingException,
--%>

<%@page isErrorPage="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="org.mirna.designer.logger.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>

        <meta name="author" content="Astrid Research">
        <meta name="copyright" content="$copy; 2009-2012 Astrid Research">
        <meta name="description" content="Intragenic miRNA Browser contains the intragenic miRNAs of the most examined species. The browser helps you to find and get more information about intragenic miRNA(s) of the specified species.">
        <meta name="keywords" content="mirna,mirna,gene,primer,design,genom,upl,probe,astrid">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="design/css/mainstyle.css" type="text/css">
        <link rel="stylesheet" href="design/css/textstyle.css" type="text/css">

        <title>miRNA Primer Design Tool | Mail send error!</title>

    </head>

    <body>

        <div>

            <table>

                <tbody>

                    <tr>
                        <td align="right">
                             <img src="design/pics/warning.jpg" width="100" height="100" border="0" alt="error"/>
                         </td>

                         <td>

                             <table>

                                 <tbody>

                                     <tr>
                                         <td>
                                             <p class="parag">
                                                 <c:out value="Your browser's cookie functionality is turned off."/>
                                                 <br/>
                                                 <c:out value="Please turn it on."/>
                                             </p>
                                         </td>
                                     </tr>

                                     <tr>
                                         <td align="right">
                                             <input id="butt0" class="button" type="button" value="<< Back" onClick="history.back();" title="Go back to previous page."/>
                                         </td>
                                     </tr>

                                 </tbody>

                             </table>

                         </td>

                    </tr>

                </tbody>

            </table>

        </div>

        <% ActivityLogger.pageView( request, "enablecookie"); %>

  </body>

</html>