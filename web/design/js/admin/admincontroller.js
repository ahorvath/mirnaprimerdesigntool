/*
 *miRNA Design Tool - admin controller
 */

function menuAccountCreateProcess( fieldIDs, form_id, action, msgID, emailID, passID ) {    
    
    document.getElementById( msgID ).innerHTML = '';

    var passl = true;
    
    if( action == 'modify' ){
      var e = document.getElementById( passID );
      var passw = e.value;
      if( passw.length > 5 ) {
        var hashPass = hex_md5( passw );
        e.value = hashPass;

      } else if( (passw.length > 0) && (passw.length < 6) ) {
          passl = false;
          addMsg( msgID, 'The length of password minimum 6 characters!');
      }
    }    
    
    var fieldFilt = fieldFilter( fieldIDs, msgID );

    if( !fieldFilt ) {
        addMsg( msgID, 'Required field cannot be left blank!');
    }
    
    var emailField = document.getElementById( emailID );
    
    var emailValid = emailAddressChek( emailField, msgID );

    if( !emailValid ) {
        emailField.style.borderColor = 'red';
        addMsg( msgID, 'Please, enter a valid e-mail address!');
    }
    
    if( fieldFilt && emailValid && passl ) {
        
        sender(form_id, action);
        
    }
        
}

function modifyRefresh( usname, action ) {
    
    var act = 'action=' + encodeURIComponent( action );
    
    var usn = 'usname=' + encodeURIComponent( usname );
    
    var url = 'controlx?' + act + '&' + usn;
    
    requestControl( url );
    
}

function remainderFill( ids, data ){
    
    var l = ids.length;
    
    for( var i=0; i<l; i++ ){
        
        var e = document.getElementById( ids[i] );
        
        if( e.type == 'checkbox' ) {
            if( data[i] == 'true' )
                e.checked = false;
            else
                e.checked = true;
            
        } else {
            e.value = data[i];
        }
        
    }
}

function selectUnselect( rowID, slct ) {
    
    var r = document.getElementById( rowID );
    
    var cName = r.className;
    
    if( (slct == 'sel') && (cName != 'clickedRow') ) {
        r.className = 'selectedRow';
        
    } else if( (slct == 'un') && (cName != 'clickedRow') ) {
        r.className = 'unselectedRow';
    }
}

function showHideUser( rowID, tagID ) {
    
    var r = document.getElementById( rowID );
    var e = document.getElementById( tagID );
    
    var c = e.className;
    
    if( c == 'hideUser' ) {
        r.className = 'clickedRow';
        e.className = 'showUser'
        
    } else {
        r.className = 'selectedRow';
        e.className = 'hideUser';
    }
    
}
