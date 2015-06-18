/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function get(name){
   if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
      return decodeURIComponent(name[1]);
}

var id = get('facid');

r = new XMLHttpRequest();
r.open('POST', 'http://localhost:5051/RekeningAdministratieBackend/api/RekAdmin/Facturen/'+id+'/Betaald', true);
r.send();