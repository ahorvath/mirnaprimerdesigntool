/*
 *miRNA Design Tool - login authentication
 */

function getpasskey( form_id ) {
    
    var cookie = IEScanner();//session add jsession on IE
    
    if( cookie == null )
        return;
    
    var req;
    
    var passwdLength = document.getElementById('passwd').value.length;
    var usnameLength = document.getElementById('usname').value.length;
    
    //if the password field text is length equals zero
    if( (passwdLength == 0) || (usnameLength == 0) ){
        location.href = 'index.jsp?action=invalidpass';
        return;
    }
    
    window.setTimeout("loadingevent('divload', 'divcontent', false)", 1);
    
    req = new getXMLObject();
    
    if( req ) {
        req.open("POST", "pdkey", true);
        req.onreadystatechange = function(){serverResponseHandler( req, form_id, cookie );};
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send(null);
        
    } else {
        alert( "Object generating error!" );
        
    }
        
}

function serverResponseHandler( req, form_id, cookie ) {
    
    if( req.readyState == 4 ) {
        
        if( req.status == 200 ) {
            
            var e_list =  req.responseXML.getElementsByTagName("msg")[0].childNodes;
              
            var msg0 = e_list[0].childNodes[0].nodeValue;
            var msg1 = e_list[1].childNodes[0].nodeValue;
            var id = e_list[2].childNodes[0].nodeValue;
            
            //passkey generated Exception
            if( msg0.match( "!$" ) ){
                document.getElementById('passwd').value = '';
                window.setTimeout("loadingevent('divload', 'divcontent', true)", 1);
                alert( msg0 + "\n\n" + "Please try again later the login!" );
                return;
            }
            
            auth( form_id, msg0, msg1, id, cookie );
            
        } else {
            alert( "Authentication!\n\nLoading error! - status: " + req.status );
            
        }
        
    } else {
        
        /*Loading*/
    }
}

function auth( form_id, msg0, msg1, sessid, cookie ){
    
    var usname = document.getElementById('usname');
    
    var passwd = document.getElementById('passwd');
    
    var s_usname = document.getElementById('sendusname');
    
    var s_passwd = document.getElementById('sendpassword_data');
    
    s_usname.value = usname.value;
    
    var passwd_hash = hex_md5( passwd.value );
    
    s_passwd.value = hex_md5( msg0 + passwd_hash + msg1 );   
    
    var loginform = document.getElementById( form_id );
    
    if( cookie ){
        loginform.action = "loginCheck;jsessionid=" + sessid;
    }
    
    loginform.submit();
}
