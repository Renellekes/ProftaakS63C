/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


'use strict';
angular.module('myApp.Factory',[])

.service('userService', function(){
    this.getUsers = function(){
        return users;
    };
    
    this.getUserById = function(id){
        for (var i = 0; i < users.length; i++){
            if (users[i].id === id){
                return users[i];
            }
        }
    };
    
    this.getNameForId = function(id){
        for (var i = 0; i < users.length; i++){
            if (users[i].id === id){
                return users[i].name;
            }
        }
    };
    
    this.getUserByName = function(name){
      for (var i = 0; i < users.length; i++){
            if (users[i].name === name){
                return users[i];
            }
        }  
    };
    
    this.addTweetForUser = function(tweet, id){
        this.getUserById(id).tweets.push({content : tweet, date : new Date()});
    };
    
    var users = [
        {
            id : 3, name : 'Bliss B', location : 'Thuis',web : 'https://www.happy.nl', bio : 'springen rennen',
            tweets : [
                {
                    content : 'Ik heb gesprongen vandaag', date : new Date()
                }
            ],
            following : [
                {
                    id : 1
                }
            ],
            followers : [
                {
                    id : 2
                }
            ]
        }
    ];
});