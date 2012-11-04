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