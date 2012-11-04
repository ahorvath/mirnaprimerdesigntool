<%-- 
comment
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="org.mirna.designer.logger.*" %>

<c:set var="ptitle" value="" scope="request"/>

<form id="mdtform" method="post" action="<c:url value='processor'/>">

    <div class="content">
         <p id="designlink">
             <a  href="<c:url value='index.jsp?action=searchpage'/>" target="_blank" title="Other Tools" >Other Tools</a>
             &nbsp;
             <a href="http://www.astridbio.com/about-us.html" target="_blank" title="Contact">Contact</a>
         </p>        

         <h1>miRNA Design Tool</h1>
        
         <p class="parag">             
             The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection.&nbsp;
             The user gets the best result by two different Tm calculating methods.&nbsp;
             The tool designs the miRNA specific sequence of the stem-loop RT primer as well.&nbsp;
         </p>

        
        <h1>Primer design for optional small RNA sequence(s)</h1>
        
        <p class="parag">
            Type your optional small RNA sequence which should be minimum 17 nucleotides long.&nbsp;
            You can design several primer sets simultaneously.&nbsp;
            Be aware that the sequences should be separated by Enter. <span style="color:red">Due to this feature, please doublecheck not to have Enter followed by no sequence, before or after your input sequences.</span><br/>
            
            <em>
                <textarea class="tarea" id="ownMiRNASeq" name="ownMiRNASequence" onfocus="changeBackColor(this.id, 'on');" onblur="changeBackColor(this.id, 'blur');" rows="0" cols="0"></textarea>
                <br/>
                
                <span id="uplWord">
                    <c:out value="UPL probe:"/>
                </span>

                <select id="upl" name="upltag">
                    <option value="6;cagaggaa">6</option>
                    <option value="20;ccagccag">20</option>
                    <option value="21;tggctctg" selected>21</option>
                    <option value="64;cagcctgg">64</option>
                    <option value="82;cagaggag">82</option>
                </select>                
                
                <input id="own" class="button" type="button"  name="ownMiRNA" value="Design" title="Primer design for optional miRNA sequence(s)" onclick="miRNACheck('mdtform', 'ownmirna');"/>                
            </em>
        </p>

    </div>

</form>

<% ActivityLogger.pageView( request, "midetool2" ); %>
