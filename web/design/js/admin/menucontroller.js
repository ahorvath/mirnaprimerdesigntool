function menuOver( obj ) {
    obj.style.color = '#ffffff';
}

function menuOut( obj ) {
    if( obj.className != 'menuOn' ) {
        obj.style.color = '#d8d9d4';
    }
}

function menunClick( eID, obj ) {
    var element = document.getElementById( eID );
    var spans = element.getElementsByTagName( 'span' );
    var l = spans.length;
    
    for( var i=0; i < l; i++ ) {
        spans[i].className = 'menuOff';
    }
    
    
    obj.className = 'menuOn';
}

