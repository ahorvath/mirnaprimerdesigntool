/*
 *comment
 **/

function chkboxon_db_click( id, groupname, selected, param, form_id ){
    
    if( selected ){
        dbclickcontrol( id, groupname );
        
    } else {
        clickcontrol( id, groupname );
    }
    
    searchStatus( groupname );
    chkAllButton(param, form_id);
    
    return;
    
}

function dbclickcontrol( id, groupname ){
    
    var chkbox = document.getElementById(id);
    
    chkbox.checked = true;
    
    var nlist = id.split( '_' );
    
    var idlist = nlist[1].split( ':' );
    
    var idcount = idlist.length;
    
    var nindex = (idcount - 1);
    
    var chkboxgroup = document.getElementsByName( groupname );
    
    var chkboxcount = chkboxgroup.length;
    
    for( var i=0; i < chkboxcount; i++ ){
        
        var e_nlist = chkboxgroup[i].id.split("_");
        
        var e_ids = e_nlist[1].split(":");
        
        var e_idsl = e_ids.length;
        
        if( (idlist[0] == e_ids[0]) && (idcount < e_idsl) && (idlist[nindex] == e_ids[nindex]) ){
            
            chkboxgroup[i].checked = true;
            chkboxgroup[i].disabled = true;
        }
    }
    
    return;
}

function compprocess( id, groupname ){
    
    var nlist = id.split( '_' );
    
    var idlist = nlist[1].split(':');
    
    var idcount = idlist.length;
    
    if( idcount > 1 ){
        
        var clicklist = '';
        
        var offset = (idcount - 2);
        
        var chkboxgroup = document.getElementsByName( groupname );
        
        var chkboxcount = chkboxgroup.length;
        
        var parentid = null;
        
        var parent = null;
        
        for( var i=0; i < chkboxcount; i++ ){
            
            var e_id = chkboxgroup[i].id;
            
            var e_nlist = e_id.split( '_' );

            var e_idlist = e_nlist[1].split(':');            
            
            if( (e_idlist[0] == idlist[0]) && (e_idlist[offset] == idlist[offset]) ){
                
                var e_idcount = e_idlist.length;
                
                if( ( e_idcount == (idcount)) && (chkboxgroup[i].checked == false) ){
                    clicklist = null;
                    break;
                    
                } /*else if( chkboxgroup[i].checked == false ){
                    clicklist = null;
                    break;
                    
                } */else if( e_idcount == idcount ){
                    if( clicklist.length > 0 ){
                        clicklist = clicklist + "-";
                    }
                    clicklist = clicklist + chkboxgroup[i].id;
                    
                } else if( (e_idcount > 1) && (e_idcount < idcount) ){
                    parentid = e_id;
                }
                if( e_idcount == (idcount - 1)){
                    parent = chkboxgroup[i];
                }
            }
            
        }
    }
    
    if( (clicklist != null) && (clicklist.length > 0) ){
        selectcontrol( clicklist, '-', true, true );
        parent.checked = true;
    }
    
    return parentid;
    
}

function clickcomplement( id, groupname ){
    
    var parentid = id;
    
    while( parentid != null){
        parentid = compprocess( parentid, groupname );
    }
    
    return;
    
}

function selectcontrol( idstream, delim, chk, disab ){
    
    var idlist = idstream.split( delim );
    
    var idlistcount = idlist.length;
    
    for( var j=0; j < idlistcount; j++ ){
        
        if( chk != null ){
            document.getElementById( idlist[j] ).checked = chk;
        }
        
        if( disab != null ){
            document.getElementById( idlist[j] ).disabled = disab;            
        }
    }
    
    return;
}

function clickcontrol( id, groupname ){
    
    var chkbox = document.getElementById(id);    
    var chkd = chkbox.checked;
    
    var nlist = id.split( '_' );
    
    var idlist = nlist[1].split(':');
    
    var idcount = idlist.length;
    
    var nidx = (idcount - 1);
    
    var chkboxgroup = document.getElementsByName( groupname );    
    var chkboxcount = chkboxgroup.length;
    
    var clicklist = '';
    
    for( var i=0; i < chkboxcount; i++ ){
        
        var e_nlist = chkboxgroup[i].id.split( '_' );

        var e_idlist = e_nlist[1].split(':');        
        
        var e_idcount= e_idlist.length;
        
        if( (idcount < e_idcount) && (e_idlist[0] == idlist[0]) && (e_idlist[nidx] == idlist[nidx]) ){
        
          if( !chkd ){

              if( chkboxgroup[i].disabled == true ){
                  chkboxgroup[i].checked = false;
                  chkboxgroup[i].disabled = false;

              } else {
                  break;
              }

          } else if( chkd ){

              if( chkboxgroup[i].checked == true ){
                  
                  if( clicklist.length > 0 ){
                      clicklist = clicklist + "-";
                  }
                  
                  clicklist = clicklist + chkboxgroup[i].id;

              } else{
                  clicklist = null;
                  break;
              }

          }
          
      }
        
    }
    
    if( (clicklist != null) && (clicklist.length > 0) ){
        selectcontrol( clicklist, '-', null, true );
    }
    
    return;
}

function searchStatus( ename ) {

  var element = document.getElementsByName( ename );

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
  
  return;

}

//if hide equals true then 'HideAll'
//if hide equals false then 'ShowAll'
function showHideButton( hide ){
    
    var shaID = document.getElementById( 'showHide' );
    
    if( hide ){
        shaID.value = 'Hide all';
        shaID.title = 'Collapse all groups';
        
    } else {
        shaID.value = 'Show all';
        shaID.title = 'Expand all groups';        
    }
    
    return;
    
}

function showHideMenu( groupid ) {

    var element = document.getElementsByTagName('span');
    
    var l = element.length;
    
    var hide = false;
    
    var subID = ('s_' + groupid);

    for( var i=0; i < l; i++ ) {

        var mainEl = document.getElementById( groupid );

        var el = element[i];

        if( el.id == subID ) {

            if( el.className == 'hideGroup' ){

                el.className = 'showGroup';
                mainEl.innerHTML = '\xa0-';

            } else {

                el.className = 'hideGroup';
                mainEl.innerHTML = '+';

            }
        }
        
        if( (!hide) && (el.className == 'showGroup') ){
            hide = true;
        }        

    }
    
    showHideButton( hide );
    
    return;
    
}

function showHideAll( param ) {

    var shaID = document.getElementById( param );

    var visib = 'showGroup';
    var pre = '\xa0-';

    if( shaID.value == 'Show all' ) {
        shaID.value = 'Hide all';
        shaID.title = 'Collapse all groups';
    } else {
        shaID.value = 'Show all';
        shaID.title = 'Expand all groups';
        visib = 'hideGroup';
        pre = '+';
    }

    var element = document.getElementsByTagName('span');

    var prefix = document.getElementsByTagName('a');
    
    var l = element.length;

    for( var i=0; i < l; i++ ) {

        element[i].className = visib;

        if( prefix[i].className == 'linkGroup' ) {
            prefix[i].innerHTML = pre;
        }

    }
    
    return;

}

function chkAllButton( param, form_id ){
    
    var selbtn = document.getElementById( param );
    
    var frm = document.getElementById( form_id );
    
    var l = frm.geneGroup.length;    
    
    var all = false;
    
    for( var i=0; i < l; i++ ) {
        
        if( frm.geneGroup[i].checked == false ){
            all = true;
            break;
        }
        
    }
    
    if( all ){
        selbtn.value = 'Check all';
        selbtn.title = 'Select all groups.';
        
    } else {
        selbtn.value = 'Uncheck all';
        selbtn.title = 'Unselect all groups.';        
    }
    
   return; 
}

function chkSelec( param, form_id ) {

    var selID = document.getElementById( param );

    var sel = false;

    if( selID.value == 'Check all' ) {
        selID.value = 'Uncheck all';
        selID.title = 'Unselect all groups.';
        sel = true;
    } else {
        selID.value = 'Check all';
        selID.title = 'Select all groups.';
    }
    
    var frm = document.getElementById( form_id );
    
    var l = frm.geneGroup.length;

    for (i=0; i < l; i++){
        frm.geneGroup[i].checked = sel;
        
        if( frm.geneGroup[i].id.match('^sub') != null ){           
            frm.geneGroup[i].disabled = sel;
        }
    }
    
    return;

}