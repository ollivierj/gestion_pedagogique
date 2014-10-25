'use strict';
 
services.factory('AuthentificationFactory',
    function (Base64, $http, $cookieStore, $rootScope) {
        var service = {};
        service.Login = function (username, password) {
            return $http.post('/ng_gst_pdg/web/utilisateurs/login', { login : username, motPasse: password });
        };

        service.setCredentials = function (data) {
            var authTokenData= Base64.encode(data.login + ':' + data.motPasse);
            var utilisateurConnecteData =
    			{
    			id : data.id,
    			prenom : data.prenom,
    			nom : data.nom,
    			profil : {droits : data.profil.droits},
    			};
            $http.defaults.headers.common.Authorization =  'Basic ' + authTokenData;
            $cookieStore.put('authtoken', authTokenData);
            $cookieStore.put('utilisateurConnecte', utilisateurConnecteData);
        	$rootScope.authtoken = authTokenData;
        	$rootScope.utilisateurConnecte = utilisateurConnecteData;
        };
 
        service.clearCredentials = function () {
            delete $rootScope.authtoken;
            delete $rootScope.utilisateurConnecte;
            $cookieStore.remove('authtoken');
            $cookieStore.remove('utilisateurConnecte');
            $http.defaults.headers.common.Authorization =  'Basic ';
        };
        return service;
    })
 
.factory('Base64', function () {
    var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
    return {
        encode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;
 
            do {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);
 
                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;
 
                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }
 
                output = output +
                    keyStr.charAt(enc1) +
                    keyStr.charAt(enc2) +
                    keyStr.charAt(enc3) +
                    keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);
 
            return output;
        },
 
        decode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;
            var base64test = /[^A-Za-z0-9\+\/\=]/g;
            if (base64test.exec(input)) {
                window.alert("Les login/mode de passe ne peuvent pas être encodés en Base64");
            }
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
            do {
                enc1 = keyStr.indexOf(input.charAt(i++));
                enc2 = keyStr.indexOf(input.charAt(i++));
                enc3 = keyStr.indexOf(input.charAt(i++));
                enc4 = keyStr.indexOf(input.charAt(i++));
 
                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;
 
                output = output + String.fromCharCode(chr1);
 
                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);
            return output;
        }
    };
});