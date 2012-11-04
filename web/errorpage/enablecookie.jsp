<%-- 
miRNA Design Tool - cookie available warning
Exception(s): CookieEnabledException
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
        <meta name="copyright" content="$copy; 2009 Astrid Research">
        <meta name="description" content="Intragenic miRNA Browser contains the intragenic miRNAs of the most examined species. The browser helps you to find and get more information about intragenic miRNA(s) of the specified species.">
        <meta name="keywords" content="mirna,mirna,gene,primer,design,genom,upl,probe,astrid">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            
        <link rel="stylesheet" href="design/css/mainstyle.css" type="text/css">
        <link rel="stylesheet" href="design/css/textstyle.css" type="text/css">
        
        <title>miRNA Primer Design Tool | Cookie available warning!</title>
        
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