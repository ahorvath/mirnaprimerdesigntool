function buttonStatus() {

  var element = document.getElementsByName( "geneGroup" );

  var n = element.length;

  for( var i=0; i < n; i++ ) {

      var el = element[i];

      if( el.checked == true ) {
          i = n;
      }

  }              

  if( i > n ) {
      document.forms[0].Search.disabled = false;

  } else {
      document.forms[0].Search.disabled = true;

  }

}