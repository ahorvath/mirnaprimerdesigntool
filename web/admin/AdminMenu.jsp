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
miRNA Design Tool - admin menu page
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table id="menuTable">

    <tbody>
        <tr id="menupoint">
            <td class="menup"><span id="online" class="menuOff" onclick="menunClick('menupoint',this);sender('menuform','online');" onmouseover="menuOver(this)" onmouseout="menuOut(this)">online&nbsp;users</span></td>
            <td class="menup"><span id="create" class="menuOff" onclick="menunClick('menupoint',this);sender('menuform','create');" onmouseover="menuOver(this)" onmouseout="menuOut(this)">create&nbsp;account</span></td>
            <td class="menup"><span id="modify" class="menuOff" onclick="menunClick('menupoint',this);sender('menuform','modify');" onmouseover="menuOver(this)" onmouseout="menuOut(this)">modify&nbsp;account</span></td>
            <td class="menup"><span id="logview" class="menuOff" onclick="menunClick('menupoint',this);sender('menuform','logview');" onmouseover="menuOver(this)" onmouseout="menuOut(this)">logview</span></td>
            <td class="menup"><span id="traffic" class="menuOff" onclick="menunClick('menupoint',this);sender('menuform','traffic');" onmouseover="menuOver(this)" onmouseout="menuOut(this)">traffic&nbsp;view</span></td>
            <td class="menup"><span id="refresh" class="menuOff" onclick="menunClick('menupoint',this);sender('menuform','refresh');" onmouseover="menuOver(this)" onmouseout="menuOut(this)">refresh</span></td>
        </tr>
    </tbody>

</table>
    
<form id="menuform" method="post" action="<c:url value='menucontrol'/>" >
</form>

<script type="text/javascript">
    document.getElementById('${menuaction}').className = 'menuOn';
</script>