/*
 *miRNA Design Tool - admin log controller
 */

function serverLogResponseHandler( req, textID ) {
    
    if( req.readyState == 4 ) {
        
        if( req.status == 200 ) {
            
            var logText =  req.responseXML.getElementsByTagName("msg")[0].childNodes[0].childNodes[0].nodeValue;
            
            document.getElementById( textID ).innerHTML = '';
            document.getElementById( textID ).innerHTML = logText;
            
        } else {
            alert( 'Loading error!\nStatus:' + req.status );
        }
    } else {
        document.getElementById( textID ).innerHTML = 'Loading ...';
    }
}

function requestControl( sessid, textID ){
    
    var unameResult = document.viewform.uname.value;
    
    var udateResult = document.viewform.udate.value;
    
    var viewResult = '';
    var uradio = document.viewform.uview;    
    var uradioL = uradio.length;
    
    for( i=0; i < uradioL; i++ )
        if( uradio[i].checked ) {
            viewResult = uradio[i].value;
            break;
        }
    
    var url = 'control';
    
    var req = new getXMLObject();
    
    if( req ) {
        req.open("POST", url, true);
        req.onreadystatechange = function(){serverLogResponseHandler(req,textID);};
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        var params = 'jsessionid=' + sessid + '&action=logview&uview=' + viewResult + '&uname=' + unameResult + '&udate=' + udateResult;
        req.send(params);
        
    } else {
        alert( "Object generating error!" );        
    }
    
}