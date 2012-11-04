<%--
miRNA Design Tool
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    
    <head>
        
        <meta name="author" content="Astrid Research">
        <meta name="copyright" content="$copy; 2009 Astrid Research">
        <meta name="description" content="Intragenic miRNA Browser contains the intragenic miRNAs of the most examined species. The browser helps you to find and get more information about intragenic miRNA(s) of the specified species.">
        <meta name="keywords" content="mirna,mirna,gene,primer,design,genom,upl,probe,astrid">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-control" content="no-cache">
        <!--meta http-equiv="refresh" content="<%--${pageContext.session.maxInactiveInterval};c:url value='/index.jsp?action=startpage'/--%>"-->
            
        <link rel="shortcut icon" href="design/pics/dnap.ico">    
        
        <link rel="stylesheet" href="design/css/identification.css" type="text/css">
        <link rel="stylesheet" href="design/css/mainstyle.css" type="text/css">
        <link rel="stylesheet" href="design/css/designerstyle.css" type="text/css">
        <link rel="stylesheet" href="design/css/textstyle.css" type="text/css">
        <link rel="stylesheet" href="design/css/oligodesign.css" type="text/css">            
        <link rel="stylesheet" href="design/css/admin/adminstyle.css" type="text/css">
            
        <script type="text/javascript" language="javascript" src="design/js/identification.js"></script>
        <script type="text/javascript" language="javascript" src="design/js/registration.js"></script>
        <script type="text/javascript" language="javascript" src="design/js/md5.js"></script>
        <script type="text/javascript" language="javascript" src="design/js/controller.js"></script>
        <script type="text/javascript" language="javascript" src="design/js/designer.js"></script>
        <script type="text/javascript" language="javascript" src="design/js/expandmenu.js"></script>
        <script type="text/javascript" language="javascript" src="design/js/genegroup.js"></script>
        
        <title>miRNA Primer Design Tool</title>
        
    </head>
    
    <body class="mainpage" onunload="return true;">  
        
        <c:set var="usr" value="${sessionScope.user}" scope="page" />
        <c:if test="${usr != null}">
            <c:set var="valid" value="${sessionScope.user.valid}" scope="page" />
            <c:set var="adminrol" value="${sessionScope.user.adminrol}" scope="page" />
            <c:set var="sessid" value="${sessionScope.user.sessid}" scope="page"/>
        </c:if>
        
            <table class="maintable">    
                <tbody>

                    <tr>
                        <td class="header1" colspan="2">
                            
                            <a href="http://www.astridbio.com" target="_blank" title="www.astridbio.com">
                                <img class="space" src="design/pics/space.gif" alt="http://www.astridbio.com"/>
                            </a>
                            
                            <c:if test="${(usr != null) && (valid == true)}">
                                <input id="lgbutton" class="button" type="button" value="Log out" title="Log out" onclick="sender('lgform', 'logout');"/>
                            </c:if>
                            
                        </td>   
                    </tr>

                    <tr>
                        
                        <c:choose>
                            <c:when test="${(usr != null) && (valid == true) && (adminrol) && (sessid == pageContext.session.id)}">
                                <td class="headerAdmin" colspan="2">
                            </c:when>
                            
                            <c:otherwise>
                                <td class="headerUser" colspan="2">
                            </c:otherwise>
                        </c:choose>
                            
                            <a href="<c:url value='index.jsp?action=startpage'/>" target="_self" title="miRNA Design Tool">
                                <img class="space" src="design/pics/space.gif" alt="miRNA Design Tool"/>
                            </a>
                                                        
                        </td>   
                    </tr>
                    
                    <tr>
                        <c:if test="${(usr != null) && (valid == true) && (sessid == pageContext.session.id)}">
                            <td style="font-family:'Courier New',Courier,monospace; text-align:left;"><small style="margin-left: 2px;">Welcome: ${sessionScope.user.usname}</small></td>
                        </c:if>
                        
                        <td style="font-family:'Courier New',Courier,monospace; text-align:right;"><small style="margin-right: 2px;">v3.4</small></td>
                    </tr>

                    <tr>
                        
                        <%-- content cell--%>
                        <td colspan="2">
                            
                            <script type="text/javascript">
                                changeTitle( "miRNA Primer Design Tool", "Loading ..." );
                            </script>
                            
                            <div id="divload" class="divloadN">
                                <img class="imgload" src="design/pics/loader.gif" alt="loading..."/>
                            </div>                            
                            
                             <div id="divcontent" class="block">
                                 
                            <c:choose>
                                
                                <c:when test="${(usr != null) && (valid == true) && (sessid == pageContext.session.id)}">
                                    
                                    <c:choose>
                                        
                                        <c:when test="${!adminrol}">

                                            <c:choose>

                                                <c:when test="${param.action == 'genetomirna'}">
                                                    <jsp:include page="/representation/GeneToMiRNA.jsp" flush="true"></jsp:include>
                                                </c:when>

                                                <c:when test="${param.action == 'mirnatogene'}">
                                                    <jsp:include page="/representation/MiRNAToGene.jsp" flush="true"></jsp:include>
                                                </c:when>

                                                <c:when test="${param.action == 'viewer'}">
                                                    <jsp:include page="/representation/OligoViewer.jsp" flush="true"></jsp:include>
                                                </c:when>

                                                <c:when test="${param.action == 'genegroup'}">
                                                    <jsp:include page="/selector/GeneGroupTree.jsp" flush="true"></jsp:include>
                                                </c:when>                                

                                                <c:when test="${param.action == 'startpage'}">
                                                    <jsp:include page="/mirnapage/midetool2.jsp" flush="true"></jsp:include>
                                                </c:when>
                                                
                                                <c:when test="${param.action == 'searchpage'}">
                                                    <jsp:include page="/mirnapage/midetool1.jsp" flush="true"></jsp:include>
                                                </c:when>
                                                
                                                <c:otherwise>
                                                    <%--jsp:include page="/mirnapage/midetool.jsp" flush="true"></jsp:include--%>
                                                    <jsp:include page="/login/login.jsp" flush="true"></jsp:include>
                                                </c:otherwise>

                                            </c:choose>
                                            
                                        </c:when>
                                        
                                        <c:when test="${adminrol}">
                                            
                                            <jsp:include page="/admin/AdminHome.jsp" flush="true"></jsp:include>
                                            
                                        </c:when>
                                    
                                    </c:choose>
                                    
                                </c:when>

                                <c:when test="${param.action == 'regist'}">

                                    &nbsp;
                                    <jsp:include page="registration.jsp" flush="true"></jsp:include>
                                    &nbsp;

                                </c:when>

                                <c:otherwise>                                    
                                    <jsp:include page="/login/login.jsp" flush="true"></jsp:include>
                                    <%--jsp:include page="/update/update.jsp" flush="true"></jsp:include--%>
                                </c:otherwise>

                            </c:choose>
                            </div>
                            
                            <script type="text/javascript">
                                changeTitle( "miRNA Primer Design Tool", "${ptitle}" );
                            </script>
                            
                        </td>
                        
                        <%-- content cell--%>

                    </tr>

                    <tr>
                        <td class="footer" colspan="2">
                            
                            <a href="http://www.astridbio.com" target="_blank" title="www.astridbio.com">
                                
                                <font class="prefooter">
                                    <c:out value="astrid"/>
                                </font>

                                <font class="postfooter">
                                    <c:out value="research"/>
                                    &nbsp;&copy;&nbsp;
                                    <c:out value="2012"/>
                                </font>
                                
                            </a>
                            
                        </td>
                    </tr>
                
                </tbody>

            </table>
        
    </body>

    <form id="lgform" method="post" action="<c:url value='processor'/>">
    </form>
    
</html>
