<%--
miRNA Primer Design Tool
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="org.mirna.designer.logger.*" %>

<form id="mdtform" method="post" action="<c:url value='processor'/>" >
    
    <table class="contain">
        
        <tbody>
            <tr>
                <td>
                    <table class="contain">
                        <tbody>
                            <tr>
                                <td id="headline1">
                                    <h2>
                                        <c:out value="Gene Groups Browser"/> <br/>
                                        <c:out value="(${genedb})"/>
                                    </h2>
                                </td>

                                <td id="headline2">
                                    <input id="showHide" class="dbutton" type="button" value="Show all" onclick="showHideAll( 'showHide' ); " title="Expand all groups."/>
                                    
                                    <input id="selec" class="dbutton" type="button" value="Check all" onclick="chkSelec( 'selec', 'mdtform' ); searchStatus('geneGroup'); " title="Select all groups."/>                                
                                    
                                    <input id="butt1" class="button" type="button"  value="Search" name="Search" title="Search" onclick="sender('mdtform', 'genegroupsearch');" disabled />
                                    &nbsp;&nbsp;&nbsp;
                                    <input id="butt2" class="button" type="button" value="<< Back" onclick="history.back();" title="Go back to previous page."/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
            
            <tr>
                <td class="groupcontain">
                    
                    <table class="contain">
                        <tbody>
                        <tr>
                            <td>
                                <div style="width:100%;height:350px;overflow:auto;">
                                <c:forEach items="${geneGroups}" var="geneGroup1">
                                    
                                    <c:set var="geneGroup1Name" value="${geneGroup1.groupName}" scope="page"/>
                                    <c:set var="geneGroup1ID" value="${geneGroup1.groupID}" scope="page"/>
                                    
                                    <div class="group1">
                                        <c:choose>
                                            
                                        <c:when test="${geneGroup1.size > 0}">
                                            
                                            <div class="group1">
                                                <a id="${geneGroup1ID}" class="linkGroup" style="cursor:pointer;" onclick="showHideMenu(this.id);">+</a>
                                                <input id="n_${geneGroup1.levelGroup}" type="checkbox" name="geneGroup" value="${geneGroup1Name}" onclick="chkboxon_db_click(this.id,this.name,this.checked,'selec','mdtform');"/>
                                                <em class="group1" style="cursor:pointer" onclick="showHideMenu('${geneGroup1ID}');">    
                                                    <c:out value="${geneGroup1Name}"/>
                                                </em>
                                            </div>
                                            
                                            <span id="s_${geneGroup1ID}" class="hideGroup">
                                            
                                              <c:forEach items="${geneGroup1.geneGroupColl}" var="geneGroup2">
                                                  
                                                  <c:set var="geneGroup2Name" value="${geneGroup2.groupName}" scope="page"/>
                                                  <c:set var="geneGroup2ID" value="${geneGroup2.groupID}" scope="page"/>
                                                  
                                                  <c:choose>
                                                      
                                                  <c:when test="${geneGroup2.size > 0}">
                                                
                                                    <em class="group2">
                                                        <a id="${geneGroup2ID}" class="linkGroup" style="cursor:pointer;" onclick="showHideMenu(this.id);">+</a>
                                                        <input id="sub_${geneGroup2.levelGroup}" type="checkbox" name="geneGroup" value="${geneGroup2Name}" onclick="clickcomplement(this.id,this.name);chkboxon_db_click(this.id,this.name,this.checked,'selec','mdtform');"/>
                                                        <em class="group22" style="cursor:pointer;" onclick="showHideMenu('${geneGroup2ID}');">    
                                                            <c:out value="${geneGroup2Name}"/>
                                                        </em>
                                                    </em>
                                                    
                                                    <br/>
                                                    
                                                    <span id="s_${geneGroup2ID}" class="hideGroup">
                                                    
                                                      <c:forEach items="${geneGroup2.geneGroupColl}" var="geneGroup3">
                                                          
                                                          <c:set var="geneGroup3Name" value="${geneGroup3.groupName}" scope="page"/>
                                                          <c:set var="geneGroup3ID" value="${geneGroup3.groupID}" scope="page"/>
                                                          
                                                          <em class="group3">
                                                              <input id="sub_${geneGroup3.levelGroup}" type="checkbox" name="geneGroup" value="${geneGroup3Name}" onclick="clickcomplement(this.id,this.name);searchStatus(this.name);chkAllButton('selec', 'mdtform');"/>
                                                              <c:out value="${geneGroup3Name}"/>
                                                          </em>
                                                          
                                                          <br/>
                                                      </c:forEach>
                                                      
                                                    </span>
                                                    
                                                  </c:when>

                                                  <c:otherwise>
                                                      <em class="group2">
                                                          <a class="linkGroup" style="visibility:hidden;">+</a>
                                                          <input id="sub_${geneGroup2.levelGroup}" type="checkbox" name="geneGroup" value="${geneGroup2Name}" onclick="clickcomplement(this.id,this.name);searchStatus(this.name);chkAllButton('selec', 'mdtform');"/>
                                                          <c:out value="${geneGroup2Name}"/>
                                                      </em>
                                                      
                                                      <br/>
                                                  </c:otherwise>
                                                  
                                                  </c:choose>

                                              </c:forEach>
                                              
                                            </span>
                                            
                                        </c:when>

                                        <c:otherwise>
                                            <div class="group1">
                                                <a class="linkGroup" style="visibility:hidden;">+</a>
                                                <input id="n_${geneGroup1.levelGroup}" type="checkbox" name="geneGroup" value="${geneGroup1Name}" onclick="searchStatus(this.name);chkAllButton('selec', 'mdtform');"/>
                                                <c:out value="${geneGroup1Name}"/>
                                            </div>
                                        </c:otherwise>
                                        
                                        </c:choose>
                                        
                                    </div>
                                    
                                </c:forEach>
                                </div>
                                
                            </td>
                            
                        </tr>
                        
                    </tbody>    
                        
                    </table>
                    
                </td>
                
            </tr>
                    
          </tbody>
                
      </table>
    
</form>

<% ActivityLogger.pageView( request, "genegrouptree" ); %>
