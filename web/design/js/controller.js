/* 
 * miRNA Design Tool
 * comment
 */

function getXMLObject()  //XML OBJECT
{
   var xmlHttp;

   if ( typeof XMLHttpRequest != "undefined" ) {

     xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers

   } else {

     try {

         xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); // For Microsoft IE 6.0+

     } catch (e) {

         try {

           xmlHttp = new ActiveXObject("Msxml2.XMLHTTP"); // For Old Microsoft Browsers

         } catch (e2) {

             xmlHttp = false;   // No Browser accepts the XMLHTTP Object then false
       }

     }

   }

   return xmlHttp;
}

function IEScanner(){

    var result = true;

    var b_name = navigator.appName;

    document.cookie="testcookie";
    var cookie = ( document.cookie.indexOf("testcookie") != -1 ) ? true : false ;

    if( b_name == 'Microsoft Internet Explorer' ){
        if( !cookie ){
            document.getElementById('passwd').value = '';
            alert( "Your browser's cookie functionality is turned off.\nPlease turn it on." );
            result = null;

        } else {
            result = false;
        }
    }

    return result;

}

function loadingevent( dl_id, dc_id, visib ){
    
    var dl = document.getElementById( dl_id );
    
    var dc = document.getElementById( dc_id );
    
    
    if( visib ){
        dl.className = 'divloadN';
        dc.className = 'block';
        
    } else {
        dl.className = 'divloadV';
        dc.className = 'none';
    }
}

function fieldMatch( field1_ID, field2_ID ) {

    var text1 = document.getElementById( field1_ID ).value;
    var text2 = document.getElementById( field2_ID ).value;

    return ( text1 == text2 );

}

function emailAddressChek( emailField ){

    var goodEmail = emailField.value.match(/\b(^(\S+@).+((\.com)|(\.net)|(\.edu)|(\.mil)|(\.gov)|(\.org)|(\..{2,2}))$)\b/gi);

    return goodEmail;

}

function fieldFilter( fieldIDs ){

    var l = fieldIDs.length;

    var fieldsOK = true;

    for( i=0; i < l; i++ ){
        var e = document.getElementById( fieldIDs[i] );

        if( (e.value.length == 0) && (e.disabled == false) ){
            e.style.borderColor = 'red';
            if( fieldsOK ) fieldsOK = false;

        } else {
            e.style.borderColor = '#a8c9f6';
        }
    }

    return fieldsOK;
}

function addMsg( id, text ){

    var e = document.getElementById( id );

    var tmp = e.innerHTML;

    if( tmp.length > 0 ) {
        e.innerHTML = tmp + '<br/>' + text;

    } else {
        e.innerHTML = text;
    }

}

function getScroller( direction ) {
    
    var scr = null;
    var inn = null;
    var wNoScroll = 0;
    var wScroll = 0;
    
    // Outer scrolling div
    scr = document.createElement('div');

    scr.style.position = 'absolute';

    scr.style.top = '-1000px';

    scr.style.left = '-1000px';

    scr.style.width = '100px';

    scr.style.height = '50px';

    // Start with no scrollbar
    scr.style.overflow = 'hidden';
    
    // Inner content div
    inn = document.createElement('div');
    
    if( direction == 'width'){
        inn.style.width = '100%';
        inn.style.height = '200px';        
        
    } else if( direction == 'height' ){        
        inn.style.width = '200px';
        inn.style.height = '100%';                
    }

    // Put the inner div in the scrolling div
    scr.appendChild(inn);

    // Append the scrolling div to the doc
    document.body.appendChild(scr);
    
    // Width of the inner div sans scrollbar
    if( direction == 'width' )
        wNoScroll = inn.offsetWidth;
    
    else if( direction == 'height')
        wNoScroll = inn.offsetHeight;

    // Add the scrollbar
    scr.style.overflow = 'auto';

    // Width of the inner div width scrollbar
    if( direction == 'width')
        wScroll = inn.offsetWidth;
    
    else if( direction == 'height')
        wScroll = inn.offsetHeight;

    // Remove the scrolling div from the doc
    document.body.removeChild(
    document.body.lastChild);

    // Pixel width of the scroller
    return (wNoScroll - wScroll);
}

function getYOffset(){
    
    var yOffset = window.pageYOffset;
    
    if( typeof(yOffset) != 'number' ){
        yOffset = document.documentElement.scrollTop;
    }
    
    return yOffset;
}

function getXOffset(){
    var xOffset = window.pageXOffset;
    
    if( typeof(xOffset) != 'number' ){
        xOffset = document.documentElement.scrollLeft;
    }
    
    return xOffset;
}

function getInnerHeight(){
    
    var innerHeight = window.innerHeight;
    
    if( typeof(innerHeight) != 'number' ){
        innerHeight = document.documentElement.clientHeight;
    }
    
    return innerHeight - getScroller( 'height' );
}

function getInnerWidth(){
    
    var innerWidth = window.innerWidth;
    
    if( typeof(innerWidth) != 'number' ){
        innerWidth = document.documentElement.clientWidth;
    }
    
    return innerWidth - getScroller( 'width' );
}

function tooltipviewer( element, className, topPos, leftPos ){
    
    element.className = className;
    
    var e_div = element.getElementsByTagName('div')[0];
    
    if( topPos != null){
        e_div.style.top = topPos;
    }
    
    if( leftPos != null ){
        e_div.style.left = leftPos;
    }
    
    e_div.className = (className + 's');
    
    /*set div top ->*/
    var divTop = (element.offsetTop + e_div.offsetTop) - getYOffset();
    
    var divBottom = divTop + e_div.offsetHeight;
    
    var innerHeight = getInnerHeight();
    
    if( divBottom > innerHeight ){
        var newYPos = 0 - e_div.offsetHeight;
        e_div.style.top = ( newYPos + 'px' );
    }
    /*<- set div top */
    
    /*set div left ->*/
    var divLeft = (element.offsetLeft + e_div.offsetLeft ) - getXOffset();
    
    var divRight = divLeft + e_div.offsetWidth;
    
    var innerWidth = getInnerWidth();
    
    if( divRight > innerWidth ){
        var newXPos = 0 - e_div.offsetWidth;
        e_div.style.left = ( newXPos + 'px' );
    }
    /*<- set div left*/
    
    return true;
}

function trim( str ){
    return str.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
}

function changeBorder( id ){
    
    var e = document.getElementById(id);
    
    if( e.disabled == false ){
        
        var cname = e.className;
        
        var l = cname.length;
        
        if( cname.indexOf('h') == -1 ){
            e.className = (cname + 'h');
            
        } else {
            e.className = cname.substr( 0, (l-1) );
        }
        
    }
}

function inact( id, active ){
    
    alert( e.className );
    
    var e = document.getElementById(id);
    
    alert( e.className );
    
    if( e.disabled == true ){
        e.className = 'inactive';
        
    } else{
        e.className = active;
    }
    
    alert( e.className );
    
}

function changeTitle( mainTitle, subTitle ){
    
    var delim = " | ";
    
    if( subTitle.length > 0 ){
        mainTitle += (delim + subTitle);
    }
    
    document.title = mainTitle;
}

function changeBackColor( id, action ){

    var color = '#e5fff3';

    if( action == 'blur' )
        color = 'white';

    document.getElementById(id).style.backgroundColor=color;
}

function textfilter( text_id, form_id, action ){
    
    var text = document.getElementById( text_id ).value;
    
    var l = trim(text).length;
    
    if( l > 0 ){
        sender(form_id, action);
        
    } else {
        alert( "Text too short!\n\nPlease repair!" );
    }
}

function setFocus( e_id ) {
    document.getElementById(e_id).focus();
}

function sender( form_id, action ){
    
    var child = document.getElementById( "sendaction" );
    
    if( child != null ){
        document.getElementById( form_id ).removeChild( child );
    }
    
    var input = document.createElement( "input" );
    input.setAttribute( "id", "sendaction" );
    input.setAttribute( "type", "hidden" );
    input.setAttribute( "name", "action" );
    input.setAttribute( "value", action );
    document.getElementById( form_id ).appendChild( input );
    
    window.setTimeout("loadingevent('divload', 'divcontent', false)", 1);
    
    document.getElementById( form_id ).submit();
}