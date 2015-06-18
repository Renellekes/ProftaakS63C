/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function login(){
    var username = document.getElementById( "username" ).value;
    var password = document.getElementById( "password" ).value;
    if ((username == "admin" && password == "admin") || (username == "harry" && password == "harry")){
        setCookie("username", username, 365);
        alert("Welcome again " + username);
        document.location = "index.html";
    }else{ 
        alert("Wrong credentials");
    }
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;  
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
    }
    return "";
}

function checkCookie() {
    var username=getCookie("username");
    if (username =="admin" || username=="harry") {
        
    }else{
        logout();
        
    }
}

function isAdmin(){
    return (getCookie("username") == "admin");
}

function logout(){
    document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC";
    window.location.assign("login.html");
}