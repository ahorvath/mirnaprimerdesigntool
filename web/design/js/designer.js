function checkallbox( ename ){
    
    var chkall = document.getElementById( 'checkall' ).checked;
    
    var element = document.getElementsByTagName( 'input' );
    
    var l = element.length;
    
    for( var i=0; i < l; i++ ){
        
        var e = element[i];
        
        if( e.name == ename ){
            e.checked = chkall;
        }
    }
    
    document.forms[0].Design.disabled = !chkall;
}

function designButton( ename ) {
    
    var element = document.getElementsByName( ename );
    
    var n = element.length;
    
    var c = 0;
    
    for( var i=0; i < n; i++ ) {
        
      var el = element[i];

      if( el.checked == true ) {
          c = c + 1;
      }        
        
    }
    
    if( c == n ){
        document.getElementById( 'checkall' ).checked = true;
        
    } else {
        document.getElementById( 'checkall' ).checked = false;
    }
    
    if( c > 0 ) {
        document.forms[0].Design.disabled = false;
        
    } else {
        document.forms[0].Design.disabled = true;
    }
}

//own miRNA design
function trim(str, chars) {
    return ltrim(rtrim(str, chars), chars);
}

function ltrim(str, chars) {
    chars = chars || "\\s";
    return str.replace(new RegExp("^[" + chars + "]+", "g"), "");
}

function rtrim(str, chars) {
    chars = chars || "\\s";
    return str.replace(new RegExp("[" + chars + "]+$", "g"), "");
}

function nucleotideCheck( str ) {
    var regex = new RegExp( '[^acguACGU]+');
    return regex.test( str );
}

function nucleotideLength( str ) {
    return ( str.length < 17 );
}

function miRNACheck( form_id, action ) {
    
    var token;
    
    var alertMessage = '';
    
    var containerPrefix = 'Min. length 17 character!\n' + 
                    'Valid characters: a,c,g,u,A,C,G,U\n\n';
    
    var lengthAlert = 'Too short nucleotide sequence';
    
    var checkAlert = 'nvalid character(s)!';
    
    var nucLength, nucCheck;
    
    var element = document.getElementById( 'ownMiRNASeq' );
    
    var str = element.value;
    
    var tokenizer = str.split( '\n' );
    
    for( var i=0; i < tokenizer.length; i++) {
        token = trim( tokenizer[i] );
        
        nucLength = nucleotideLength( token );
        
        nucCheck = nucleotideCheck( token );
        
        if( nucLength && nucCheck ) {
            alertMessage = alertMessage + lengthAlert + ' and i' + checkAlert;
            
        } else if( nucLength ) {
            alertMessage = alertMessage + lengthAlert + '!';
            
        }
        
        if( !nucLength && nucCheck ) {
            alertMessage = alertMessage + 'I' + checkAlert + '!';
        }
        
        if( nucLength || nucCheck ) {
            alertMessage = alertMessage + '\n' + 
                        (i+1) + ': ' + token + '\n\n';
        }
    }
        
        if( alertMessage.length > 0 ) {
            alert( containerPrefix + alertMessage + '\nRepair, please!');
            
        } else {
            sender(form_id, action);
            
        }
}