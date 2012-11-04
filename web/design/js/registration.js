/* 
 *miRNA Design Tool - registration
 */

function regist() {

    location.href = 'index.jsp?action=regist';
    return;
    
}

function reg_control( fieldIDs, passwdID, cpasswdID, captchapasswordID, emailID, msgID ) {
    document.getElementById( msgID ).innerHTML = '';

    var fieldFilt = fieldFilter( fieldIDs, msgID );

    if( !fieldFilt ) {
        addMsg( msgID, 'Required field cannot be left blank!');
    }
    
    var emailField = document.getElementById( emailID );

    var emailValid = emailAddressChek( emailField );

    if( !emailValid ) {
        emailField.style.borderColor = 'red';
        addMsg( msgID, 'Please, enter a valid e-mail address!');
    }
    
    var passwdField = document.getElementById( passwdID );
    var cpasswdField = document.getElementById( cpasswdID );

    var passwdMatch = fieldMatch( passwdID, cpasswdID );

    var passl = ( passwdField.value.length > 5 );

    if( !passwdMatch ) {        
        passwdField.style.borderColor = 'red';        
        cpasswdField.style.borderColor = 'red';
        addMsg( msgID, 'Passwords do not match!');

    } else if( !passl ) {
        passwdField.style.borderColor = 'red';
        cpasswdField.style.borderColor = 'red';
        addMsg( msgID, 'The length of password minimum 6 characters!');
    }

    var captchaOk = jcap(captchapasswordID);
    var captchaField = document.getElementById( captchapasswordID );

    if(!captchaOk) {
        //captchaField.style.borderColor = 'red';
        captchaField.value = '';
        addMsg( msgID, 'The captcha password is not valid!');
    }

    if( fieldFilt && emailValid && passwdMatch && passl && captchaOk) {
        
        var passwd = passwdField.value;
        var passwd_hash = hex_md5( passwd );
        passwdField.value = "";
        cpasswdField.value = "";

        var cookie = IEScanner();//session add jsession on IE

        if( cookie == null )
            return

        window.setTimeout("loadingevent('divload', 'divcontent', false)", 1);

        var params = 'reg_email=' + emailField.value +
        '&passwd=' + passwd +
        '&passwd_hash=' + passwd_hash;
                 
        var l = fieldIDs.length;

        for( i=0; i < l; i++ ){

            var fieldID = fieldIDs[i];

            if( (fieldID != emailID ) && (fieldID != passwdID) && (fieldID != cpasswdID) ) {

                var e = document.getElementById( fieldID );

                params = params + "&" + fieldID + "=" + e.value;

            }
        }

        var req = new getXMLObject();

        if( req ) {
            req.open("POST", "reg", true);
            req.onreadystatechange = function(){
                registrationResponseHandler( req, msgID );
            };
            req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            req.send( params );

        } else {
            alert( "Object generating error!" );

        }
    }
}

function registrationResponseHandler( req, msgID ) {

    if( req.readyState == 4 ) {

        if( req.status == 200 ) {

            document.getElementById( msgID ).innerHTML = '';
            
            var e_list =  req.responseXML.getElementsByTagName("msg")[0].childNodes;

            var msg0 = e_list[0].childNodes[0].nodeValue;
            var msg1 = e_list[1].childNodes[0].nodeValue;

            window.setTimeout("loadingevent('divload', 'divcontent', true)", 1);

            if( msg0 == 'ok' ) {

                document.getElementById( 'reg_form' ).innerHTML = '';
                addMsg( 'reg_form', msg1 );

            } else if( msg0 == 'fail' ) {

                addMsg( msgID, msg1 );

            }


        } else {
            alert( 'Registration unsuccessful!\nLoading error!\nStatus:' + req.status );
            window.setTimeout("loadingevent('divload', 'divcontent', false)", 1);
        }
    } else {
        document.getElementById( msgID ).innerHTML = 'Registration ...';
    }

}
