<%-- 
comment
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="org.mirna.designer.logger.*" %>

<c:set var="ptitle" value="" scope="request"/>

<form id="mdtform" method="post" action="<c:url value='processor'/>">

    <div class="content" >

         <p id="designlink">
             <a href="<c:url value='index.jsp?action=startpage'/>" target="_blank" title="Custom Oligo Design">Custom Oligo Design</a>
             &nbsp;
             <a href="http://www.astridbio.com/about-us.html" target="_blank" title="Contact">Contact</a>
         </p>        

         <h1>miRNA Design Tool</h1>
        
         <p class="parag">             
             The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection.&nbsp;
             The user gets the best result by two different Tm calculating methods.&nbsp;
             The tool designs the miRNA specific sequence of the stem-loop RT primer as well.&nbsp;
         </p>
         
         
         <h1>Species</h1>
         
         <p class="parag">             
             Select an organism in which you are interested and then scroll down to read the Search instructions below.             
         </p>
         
         <ul class="none">
             <li>
                 <input type="radio" name="genedb" value="HomoSapiens" checked />
                 <strong>Homo Sapiens Genom Database</strong>
             </li>

             <li>
                <input type="radio" name="genedb" value="MusMusculus" />                    
                <strong>Mus Musculus Genom Database</strong>
             </li>

             <li>
                <input type="radio" name="genedb" value="Drosophila" />
                <strong>Drosophila Melanogaster Genom Database</strong>
             </li>

             <li>
                <input type="radio" name="genedb" value="RattusNorvegicus" />
                <strong>Rattus Norvegicus Genom Database</strong>
             </li>
         </ul>
         
         
         <h1>Search</h1>
         
         <p class="parag">
             The search page allows you to search the database in a number of ways.&nbsp;
             You can search for miRNAs, genes and gene groups by identifier or keyword in the panel.&nbsp;<br/>
             By searching for gene groups we have to add that the classification of the gene groups is based on the Panther Classification System which classifies only the protein-coding genes.&nbsp;
             From the results of and type of search or browse you can click on a miRNA name to access the entry page of miRBase from where the miRNA derive.&nbsp; 
             The miRBase Sequence Database is a searchable database of published miRNA sequences and annotation.&nbsp;
             Also to click on a GeneID you will be delivered to the appropriate page of Entrez database (NCBI) where you can find additional data of your interest.&nbsp;
         </p>
         
         
         <h1>Search for miRNA</h1>
         
         <p class="parag">             
             You have the choice to search for intragenic or intergenic or all of the miRNAs.
         </p>
         
         <ul class="none">
             <li>
                 <input type="radio" name="miRNAStyle" value="intragenic" checked />
                 <strong>intragenic miRNAs</strong>
             </li>
             
             <li>
                 <input type="radio" name="miRNAStyle" value="intergenic" />
                 <strong>intergenic miRNAs</strong>
             </li>
             
             <li>
                 <input type="radio" name="miRNAStyle" value="all" />
                 <strong>all miRNAs</strong>                 
             </li>
         </ul>
         
         <p class="parag">
             Type search terms in the text box.&nbsp;
             You can search by identifier (e.g. "let-7c" or "mir-361") or adding a keyword (e.g. "let" or "124").&nbsp;
             You are entered in to the page which contains information about the miRNA and the related gene of interest and depicts the precursor sequence with the mature miRNA sequence highlighted.<br/>
         
             <em class="function">
                 <strong>Enter a microRNA name:</strong>
                 <input id="field" type="text" name="miRNAName" onfocus="changeBackColor(this.id, 'on');" onblur="changeBackColor(this.id, 'blur');" onkeypress="if( event.keyCode == 13 ) {return false;} else return true;"/>
                 &nbsp;&nbsp;
                 <input id="butt0" class="button" type="button" value="Search" title="Search for gene(s)." onclick="textfilter('field','mdtform','mirnasearch');"/>
             </em>
         </p>
         
         
         <h1>View all miRNAs</h1>
         
         <p class="parag">
             You can also browse the database of each miRNAs of the selected species.&nbsp;<br/>
             In this page you may find basic information and from here you can access the miRBase database by clicking on the miRNA and the NCBI page related to the gene of interest.
             
             <input id="butt1" class="button" type="button" value="View all miRNAs" title="View all miRNAs." onclick="sender('mdtform','allmirna');"/>
         </p>
         
         
         <h1>Search for gene(s)</h1>
         
         <p class="parag">
             Search by gene name, gene symbol or gene ID (Entrez ID) or by keywords.&nbsp;
             With copy-paste process you can search for more genes but note that one row should contain only one gene term.&nbsp;
             You can search by single or multiple terms.<br/>
             
             <em>
                 <strong>Enter gene properties (gene id or gene symbol or gene name):</strong>
                 <br/>
                 <textarea id="geneProp" class="tarea" name="geneProperty" onfocus="changeBackColor(this.id,'on');" onblur="changeBackColor(this.id, 'blur');" rows="0" cols="0"></textarea>
                 <br/>
                <input id="butt2" class="button" type="button" value="Search" title="Search for gene(s)." onclick="textfilter('geneProp','mdtform','genesearch');"/>
            </em>
        </p>
        

        <h1>Search for genegroup(s)</h1>
        
        <p class="parag">
            Browse gene(s) by choosing one or more groups of biological process. You can search for gene families or subfamilies.
            &nbsp;&nbsp;
            <input id="butt3" class="button" type="button" value="Gene Group Browser" title="Search for genegroup(s)." onclick="sender('mdtform', 'genegroup');"/>
        </p>
    </div>

</form>

<% ActivityLogger.pageView( request, "midetool1" ); %>
